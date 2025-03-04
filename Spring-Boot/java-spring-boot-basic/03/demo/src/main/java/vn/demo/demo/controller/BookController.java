package vn.demo.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.demo.demo.model.Book;
import vn.demo.demo.service.BookService;


import java.util.List;


@RequestMapping("/books")
@RestController
public class BookController {

        private BookService bookService;
        public BookController(BookService bookService) {
            this.bookService = bookService;
        }

        @GetMapping
        public ResponseEntity<?> getAllBooks() {
            List<Book> books = bookService.getAllBooks();
            return ResponseEntity.ok(books);
        }


        
        @GetMapping("/sortByYear")
        public ResponseEntity<?> getBooksByYear() {
            List<Book> books = bookService.sortByYear();
            return ResponseEntity.ok(books);
        }

        @GetMapping("/search/{keyword}")
        public ResponseEntity<?> getBooksByKeyword(@PathVariable String keyword) {
            List<Book> books = bookService.geBookSByKeyword(keyword);
            return ResponseEntity.ok(books);
        }

        @GetMapping("/startYear/{startYear}/endYear/{endYear}")
        public ResponseEntity<?> getBooksByYear(@PathVariable int startYear, @PathVariable int endYear) {
            List<Book> books = bookService.getBookByYear( startYear, endYear);
            return ResponseEntity.ok(books);
        }


}
