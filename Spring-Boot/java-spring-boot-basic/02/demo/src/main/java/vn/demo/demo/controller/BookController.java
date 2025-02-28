package vn.demo.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.demo.demo.model.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// @controller trả và  view(template) , muốn trả về json/xml (bổ sung thêm)
// @RestController =  @controller + @ResponseBody
//@RestController
@RequestMapping("/books")
@Controller
public class BookController {
    private final List<Book> books;
    public BookController() {
        books = new ArrayList<>();
        books.add(new Book("OX-13", "Gone with the wind", "Cuong", 1945));
        books.add(new Book("OX-14", "Chi Dau", "Nam Cao", 1943));
    }

//    @GetMapping("/book")
//    public List<Book> getBooks() {
//        return books;
//    }


//    @GetMapping
//    public ResponseEntity<?> getBooks() {
//        return ResponseEntity.status(HttpStatus.CREATED).body(books);
//    }




    @GetMapping("/sortByYearDesc")
    public ResponseEntity<List<Book>> sortByYearDesc() {
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparingInt(Book::getYear).reversed()) // Sort by year (newest to oldest)
                .toList();
        return new ResponseEntity<>(sortedBooks, HttpStatus.OK);
    }


    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Book>> getBooksByKeyword(@PathVariable String keyword) {
        List<Book> bks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                bks.add(book);

            }
        }
        return  ResponseEntity.ok(bks);
    }

}
