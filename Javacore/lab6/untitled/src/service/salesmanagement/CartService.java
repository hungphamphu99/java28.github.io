package service.salesmanagement;

import entities.salesmanagement.*;
import utils.Enum;
import utils.Enum.statusOder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class CartService {
    Scanner scanner = new Scanner(System.in);

    // Add product to the customer's cart
    public boolean addProductToCart(Customer customer, Product product, int quantity) {
        Cart cart = customer.getCart();
        boolean success = cart.addProduct(product, quantity);
        if (success) {
            System.out.println("\n");
        }
        return success;
    }

    public boolean removeProductFromCart(Customer customer, Product product) {
        Cart cart = customer.getCart();
        return cart.removeProduct(product);
    }

    // Checkout the customer's cart and create an order
    public Order checkout(Customer customer) {
        Cart cart = customer.getCart();
        Map<Product, Integer> products = cart.getProducts();

        // Check if the cart is empty
        if (products.isEmpty()) {
            System.out.println("Cart is empty, cannot proceed with checkout.");
            return null;
        }

        // Get the total cost as BigDecimal for better precision
        BigDecimal totalCost = cart.getTotalCost().setScale(2, RoundingMode.HALF_UP);

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
            try {
                System.out.println("Choose payment method: \n1. E-Wallet \n2. Cash on Delivery");
                int paymentChoice = Integer.parseInt(scanner.nextLine().trim());

                if (paymentChoice == 1) {
                    // E-Wallet payment
                    if (customer.getBalance().compareTo(totalCost) >= 0) {
                        paymentStrategy = new EWalletPayment();
                        customer.pay(totalCost, paymentStrategy); // Pass BigDecimal directly
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
                    System.out.println("Payment will be completed upon delivery.");
                } else {
                    System.out.println("Invalid payment method. Please choose again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number for payment method.");
            }
        }

        if (paymentSuccess) {
            // Create an order after successful payment
            Order order = new Order(customer, new HashMap<>(products), statusOder.Pending, totalCost, paymentStrategy);
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

    public void removeUnavailableProductsFromCart(Customer customer) {
        Map<Product, Integer> products = customer.getCart().getProducts();

        // Iterate through the cart and remove products that are marked as Deleted or Out of Stock
        Iterator<Map.Entry<Product, Integer>> iterator = products.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Product, Integer> entry = iterator.next();
            Product product = entry.getKey();

            if (product.getStatus() == Enum.statusProduct.Deleted) {
                System.out.println("Product " + product.getName() + " has been deleted and will be removed from your cart.");
                iterator.remove(); // Remove the product from the cart
            } else if (product.getStatus() == Enum.statusProduct.Out_Stock) {
                System.out.println("Product " + product.getName() + " is out of stock and will be removed from your cart.");
                iterator.remove(); // Remove the product from the cart
            }
        }
    }


}
