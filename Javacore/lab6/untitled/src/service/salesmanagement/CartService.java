package service.salesmanagement;

import entities.salesmanagement.*;
import utils.Enum.statusOder;

public class CartService {

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
        double totalCost = cart.getTotalCost();

        if (totalCost > customer.getBalance()) {
            System.out.println("Insufficient balance to complete the purchase.");
            return null;
        }

        if (cart.getProducts().isEmpty()) {
            System.out.println("Cart is empty, cannot proceed with checkout.");
            return null;
        }

        customer.setBalance(customer.getBalance() - totalCost);
        Orders order = new Orders(customer, cart.getProducts(), statusOder.Pending, totalCost);
        customer.addOrder(order);

        cart.clearCart();
        System.out.println("Order placed successfully.");
        return order;
    }

    // View the customer's cart
    public void viewCart(Customer customer) {
        customer.getCart().showCart();
    }
}
