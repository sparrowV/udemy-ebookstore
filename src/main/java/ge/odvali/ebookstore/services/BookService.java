package ge.odvali.ebookstore.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ge.odvali.ebookstore.entities.EBook;
import ge.odvali.ebookstore.kafka.BookProducer;
import ge.odvali.ebookstore.kafka.Topic;
import ge.odvali.ebookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookProducer bookProducer;

    public void sendDataToKafka(String topic, String bookName) {
        ObjectNode dataToSend = objectMapper.createObjectNode();
        dataToSend.put("bookTitle", bookName);
        Thread thread = new Thread(() -> {
            System.out.println("calling kafka book producer....");
            bookProducer.send(dataToSend.toPrettyString(), topic);
        });
        thread.start();
    }

    public ResponseEntity create(EBook book) {
        validateBook(book);
        EBook saved = bookRepository.save(book);
        sendDataToKafka(Topic.BOOK_CREATION_TOPIC, book.getTitle());
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }


    private void validateBook(EBook book) {
        if (!StringUtils.hasText(book.getTitle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title is mandatory!");
        }
        if (!StringUtils.hasText(book.getTitle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "authors is mandatory!");
        }

    }

    public List<EBook> findAll() {
        return bookRepository.findAll();
    }
}
