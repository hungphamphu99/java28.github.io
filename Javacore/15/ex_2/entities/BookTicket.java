package ex_2.entities;

import ex_2.entities.Reader;

import java.time.LocalDate;

public class BookTicket {
    private Reader reader;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public BookTicket(Reader reader, Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.reader = reader;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    // Getters và Setters
    public Reader getReader() { return reader; }
    public void setReader(Reader reader) { this.reader = reader; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    @Override
    public String toString() {
        return "BookTicket{reader=" + reader + ", book=" + book + ", borrowDate=" + borrowDate + ", dueDate=" + dueDate + "}";
    }
}
