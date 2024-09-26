package ex_2.services;

import ex_2.entities.Reader;
import ex_2.utils.InputUtils;

import java.util.Optional;
import java.util.Scanner;

import static ex_2.data.Database.readers;

public class ReaderService {

    Scanner scanner = new Scanner(System.in);

    public Optional<Reader> findReaderById(int id) {
        return readers.stream().filter(reader -> reader.getId() == id).findFirst();
    }

    public void addReader() {
        String name = InputUtils.inputString("Enter reader's name: ");
        String phoneNumber = InputUtils.inputString("Enter phone number: ");
        String address = InputUtils.inputString("Enter address: ");

        Reader newReader = new Reader(name, phoneNumber, address);
        readers.add(newReader);
        System.out.println("Reader has been added: " + newReader);
    }

    public void removeReader() {
        int id = InputUtils.inputInt("Enter the reader ID to remove: ");

        Optional<Reader> readerToRemove = findReaderById(id);

        if (readerToRemove.isPresent()) {
            readers.remove(readerToRemove.get());
            System.out.println("Reader has been removed: " + readerToRemove.get());
        } else {
            System.out.println("No reader found with ID: " + id);
        }
    }

    public void updateReader() {
        int id = InputUtils.inputInt("Enter the reader ID to update: ");

        Optional<Reader> readerToUpdate = findReaderById(id);

        if (readerToUpdate.isPresent()) {
            Reader reader = readerToUpdate.get();

            System.out.println("Current reader information: " + reader);

            String newName = InputUtils.inputString("New name (leave empty if no change): ");
            if (!newName.isEmpty()) {
                reader.setName(newName);
            }

            String newPhoneNumber = InputUtils.inputString("New phone number (leave empty if no change): ");
            if (!newPhoneNumber.isEmpty()) {
                reader.setPhoneNumber(newPhoneNumber);
            }

            String newAddress = InputUtils.inputString("New address (leave empty if no change): ");
            if (!newAddress.isEmpty()) {
                reader.setAddress(newAddress);
            }

            System.out.println("Reader information has been updated: " + reader);
        } else {
            System.out.println("No reader found with ID: " + id);
        }
    }

    public void displayAllReaders() {
        if (readers.isEmpty()) {
            System.out.println("No readers found.");
        } else {
            System.out.println("List of readers:");
            for (Reader reader : readers) {
                System.out.println(reader);
            }
        }
    }

}
