package vn.demo.demo.utils.file;

import org.springframework.stereotype.Component;
import vn.demo.demo.model.Book;

import java.io.FileReader;
import java.util.List;

@Component
public class JsonFileReader implements IFileReader {


    @Override
    public List<Book> readFile(String path) {


        return List.of();
    }
}
