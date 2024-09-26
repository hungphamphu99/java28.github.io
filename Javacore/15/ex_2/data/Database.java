package ex_2.data;

import ex_2.entities.Book;
import ex_2.entities.BookTicket;
import ex_2.entities.Reader;
import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;

public class Database {
    public static List<Book> books = new ArrayList<>();
    public static List<Reader> readers = new ArrayList<>();
    public static List<BookTicket> bookTickets = new ArrayList<>();
    public Database(){

    }

    public static void inputData(){
        Book book = new Book("hung", "Hung", "Hung",12);
        books.add(book);
        Reader reader = new Reader("hung01","09","Hung");
        readers.add(reader);
        LocalDate borrowDate = LocalDate.of(2001, 1, 1);
        LocalDate dueDate = LocalDate.of(2003, 1, 1);
        BookTicket bookTicket = new BookTicket(reader, book, borrowDate, dueDate);
        bookTickets.add(bookTicket);
    }
}

