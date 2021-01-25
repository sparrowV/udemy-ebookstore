package ge.odvali.ebookstore.controllers;

import ge.odvali.ebookstore.entities.EBook;
import ge.odvali.ebookstore.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@SecurityRequirement(name = "jwt")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity create(@RequestBody EBook book) {
        return bookService.create(book);
    }

    @GetMapping
    public List<EBook> getAll() {
        return bookService.findAll();
    }

}
