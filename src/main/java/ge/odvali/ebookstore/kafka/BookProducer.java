package ge.odvali.ebookstore.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.UUID;

@Component
public class BookProducer {
    private KafkaProducer<String, String> producer;

    public BookProducer(@Value("${kafka.bootstrap.servers}") String bootstrapServers) {
        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        producer = new KafkaProducer<String, String>(properties);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("closing kafka book producer...");
            producer.close();
        }));

    }


    public void send(String dataToSend, String topic) {
        System.out.println("Sending data to book-creation-topic : " + dataToSend);
        ProducerRecord<String, String> record =
                new ProducerRecord<String, String>(topic, UUID.randomUUID().toString(), dataToSend);
        producer.send(record);
        producer.flush();
    }
}
