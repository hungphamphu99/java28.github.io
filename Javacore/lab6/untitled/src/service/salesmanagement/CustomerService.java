package service.salesmanagement;

import entities.salesmanagement.*;
import data.ShopData;
import utils.Enum;
import utils.Enum.statusOder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CustomerService {
    Scanner scanner = new Scanner(System.in);
    ProductService productService = new ProductService();
    CartService cartService = new CartService();


    public Customer findCustomerById(int id) {
        for (Customer customer : ShopData.getCustomers()) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public void displayCustomer() {
        System.out.println("Enter Customer ID:");
        int id = Integer.parseInt(scanner.nextLine());
        Customer customer = findCustomerById(id);
        if (customer == null) {
            System.out.println("Customer Not Found");
        } else {
            System.out.println("Customer Found:\n" + customer);
        }
    }

    public void addProductToCartByCustomer(Customer customer) {
        System.out.println("Enter Product ID to add to cart:");
        int productId = scanner.nextInt();
        Product product = productService.findProduct(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();

        boolean success = cartService.addProductToCart(customer, product, quantity);
        if (success) {
            System.out.println("Product " + product.getName() + " added to cart.");
        } else {
            System.out.println("Failed to add product to cart.");
        }
    }

    public void showProductsAndBuyImmediately(Customer customer) {
        List<Product> products = ShopData.getProducts();

        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        // Filter and display only products that are In_Stock
        System.out.println("Available Products In Stock:");
        boolean foundInStock = false;
        for (Product product : products) {
            if (product.getStatus() == Enum.statusProduct.In_Stock) {
                System.out.println("Product ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());
                foundInStock = true;
            }
        }

        if (!foundInStock) {
            System.out.println("No products are currently in stock.");
            return;
        }

        System.out.println("Enter Product ID to buy immediately:");
        int productId = Integer.parseInt(scanner.nextLine());
        Product product = productService.findProduct(productId);

        if (product == null || product.getStatus() != Enum.statusProduct.In_Stock) {
            System.out.println("Product not found or is out of stock.");
            return;
        }

        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();

        if (product.getQuantity() >= quantity) {
            double totalCost = product.getPrice() * quantity;

            if (totalCost > customer.getBalance()) {
                System.out.println("Insufficient balance to complete the purchase.");
                return;
            }

            // Deduct balance and create an order
            customer.setBalance(customer.getBalance() - totalCost);
            Orders order = new Orders(customer, Map.of(product, quantity), statusOder.Pending, totalCost);
            customer.addOrder(order);

            // Adjust stock for the product
            product.setQuantity(product.getQuantity() - quantity);

            System.out.println("Purchase successful. Ordered " + quantity + " " + product.getName() + " for " + totalCost + ".");
        } else {
            System.out.println("Not enough stock for product: " + product.getName());
        }
    }


    public void openCartToBuyByCustomer(Customer customer) {
        Cart cart = customer.getCart();  // Get the customer's cart
        cart.showCart();  // Display the cart contents

        if (cart.getProducts().isEmpty()) {
            System.out.println("Your cart is empty. Add products to your cart first.");
            return;
        }

        System.out.println("Select a product to purchase by its number (0 to cancel):");
        int i = 1;
        Map<Product, Integer> products = cart.getProducts();

        // Display the products in the cart
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            // Check if the product is either Deleted or Out of Stock
            if (product.getStatus() == Enum.statusProduct.Deleted) {
                System.out.println(i + ". " + product.getName() + " - This product has been deleted and cannot be purchased.");
            } else if (product.getStatus() == Enum.statusProduct.Out_Stock) {
                System.out.println(i + ". " + product.getName() + " - This product is out of stock and cannot be purchased.");
            } else {
                System.out.println(i + ". " + product.getName() + " - Quantity: " + quantity + " - Price: " + product.getPrice());
                i++;
            }
        }

        int choice = scanner.nextInt();

        if (choice == 0) {
            System.out.println("Purchase cancelled.");
            return;
        }

        // Validate the selected product
        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        // Get the selected product
        Product selectedProduct = (Product) products.keySet().toArray()[choice - 1];

        // Check if the product is deleted or out of stock
        if (selectedProduct.getStatus() == Enum.statusProduct.Deleted) {
            System.out.println("The selected product has been deleted and cannot be purchased.");
            return;
        } else if (selectedProduct.getStatus() == Enum.statusProduct.Out_Stock) {
            System.out.println("The selected product is out of stock and cannot be purchased.");
            return;
        }

        int selectedQuantity = products.get(selectedProduct);
        double totalCost = selectedProduct.getPrice() * selectedQuantity;

        System.out.println("Proceed to purchase " + selectedProduct.getName() + "? (yes/no)");
        String confirm = scanner.next();

        if (confirm.equalsIgnoreCase("yes")) {
            // Check if the customer has sufficient balance
            if (totalCost > customer.getBalance()) {
                System.out.println("Insufficient balance to complete the purchase.");
                return;
            }

            customer.setBalance(customer.getBalance() - totalCost);
            Orders order = new Orders(customer, Map.of(selectedProduct, selectedQuantity), statusOder.Pending, totalCost);
            customer.addOrder(order);

            selectedProduct.setQuantity(selectedProduct.getQuantity() - selectedQuantity);

            cart.removeProduct(selectedProduct);  // Remove the product after purchase
            System.out.println("Purchase successful. Total cost: " + totalCost);
        } else {
            System.out.println("Purchase cancelled.");
        }
    }




    public void removeProductFromCartByCustomer(Customer customer) {
        Cart cart = customer.getCart();  // Get the customer's cart
        cart.showCart();

        if (cart.getProducts().isEmpty()) {
            System.out.println("Your cart is empty. There are no products to remove.");
            return;
        }

        // Ask the customer to select a product to remove
        System.out.println("Enter Product ID to remove from cart:");
        int productId = scanner.nextInt();
        Product product = productService.findProduct(productId);

        if (product == null || !cart.getProducts().containsKey(product)) {
            System.out.println("Product not found in the cart.");
            return;
        }

        // Remove the product from the cart
        boolean success = cart.removeProduct(product);
        if (success) {
            System.out.println("Product " + product.getName() + " removed from the cart.");
        } else {
            System.out.println("Failed to remove product from cart.");
        }
    }

    public void editProductQuantityInCart(Customer customer) {
        Cart cart = customer.getCart();
        cart.showCart();

        if (cart.getProducts().isEmpty()) {
            System.out.println("Your cart is empty. There are no products to edit.");
            return;
        }

        System.out.println("Enter Product ID to edit in cart:");
        int productId = scanner.nextInt();

        Product productInCart = null;
        for (Product product : cart.getProducts().keySet()) {
            if (product.getId() == productId) {
                productInCart = product;
                break;
            }
        }

        if (productInCart == null) {
            System.out.println("Product not found in the cart.");
            return;
        }

        if (productInCart.getStatus() == Enum.statusProduct.Deleted) {
            System.out.println("The product " + productInCart.getName() + " has been deleted and cannot be edited.");
            return;
        } else if (productInCart.getStatus() == Enum.statusProduct.Out_Stock) {
            System.out.println("The product " + productInCart.getName() + " is out of stock and cannot be edited.");
            return;
        }

        System.out.println("Enter the new quantity:");
        int newQuantity = scanner.nextInt();

        if (newQuantity <= 0) {
            System.out.println("Invalid quantity. It should be greater than 0.");
            return;
        }

        if (newQuantity > productInCart.getQuantity()) {
            System.out.println("Not enough stock for product: " + productInCart.getName());
            return;
        }

        cart.getProducts().put(productInCart, newQuantity);
        System.out.println("Updated the quantity of " + productInCart.getName() + " to " + newQuantity + " in your cart.");

        System.out.println("Do you want to proceed to payment? (yes/no)");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("yes")) {
            openCartToBuyByCustomer(customer);
        } else {
            System.out.println("You can review your cart later.");
        }
    }


    public void buyAllProductsToCart(Customer customer) {
        Cart cart = customer.getCart();
        cart.showCart();

        if (cart.getProducts().isEmpty()) {
            System.out.println("Your cart is empty. There are no products to buy.");
            return;
        }

        double totalCost = 0.0;

        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.getStatus() == Enum.statusProduct.Deleted) {
                System.out.println("Product " + product.getName() + " has been deleted and cannot be purchased.");
                return;
            } else if (product.getStatus() == Enum.statusProduct.Out_Stock) {
                System.out.println("Product " + product.getName() + " is out of stock and cannot be purchased.");
                return;
            }

            if (product.getQuantity() < quantity) {
                System.out.println("Not enough stock for product: " + product.getName());
                return;
            }

            totalCost += product.getPrice() * quantity;
        }

        if (totalCost > customer.getBalance()) {
            System.out.println("Insufficient balance to complete the purchase. Total cost is: " + totalCost);
            return;
        }

        System.out.println("The total cost is: " + totalCost + ". Do you want to proceed with the purchase? (yes/no)");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("yes")) {
            customer.setBalance(customer.getBalance() - totalCost);

            Orders order = new Orders(customer, cart.getProducts(), statusOder.Pending, totalCost);
            customer.addOrder(order);

            for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();

                product.setQuantity(product.getQuantity() - quantity);
            }

            cart.clearCart();
            System.out.println("Purchase successful! Total cost: " + totalCost);
        } else {
            System.out.println("Purchase cancelled.");
        }
    }


    public void depositByCustomer(Customer customer) {
        System.out.print("Enter the amount you want to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            customer.deposit(amount);
            System.out.println("You have deposited " + amount + " into your account. Current balance: " + customer.getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdrawByCustomer(Customer customer) {
        System.out.print("Enter the amount you want to withdraw: ");
        double amount = scanner.nextDouble();
        if (customer.withdraw(amount)) {
            System.out.println("You have withdrawn " + amount + " from your account. Remaining balance: " + customer.getBalance());
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }


    public void cancelOrderByCustomer(Customer customer) {
        List<Orders> orders = customer.getOrders();

        if (orders.isEmpty()) {
            System.out.println("You have no orders to cancel.");
            return;
        }

        System.out.println("Select a pending order to cancel (0 to exit):");
        int i = 1;
        List<Orders> pendingOrders = new ArrayList<>();
        for (Orders order : orders) {
            if (order.getStatus() == statusOder.Pending) {
                System.out.println(i + ". Order ID: " + order.getId() + ", Total Cost: " + order.getTotal() + ", Status: " + order.getStatus());
                pendingOrders.add(order);
                i++;
            }
        }

        if (pendingOrders.isEmpty()) {
            System.out.println("No pending orders available to cancel.");
            return;
        }

        int choice = scanner.nextInt();
        if (choice == 0) {
            System.out.println("Cancelation aborted.");
            return;
        }

        if (choice < 1 || choice > pendingOrders.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Orders selectedOrder = pendingOrders.get(choice - 1);

        customer.setBalance(customer.getBalance() + selectedOrder.getTotal());

        for (Map.Entry<Product, Integer> entry : selectedOrder.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.setQuantity(product.getQuantity() + quantity);
        }

        selectedOrder.setStatus(statusOder.Canceled);

        System.out.println("Order canceled successfully. Refunded amount: " + selectedOrder.getTotal());
    }



    public void updateInformationCustomer() {
        System.out.print("Enter customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        Customer customer = findCustomerById(customerId);

        if (customer == null) {
            System.out.println("customer with ID " + customerId + " not found.");
            return;
        }

        System.out.println("Current information: ");
        System.out.println(customer);

        System.out.println("Press Enter without typing anything to keep the current information.");

        // Update Name
        System.out.print("Enter new name (" + customer.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            customer.setName(newName);
            System.out.println("Name  updated successfully.");
        }

        // Update Email
        System.out.print("Enter new email (" + customer.getEmail() + "): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            customer.setEmail(newEmail);
            System.out.println("Email updated successfully.");
        }

        // Update Address
        System.out.print("Enter new address (" + customer.getAddress() + "): ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            customer.setAddress(newAddress);
            System.out.println("Address updated successfully.");
        }

        // Update Phone
        System.out.print("Enter new phone number (" + customer.getPhone() + "): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            customer.setPhone(newPhone);
            System.out.println("Phone updated successfully.");
        }

        System.out.println("Updated information: ");
        System.out.println(customer);
    }
}
