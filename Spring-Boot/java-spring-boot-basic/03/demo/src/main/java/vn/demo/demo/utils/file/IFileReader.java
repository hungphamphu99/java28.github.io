package vn.demo.demo.utils.file;

import vn.demo.demo.model.Book;

import java.util.List;

public interface IFileReader {
    List<Book> readFile(String path);

}
