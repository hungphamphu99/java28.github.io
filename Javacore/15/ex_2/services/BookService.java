package ex_2.services;

import ex_2.data.Database;
import ex_2.entities.Book;
import ex_2.utils.InputUtils;

import java.util.Optional;
import java.util.Scanner;
import static ex_2.data.Database.books;

public class BookService {

    Scanner scanner = new Scanner(System.in);

    public void addBook() {
        System.out.println("Enter new book information:");
        String name = InputUtils.inputString("Enter book name: ");
        String category = InputUtils.inputString("Enter category: ");
        String author = InputUtils.inputString("Enter author: ");
        int quantity = InputUtils.inputIntInRange("Enter quantity (0 - 100): ", 0, 100);
        Book newBook = new Book(name, category, author, quantity);
        books.add(newBook);
        System.out.println("Book has been added: " + newBook);
    }

    public Optional<Book> findBookById(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst();
    }

    public void removeBook() {
        int id = InputUtils.inputInt("Enter the book ID to remove: ");

        Optional<Book> bookToRemove = findBookById(id);

        if (bookToRemove.isPresent()) {
            books.remove(bookToRemove.get());
            System.out.println("Book has been removed: " + bookToRemove.get());
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    public void updateBook() {
        int id = InputUtils.inputInt("Enter the book ID to update: ");

        Optional<Book> bookToUpdate = findBookById(id);

        if (bookToUpdate.isPresent()) {
            Book book = bookToUpdate.get();

            System.out.println("Current book information: " + book);

            String newName = InputUtils.inputString("New book name (leave empty if no change): ");
            if (!newName.isEmpty()) {
                book.setName(newName);
            }

            String newCategory = InputUtils.inputString("New category (leave empty if no change): ");
            if (!newCategory.isEmpty()) {
                book.setCategory(newCategory);
            }

            String newAuthor = InputUtils.inputString("New author (leave empty if no change): ");
            if (!newAuthor.isEmpty()) {
                book.setAuthor(newAuthor);
            }

            int newQuantity = InputUtils.inputIntInRange("New quantity (0 - 100, enter -1 if no change): ", -1, 100);
            if (newQuantity != -1) {
                book.setQuantity(newQuantity);
            }

            System.out.println("Book information has been updated: " + book);
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the system.");
        } else {
            System.out.println("List of all books:");
            books.forEach(book -> System.out.println(book));
        }
    }
}
