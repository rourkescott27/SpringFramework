package net.javaguides.springannotations.controller;

import net.javaguides.springannotations.beans.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/api")
public class BookController {

    @RequestMapping("/hello-world")
    //@ResponseBody
    public String helloWorld () {
        return "Hello World";
    }

    @GetMapping(value = {"/book", "compound-effect", "/self-help"})
    //@ResponseBody
    public Book getBook () {
        Book book = new Book(1, "The Compound Effect", "The Compound Effect by Darren Hardy is a self-help book that shows how small, consistent actions—good or bad—compound over time to produce significant long-term results in your life, finances, health, and habits");
        return book;
    }

    @PostMapping(value = "/books/create",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
//    @ResponseStatus(value =  HttpStatus.CREATED)
    public ResponseEntity<Book> createBook ( @RequestBody Book book ) {
        System.out.println(book.getId());
        System.out.println(book.getTitle());
        System.out.println(book.getDescription());

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }


    // http:localhost:8080/api/books/update/1
    @PutMapping(value = "/books/update/{id}")
    public ResponseEntity<Book> updateBook ( @PathVariable int id, @RequestBody Book updatedBook ) {
        System.out.println(id);
        System.out.println(updatedBook.getTitle());
        System.out.println(updatedBook.getDescription());
        updatedBook.setId(id);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping(value = "/books/delete/{id}")
    public ResponseEntity<String> deleteBook ( @PathVariable int id ) {
        System.out.println("Deleted Book Id: " + id);
        return ResponseEntity.ok("Book deleted successfully!");
    }
}
