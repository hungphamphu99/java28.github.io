package ex_2.views;

import ex_2.data.Database;
import ex_2.services.BookService;
import ex_2.services.ReaderService;
import ex_2.services.BookTicketService;
import ex_2.utils.InputUtils;

public class Menu {
    private BookService bookService;
    private ReaderService readerService;
    private BookTicketService bookTicketService;

    public Menu() {
        this.bookService = new BookService();
        this.readerService = new ReaderService();
        Database database = new Database();
        this.bookTicketService = new BookTicketService(database);
    }

    public void showMenu() {
        while (true) {
            System.out.println("===== MAIN MENU =====");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Readers");
            System.out.println("3. Manage Borrow/Return Books");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            int choice = InputUtils.inputInt("Your choice: ");

            switch (choice) {
                case 1:
                    showBookMenu();
                    break;
                case 2:
                    showReaderMenu();
                    break;
                case 3:
                    showBorrowReturnMenu();
                    break;
                case 0:
                    System.out.println("Program terminated.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showBookMenu() {
        while (true) {
            System.out.println("===== BOOK MANAGEMENT =====");
            System.out.println("1. Add a Book");
            System.out.println("2. Remove a Book");
            System.out.println("3. Update Book Information");
            System.out.println("4. Display All Books");
            System.out.println("0. Go Back");
            System.out.print("Select an option: ");

            int choice = InputUtils.inputInt("Your choice: ");

            switch (choice) {
                case 1:
                    bookService.addBook();
                    break;
                case 2:
                    bookService.removeBook();
                    break;
                case 3:
                    bookService.updateBook();
                    break;
                case 4:
                    bookService.displayAllBooks();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showReaderMenu() {
        while (true) {
            System.out.println("===== READER MANAGEMENT =====");
            System.out.println("1. Add a Reader");
            System.out.println("2. Remove a Reader");
            System.out.println("3. Update Reader Information");
            System.out.println("4. Display All Readers");
            System.out.println("0. Go Back");
            System.out.print("Select an option: ");

            int choice = InputUtils.inputInt("Your choice: ");

            switch (choice) {
                case 1:
                    readerService.addReader();
                    break;
                case 2:
                    readerService.removeReader();
                    break;
                case 3:
                    readerService.updateReader();
                    break;
                case 4:
                    readerService.displayAllReaders();
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showBorrowReturnMenu() {
        while (true) {
            System.out.println("===== BORROW/RETURN MANAGEMENT =====");
            System.out.println("1. Create a Borrow Ticket");
            System.out.println("2. Return a Book");
            System.out.println("3. Display All Borrow Tickets");
            System.out.println("0. Go Back");
            System.out.print("Select an option: ");

            int choice = InputUtils.inputInt("Your choice: ");

            switch (choice) {
                case 1:
                    bookTicketService.borrowBook();
                    break;
                case 2:
                    bookTicketService.returnBook();
                    break;
                case 3:
                    bookTicketService.displayAllBookTickets();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
