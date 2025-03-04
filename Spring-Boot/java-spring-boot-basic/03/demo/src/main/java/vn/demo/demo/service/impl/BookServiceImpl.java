package vn.demo.demo.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.demo.demo.model.Book;
import vn.demo.demo.repository.BookRepository;
import vn.demo.demo.service.BookService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> sortByYear() {
        List<Book> books = bookRepository.findAll();
        books.sort(Comparator.comparing(Book::getYear).reversed());
        return books;
    }

    @Override
    public List<Book> geBookSByKeyword(String keyword) {
        List<Book> books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().contains(keyword)) {
                result.add(book);
                return result;
            }
        }
        return result;
    }

    @Override
    public List<Book> getBookByYear(int startYear, int endYear) {
        List<Book> books = bookRepository.findAll();
        List<Book> rs = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() >= startYear && book.getYear() <= endYear) {
                rs.add(book);
            }
        }
        return rs;
    }


}
