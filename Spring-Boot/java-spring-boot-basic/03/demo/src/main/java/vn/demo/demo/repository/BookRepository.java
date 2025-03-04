package vn.demo.demo.repository;

import vn.demo.demo.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
}
