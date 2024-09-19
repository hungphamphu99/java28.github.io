package service.salesmanagement;

import entities.salesmanagement.Product;
import data.ShopData;
import utils.Enum;

import java.util.Scanner;

public class ProductService {

    Scanner scanner = new Scanner(System.in);
    public Product findProduct(int id){
        for (Product product : ShopData.getProducts()){
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }
    public void displayProduct(int id){
        Product product = findProduct(id);
        if (product != null){
            System.out.println("Product Name: " + product);
        }else {
            System.out.println("Product ID not found");
            return;
        }
    }
    public void displayAllProducts(){
        for (Product product : ShopData.getProducts()){
            System.out.println(product +"\n");
        }
    }
    public void addProduct() {
        String name = "";
        double price = -1;
        int quantity = -1;
        String description;

        while (name.isEmpty()) {
            System.out.println("Enter product name:");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Product name cannot be empty. Please try again.");
            }
        }

        while (price <= 0) {
            System.out.println("Enter product price:");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {
                    System.out.println("Price must be greater than zero. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for price.");
            }
        }

        while (quantity < 0) {
            System.out.println("Enter product quantity:");
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0) {
                    System.out.println("Quantity cannot be negative. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for quantity.");
            }
        }

        System.out.println("Enter product description:");
        description = scanner.nextLine();

        Enum.statusProduct status = (quantity > 0) ? Enum.statusProduct.In_Stock : Enum.statusProduct.Out_Stock;

        Product product = new Product(name, status, quantity, price, description);
        System.out.println("Product added successfully: " + product);
    }

    public void updateProduct() {
        System.out.println("Enter Product ID to update:");
        int id = Integer.parseInt(scanner.nextLine());

        Product product = findProduct(id);

        if (product != null) {
            System.out.println("Updating product: " + product.getName());
            System.out.println("Enter new product name (or press Enter to keep it the same):");
            String newName = scanner.nextLine();
            if (!newName.trim().isEmpty()) {
                product.setName(newName);
            }

            System.out.println("Enter new product price (or press Enter to keep it the same):");
            String priceInput = scanner.nextLine();
            if (!priceInput.trim().isEmpty()) {
                try {
                    double newPrice = Double.parseDouble(priceInput);
                    if (newPrice > 0) {
                        product.setPrice(newPrice);
                    } else {
                        System.out.println("Price must be positive.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price. Keeping the old price.");
                }
            }

            System.out.println("Enter new product quantity (or press Enter to keep it the same):");
            String quantityInput = scanner.nextLine();
            if (!quantityInput.trim().isEmpty()) {
                try {
                    int newQuantity = Integer.parseInt(quantityInput);
                    if (newQuantity >= 0) {
                        product.setQuantity(newQuantity);
                        product.setStatus(newQuantity > 0 ? Enum.statusProduct.In_Stock : Enum.statusProduct.Out_Stock);
                    } else {
                        System.out.println("Quantity must be non-negative.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity. Keeping the old quantity.");
                }
            }

            System.out.println("Enter new product description (or press Enter to keep it the same):");
            String newDescription = scanner.nextLine();
            if (!newDescription.trim().isEmpty()) {
                product.setDescription(newDescription);
            }

            System.out.println("Product updated successfully: " + product);

        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    public void deleteProductById() {
        System.out.println("Enter Product ID to delete:");
        int id = Integer.parseInt(scanner.nextLine());

        Product product = findProduct(id);

        if (product != null) {
            System.out.println("Are you sure you want to delete the product: " + product.getName() + "? (yes/no)");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                product.setStatus(Enum.statusProduct.Deleted);
                System.out.println("Product with ID " + id + " marked as Deleted.");
            } else {
                System.out.println("Product deletion canceled.");
            }
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    public void displayInStockProducts() {
        System.out.println("Products In Stock:");
        boolean found = false;
        for (Product product : ShopData.getProducts()) {
            if (product.getStatus() == Enum.statusProduct.In_Stock) {
                System.out.println(product + "\n");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products are currently in stock.");
        }
    }


}
