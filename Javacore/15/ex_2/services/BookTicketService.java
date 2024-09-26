package ex_2.services;

import ex_2.data.Database;
import ex_2.entities.Book;
import ex_2.entities.BookTicket;
import ex_2.entities.Reader;
import ex_2.utils.InputUtils;

import java.time.LocalDate;
import java.util.Optional;

public class BookTicketService {
    private Database database;

    public BookTicketService(Database database) {
        this.database = database;
    }

    public void borrowBook() {
        System.out.println("Create a book borrowing ticket:");

        int readerId = InputUtils.inputInt("Enter reader ID: ");
        Optional<Reader> readerOptional = database.readers.stream().filter(reader -> reader.getId() == readerId).findFirst();
        if (!readerOptional.isPresent()) {
            System.out.println("No reader found with ID: " + readerId);
            return;
        }
        Reader reader = readerOptional.get();

        int bookId = InputUtils.inputInt("Enter book ID to borrow: ");
        Optional<Book> bookOptional = database.books.stream().filter(book -> book.getId() == bookId).findFirst();
        if (!bookOptional.isPresent()) {
            System.out.println("No book found with ID: " + bookId);
            return;
        }
        Book book = bookOptional.get();

        if (book.getQuantity() <= 0) {
            System.out.println("No books available. Cannot borrow.");
            return;
        }

        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusWeeks(2);

        BookTicket bookTicket = new BookTicket(reader, book, borrowDate, dueDate);
        database.bookTickets.add(bookTicket);

        book.setQuantity(book.getQuantity() - 1);

        System.out.println("Book borrowing ticket has been created: " + bookTicket);
    }

    public void returnBook() {
        System.out.println("Return book:");

        int readerId = InputUtils.inputInt("Enter reader ID: ");
        Optional<Reader> readerOptional = database.readers.stream().filter(reader -> reader.getId() == readerId).findFirst();
        if (!readerOptional.isPresent()) {
            System.out.println("No reader found with ID: " + readerId);
            return;
        }
        Reader reader = readerOptional.get();

        int bookId = InputUtils.inputInt("Enter book ID to return: ");
        Optional<BookTicket> ticketOptional = database.bookTickets.stream()
                .filter(ticket -> ticket.getReader().getId() == readerId && ticket.getBook().getId() == bookId)
                .findFirst();

        if (!ticketOptional.isPresent()) {
            System.out.println("No borrowing ticket found for book with ID: " + bookId + " for this reader.");
            return;
        }

        BookTicket bookTicket = ticketOptional.get();
        Book book = bookTicket.getBook();

        book.setQuantity(book.getQuantity() + 1);

        database.bookTickets.remove(bookTicket);

        System.out.println("Book has been successfully returned: " + book);
    }

    public void displayAllBookTickets() {
        if (database.bookTickets.isEmpty()) {
            System.out.println("No borrowing tickets found.");
        } else {
            System.out.println("List of borrowing tickets:");
            for (BookTicket ticket : database.bookTickets) {
                System.out.println(ticket);
            }
        }
    }
}
