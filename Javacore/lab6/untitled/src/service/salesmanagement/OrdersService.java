package service.salesmanagement;

import entities.salesmanagement.Customer;
import entities.salesmanagement.Order;
import entities.salesmanagement.Product;
import data.ShopData;
import utils.Enum;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class OrdersService {
    Scanner scanner = new Scanner(System.in);
    public Order findOrderById(int id){
        for(Order oder : ShopData.orders){
            if(oder.getId() == id){
                return oder;

            }

        }
        return null;
    }
    public void displayOrdersById(){
        System.out.println("Enter the id of the order you want to search");
        int id = Integer.parseInt(scanner.nextLine());
        Order order = findOrderById(id);
        if(order != null){
            System.out.println(order.toString());
        }else {
            System.out.println("Order not found");
        }


    }

    public void cancelOrderById() {
        System.out.println("Enter the id of the order you want to cancel:");
        int orderId = Integer.parseInt(scanner.nextLine());
        Order order = findOrderById(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        // Check if the order is already canceled
        if (order.getStatus() == Enum.statusOder.Canceled) {
            System.out.println("Order is already canceled.");
            return;
        }

        // Allow cancellation only if the order is in Pending or Delivered status
        if (order.getStatus() == Enum.statusOder.Pending || order.getStatus() == Enum.statusOder.Delivered) {
            // Get the customer associated with the order
            Customer customer = order.getCustomer();

            // Refund the customer if the order was paid using an E-Wallet
            if (order.getPaymentMethod() instanceof EWalletPayment) {
                BigDecimal refundAmount = order.getTotal();
                customer.setBalance(customer.getBalance().add(refundAmount));
                System.out.println("Refund of " + refundAmount + " has been issued to the customer's E-Wallet.");
            } else {
                System.out.println("No refund required as the order was not paid using an E-Wallet.");
            }

            // Restore product quantities to the inventory
            for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                product.setQuantity(product.getQuantity() + quantity);
            }

            // Update the order status to Canceled
            order.setStatus(Enum.statusOder.Canceled);
            System.out.println("Order ID: " + orderId + " has been canceled.");
        } else {
            System.out.println("Order cannot be canceled. Only orders in 'Pending' or 'Delivered' status can be canceled.");
        }
    }

    public void deliverPendingOrders() {
        boolean foundPending = false;
        for (Order order : ShopData.orders) {
            if (order.getStatus() == Enum.statusOder.Pending) {
                order.setStatus(Enum.statusOder.Delivered);
                System.out.println("Order ID: " + order.getId() + " has been delivered.");
                foundPending = true;
            }
        }
        if (!foundPending) {
            System.out.println("No pending orders found.");
        }
    }

    public void deliverPendingOrdersById(int orderId) {
        Order order = findOrderById(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        if (order.getStatus() == Enum.statusOder.Pending) {
            order.setStatus(Enum.statusOder.Delivered);
            System.out.println("Order ID: " + orderId + " has been delivered.");
        } else {
            System.out.println("Order ID: " + orderId + " is not pending. Current status: " + order.getStatus());
        }
    }

    public void displayAllOrders() {
        if (ShopData.orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-15s %-15s %-15s %-40s %-30s %-20s\n",
                "ID", "Order Time", "Status", "Quantity", "Total Cost", "Products", "Customer", "Payment Method");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Order order : ShopData.orders) {
            String productDetails = formatProducts(order);
            String customerDetails = formatCustomer(order.getCustomer());
            int totalQuantity = order.getProducts().values().stream().mapToInt(Integer::intValue).sum();

            System.out.printf("%-5d %-20s %-15s %-15d %-15.2f %-40s %-30s %-20s\n",
                    order.getId(),
                    order.getOrderTime(),
                    order.getStatus(),
                    totalQuantity,
                    order.getTotal(),
                    productDetails,
                    customerDetails,
                    order.getPaymentMethod().getClass().getSimpleName());
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    // Helper method to format product details for an order
    private String formatProducts(Order order) {
        StringBuilder productDetails = new StringBuilder();
        for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
            productDetails.append(entry.getKey().getName())
                    .append(" (x").append(entry.getValue()).append("), ");
        }
        if (productDetails.length() > 0) {
            productDetails.setLength(productDetails.length() - 2); // Remove the trailing comma and space
        }
        return productDetails.toString();
    }

    // Helper method to format customer details
    private String formatCustomer(Customer customer) {
        return customer.getName() + ", " + customer.getEmail() + ", " + customer.getPhone();
    }


}
