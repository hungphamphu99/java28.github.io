package service.salesmanagement;

import entities.salesmanagement.*;
import utils.Enum;
import utils.Enum.statusOder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CartService {
    Scanner scanner = new Scanner(System.in);

    // Add product to the customer's cart
    public boolean addProductToCart(Customer customer, Product product, int quantity) {
        Cart cart = customer.getCart();
        boolean success = cart.addProduct(product, quantity);
        if (success) {
            System.out.println(quantity + " " + product.getName() + " added to the cart.");
        }
        return success;
    }

    public boolean removeProductFromCart(Customer customer, Product product) {
        Cart cart = customer.getCart();
        return cart.removeProduct(product);
    }

    // Checkout the customer's cart and create an order
    public Orders checkout(Customer customer) {
        Cart cart = customer.getCart();
        Map<Product, Integer> products = cart.getProducts();

        // Check if the cart is empty
        if (products.isEmpty()) {
            System.out.println("Cart is empty, cannot proceed with checkout.");
            return null;
        }

        double totalCost = cart.getTotalCost();

        // Validate product availability before proceeding
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.getStatus() == Enum.statusProduct.Deleted) {
                System.out.println("Product " + product.getName() + " has been deleted and cannot be purchased.");
                return null;
            } else if (product.getStatus() == Enum.statusProduct.Out_Stock) {
                System.out.println("Product " + product.getName() + " is out of stock and cannot be purchased.");
                return null;
            } else if (product.getQuantity() < quantity) {
                System.out.println("Not enough stock for product: " + product.getName());
                return null;
            }
        }

        // Choose a payment method
        PaymentStrategy paymentStrategy = null;
        boolean validPaymentMethod = false;
        boolean paymentSuccess = false;

        while (!validPaymentMethod) {
            System.out.println("Choose payment method: \n1. E-Wallet \n2. Cash on Delivery");
            int paymentChoice = Integer.parseInt(scanner.nextLine());

            if (paymentChoice == 1) {
                // E-Wallet payment
                if (customer.getBalance() >= totalCost) {
                    paymentStrategy = new EWalletPayment();
                    customer.pay(totalCost, paymentStrategy);
                    customer.setBalance(customer.getBalance() - totalCost); // Deduct balance from e-wallet
                    paymentSuccess = true;
                    validPaymentMethod = true;
                    System.out.println("Payment successful via E-Wallet. Remaining balance: " + customer.getBalance());
                } else {
                    System.out.println("Insufficient balance to complete the purchase via E-Wallet.");
                    validPaymentMethod = true; // Exit loop since they can't pay via E-Wallet
                }
            } else if (paymentChoice == 2) {
                // Cash on Delivery payment
                paymentStrategy = new CashOnDeliveryPayment();
                customer.pay(totalCost, paymentStrategy);
                paymentSuccess = true;
                validPaymentMethod = true;
            } else {
                System.out.println("Invalid payment method. Please choose again.");
            }
        }

        if (paymentSuccess) {
            // Create an order after successful payment
            Orders order = new Orders(customer, new HashMap<>(products), statusOder.Pending, totalCost, paymentStrategy);
            customer.addOrder(order);

            // Reduce the product's quantity after purchase
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                product.setQuantity(product.getQuantity() - quantity); // Reduce stock
            }

            // Clear the cart after purchase
            cart.clearCart();
            System.out.println("Order placed successfully. Total cost: " + totalCost);
            return order;
        } else {
            System.out.println("Order placement failed.");
            return null;
        }
    }


    // View the customer's cart
    public void viewCart(Customer customer) {
        Cart cart = customer.getCart();
        customer.getCart().showCart();

        if (cart.getProducts().isEmpty()) {
            System.out.println("Cart is empty. ");
        }
    }


}
