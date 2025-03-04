package vn.demo.demo.db;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import vn.demo.demo.model.Book;
import vn.demo.demo.utils.file.IFileReader;

import java.util.List;

@Slf4j
@Configuration
public class InitDB implements CommandLineRunner {
    private final IFileReader fileReader;

    public InitDB(@Qualifier("csvFileReader") IFileReader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Start init data");
        List<Book> books = fileReader.readFile("book.csv");
        log.info("Books size: {}", books.size());

        BookDB.books = books;
    }


}