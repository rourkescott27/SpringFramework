package net.javaguides.springannotations.controller;

import net.javaguides.springannotations.beans.Book;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = {"/book", "compound-effect", "/self-help"},
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    //@ResponseBody
    public Book getBook () {
        Book book = new Book(1, "The Compound Effect", "The Compound Effect by Darren Hardy is a self-help book that shows how small, consistent actions—good or bad—compound over time to produce significant long-term results in your life, finances, health, and habits");
        return book;
    }
}
