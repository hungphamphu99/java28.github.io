package entities.salesmanagement;

import data.ShopData;
import entities.login.User;
import service.salesmanagement.PaymentContext;
import service.salesmanagement.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private double balance;
    private List<Orders> orders;
    private Cart cart;

    public Customer(String username, String password, String role, String name, String email, String address, String phone) {
        super(username, password, role, name, email, address, phone);
        this.balance = 0.0;
        this.orders = new ArrayList<>();
        this.cart = new Cart();
        ShopData.customers.add(this);
    }

    public double getBalance() {
        return balance;
    }

    public Cart getCart() {
        if (this.cart == null) {
            this.cart = new Cart();
        }
        return this.cart;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void addOrder(Orders order) {
        this.orders.add(order);
    }
    public boolean payWithEWallet(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.println("Payment successful. Remaining balance: " + this.balance);
            return true;
        } else {
            System.out.println("Insufficient balance.");
            return false;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Balance: " + balance + ", Orders: " + orders.size();
    }

    // Method to handle payment with a specific strategy
    public void pay(double amount, PaymentStrategy paymentStrategy) {
        PaymentContext paymentContext = new PaymentContext(paymentStrategy);
        paymentContext.executePayment(amount);
    }
}
