package ge.odvali.ebookstore.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ge.odvali.ebookstore.entities.UserBookSubscription;
import ge.odvali.ebookstore.entities.UserNotification;
import ge.odvali.ebookstore.entities.UserNotificationID;
import ge.odvali.ebookstore.repositories.UserBookSubscriptionRepository;
import ge.odvali.ebookstore.repositories.UserNotificationRepository;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


@Component
public class BookConsumer {
    private KafkaConsumer<String, String> consumer;

    @Autowired
    private UserBookSubscriptionRepository userBookSubscriptionRepository;

    @Autowired
    private UserNotificationRepository userNotificationRepository;

    @Autowired
    private ObjectMapper mapper;

    public BookConsumer(@Value("${kafka.bootstrap.servers}") String bootstrapServers, @Value("${book.consumer.group.id}") String consumerGroupId) {
        Thread consumerThread = new Thread(() -> {
            // create consumer configs
            Properties properties = new Properties();
            properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);

            consumer = new KafkaConsumer<String, String>(properties);
            consumer.subscribe(Collections.singletonList(Topic.BOOK_CREATION_TOPIC));
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10));
                    consumer.commitSync();
                    if (!records.isEmpty()) {
                        System.out.println("Kafka consumer found record(s)...");
                    }
                    for (ConsumerRecord record : records) {
                        notifyUser(record);
                    }

                }
            } catch (WakeupException e) {
                System.out.println("kafka consumer wake up!");
            } finally {
                consumer.close();
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("closing kafka book event CONSUMER...");
            consumer.wakeup();
        }));
        consumerThread.start();

    }


    private void notifyUser(ConsumerRecord record) {
        Thread thread = new Thread(() -> {
            String bookTitle = getBookTitle((String) record.value());
            List<UserBookSubscription> userBookSubscriptions = userBookSubscriptionRepository.findByBookTitle(bookTitle);
            for (UserBookSubscription userBookSubscription : userBookSubscriptions) {
                UserNotificationID userNotificationID =
                        new UserNotificationID(userBookSubscription.getUserBookId().getUserId(),
                                String.format("The book with title=%s is now available", userBookSubscription.getUserBookId().getBookTitle()));
                UserNotification userNotification = new UserNotification();
                userNotification.setUserNotificationID(userNotificationID);
                userNotificationRepository.save(userNotification);
            }
        });

        thread.start();

    }

    private String getBookTitle(String value) {
        JsonNode parent = null;
        try {
            parent = mapper.readTree(value);
            return parent.path("bookTitle").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
