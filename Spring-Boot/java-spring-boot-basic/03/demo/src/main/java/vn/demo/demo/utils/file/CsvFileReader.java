package vn.demo.demo.utils.file;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.context.annotation.Primary;
import vn.demo.demo.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Primary
@Component
public class CsvFileReader implements IFileReader {
    private static final Logger log = LoggerFactory.getLogger(CsvFileReader.class);

    @Override
    public List<Book> readFile(String filePath) {
        log.info("Reading CSV file: {}", filePath);

        try (FileReader reader = new FileReader(filePath)) {
            CsvToBean<Book> csvToBean = new CsvToBeanBuilder<Book>(reader)
                    .withType(Book.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        } catch (IOException e) {
            log.error("Error reading CSV file: {}", filePath, e);
            return List.of();
        }
    }
}
