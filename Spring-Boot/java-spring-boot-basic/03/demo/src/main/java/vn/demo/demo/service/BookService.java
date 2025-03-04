package vn.demo.demo.service;

import vn.demo.demo.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    List<Book> sortByYear();

    List<Book> geBookSByKeyword(String keyword);

    List<Book> getBookByYear(int startYear, int endYear);
}
