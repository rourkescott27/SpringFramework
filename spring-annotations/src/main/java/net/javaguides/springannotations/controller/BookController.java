package net.javaguides.springannotations.controller;

import net.javaguides.springannotations.beans.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
@RestController
public class BookController {

    @RequestMapping("/hello-world")
    //@ResponseBody
    public String helloWorld(){
        return "Hello World";
    }

    @RequestMapping("/book")
    //@ResponseBody
    public Book getBook(){
        Book book = new Book(1, "The Compound Effect", "The Compound Effect by Darren Hardy is a self-help book that shows how small, consistent actions—good or bad—compound over time to produce significant long-term results in your life, finances, health, and habits");
        return book;
    }
}
