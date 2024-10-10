package service.salesmanagement;

import entities.salesmanagement.Customer;
import entities.salesmanagement.Order;
import entities.salesmanagement.Product;
import data.ShopData;
import service.Edit;
import utils.Enum;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class ProductService implements Edit<Product> {

    Scanner scanner = new Scanner(System.in);

    public void displayProduct(int id){
        Product product = findById(id);
        if (product != null){
            System.out.println("Product Name: " + product);
        }else {
            System.out.println("Product ID not found");
        }
    }
    public boolean displayProduct_2(int id) {
        Product product = findById(id);
        if (product != null) {
            // Check if the product's status is In_Stock before displaying
            if (product.getStatus() == Enum.statusProduct.In_Stock) {
                System.out.println("Product Name: " + product);
                return true;
            } else {
                System.out.println("Product ID not found.");
                return false;
            }
        } else {
            System.out.println("Product ID not found.");
            return false;
        }
    }



    public void displayInStockProducts() {
        System.out.println("Products In Stock:");
        boolean found = false;
        for (Product product : ShopData.products) {
            if (product.getStatus() == Enum.statusProduct.In_Stock) {
                System.out.println(product + "\n");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products are currently in stock.");
        }
    }
    private void cancelOrderWithRefund(Order order) {
        Customer customer = order.getCustomer();

        // Check if the order was paid using an E-Wallet
        if (order.getPaymentMethod() instanceof EWalletPayment) {
            // Refund the amount to the customer's balance
            BigDecimal refundAmount = order.getTotal();
            customer.setBalance(customer.getBalance().add(refundAmount));
            System.out.println("Refund of " + refundAmount + " issued to customer " + customer.getName() + "'s E-Wallet.");
        }

        // Restore product quantities to the inventory
        for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.setQuantity(product.getQuantity() + quantity);
        }

        // Update the order status to Cancel
        order.setStatus(Enum.statusOder.Canceled);
        System.out.println("Order ID: " + order.getId() + " containing the deleted product has been canceled.");
    }


    @Override
    public void add() {
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

    @Override
    public void update() {
        System.out.println("Enter Product ID to update:");
        int id = Integer.parseInt(scanner.nextLine());

        Product product = findById(id);

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

    @Override
    public Product findById(int id) {
        for (Product product : ShopData.products){
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }

    @Override
    public void displayAll() {
        if (ShopData.products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-15s %-10s %-10s %-30s\n", "ID", "Name", "Status", "Quantity", "Price", "Description");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for (Product product : ShopData.products) {
            System.out.printf("%-10d %-20s %-15s %-10d %-10.2f %-30s\n",
                    product.getId(),
                    product.getName(),
                    product.getStatus(),
                    product.getQuantity(),
                    product.getPrice(),
                    product.getDescription());
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void delete() {
        System.out.println("Enter Product ID to delete:");
        int id = Integer.parseInt(scanner.nextLine());

        Product product = findById(id);

        if (product != null) {
            System.out.println("Are you sure you want to delete the product: " + product.getName() + "? (yes/no)");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                // Mark the product as Deleted
                product.setStatus(Enum.statusProduct.Deleted);
                System.out.println("Product with ID " + id + " marked as Deleted.");

                // Check all orders and cancel those that contain the deleted product
                for (Order order : ShopData.orders) {
                    // Check if the order is Pending and contains the deleted product
                    if (order.getStatus() == Enum.statusOder.Pending && order.getProducts().containsKey(product)) {
                        cancelOrderWithRefund(order);
                    }
                }

            } else {
                System.out.println("Product deletion canceled.");
            }
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }
}
