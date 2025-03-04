package vn.demo.demo.repository.impl;

import org.springframework.stereotype.Repository;
import vn.demo.demo.db.BookDB;
import vn.demo.demo.model.Book;
import vn.demo.demo.repository.BookRepository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Override
    public List<Book> findAll() {
        return BookDB.books;
    }



}
