package entities.salesmanagement;

import data.ShopData;
import utils.Enum.statusOder;
import service.salesmanagement.PaymentStrategy;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private int id;
    private static int nextId = 0;
    private LocalDateTime orderTime;
    private statusOder status;
    private double total;
    private Map<Product, Integer> products; // Product, Quantity
    private Customer customer;
    private PaymentStrategy paymentMethod; // New field for payment method

    // Constructor updated to include payment method
    public Order(Customer customer, Map<Product, Integer> products, statusOder status, double total, PaymentStrategy paymentMethod) {
        this.id = ++nextId;
        this.customer = customer;
        this.orderTime = LocalDateTime.now();
        this.status = status;
        this.products = new HashMap<>(products);
        this.total = total;
        this.paymentMethod = paymentMethod; // Initialize payment method
        ShopData.orders.add(this);
    }

    public double getTotal() {
        return total;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public statusOder getStatus() {
        return status;
    }

    public void setStatus(statusOder status) {
        this.status = status;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public PaymentStrategy getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String toString() {
        StringBuilder productDetails = new StringBuilder();
        int totalQuantity = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productDetails.append("Product ID: ").append(entry.getKey().getId())
                    .append(", Product Name: ").append(entry.getKey().getName())
                    .append(", Quantity: ").append(entry.getValue()).append("; ");
            totalQuantity += entry.getValue();
        }
        return "Order [ID: " + id + ", Order Time: " + orderTime + ", Status: " + status +
                ", Total Quantity: " + totalQuantity + ", Total Cost: " + total + "]\n" +
                "Products: " + productDetails + "\n"+
                "Customer: " + customer.getName() + ", email: " + customer.getAddress() + ", Phone: " + customer.getPhone() + "\n" +
                "Payment Method: " + paymentMethod.getClass().getSimpleName() + "\n";  // Include payment method
    }
}
