package entities.salesmanagement;

import data.ShopData;
import entities.login.User;
import service.salesmanagement.PaymentContext;
import service.salesmanagement.PaymentStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private BigDecimal balance;
    private List<Order> orders;
    private Cart cart;

    public Customer(String username, String password, String role, String name, String email, String address, String phone) {
        super(username, password, role, name, email, address, phone);
        this.balance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.orders = new ArrayList<>();
        this.cart = new Cart();
        ShopData.customers.add(this);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Cart getCart() {
        if (this.cart == null) {
            this.cart = new Cart();
        }
        return this.cart;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, RoundingMode.HALF_UP);
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            this.balance = this.balance.add(amount.setScale(2, RoundingMode.HALF_UP));
            System.out.println("You have deposited " + amount + " into your account. Current balance: " + this.balance);
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }
    }

    public boolean withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && this.balance.compareTo(amount) >= 0) {
            this.balance = this.balance.subtract(amount.setScale(2, RoundingMode.HALF_UP));
            System.out.println("You have withdrawn " + amount + " from your account. Remaining balance: " + this.balance);
            return true;
        } else {
            System.out.println("Insufficient balance or invalid amount.");
            return false;
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public boolean payWithEWallet(BigDecimal amount) {
        if (this.balance.compareTo(amount) >= 0) {
            this.balance = this.balance.subtract(amount.setScale(2, RoundingMode.HALF_UP));
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
    public void pay(BigDecimal amount, PaymentStrategy paymentStrategy) {
        PaymentContext paymentContext = new PaymentContext(paymentStrategy);
        paymentContext.executePayment(amount);
    }
}
