package vn.demo.demo.utils.file;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.demo.demo.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelFileReader implements IFileReader {
    private static final Logger log = LoggerFactory.getLogger(ExcelFileReader.class);

    @Override
    public List<Book> readFile(String filePath) {
        log.info("Reading Excel file: {}", filePath);
        List<Book> books = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            int firstRow = 1;

            for (int i = firstRow; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    String id = getStringCellValue(row.getCell(0));
                    String title = getStringCellValue(row.getCell(1));
                    String author = getStringCellValue(row.getCell(2));
                    int year = (int) row.getCell(3).getNumericCellValue();

                    Book book = Book.builder()
                            .id(id)
                            .title(title)
                            .author(author)
                            .year(year)
                            .build();

                    books.add(book);
                }
            }
        } catch (IOException e) {
            log.error("Error reading Excel file: {}", filePath, e);
        }
        return books;
    }


    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }
}
