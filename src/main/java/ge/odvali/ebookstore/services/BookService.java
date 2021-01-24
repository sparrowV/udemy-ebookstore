package ge.odvali.ebookstore.services;

import ge.odvali.ebookstore.entities.EBook;
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

    public ResponseEntity create(EBook book) {
        validateBook(book);
        EBook saved = bookRepository.save(book);
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
