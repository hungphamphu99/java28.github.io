package service.salesmanagement;

import entities.salesmanagement.Customer;
import entities.salesmanagement.Orders;
import entities.salesmanagement.Product;
import data.ShopData;
import utils.Enum;

import java.util.Map;
import java.util.Scanner;

public class OrdersService {
    Scanner scanner = new Scanner(System.in);
    public Orders findOrderById(int id){
        for(Orders oder : ShopData.orders){
            if(oder.getId() == id){
                return oder;

            }

        }
        return null;
    }
    public void displayOrdersById(){
        System.out.println("Enter the id of the order you want to search");
        int id = Integer.parseInt(scanner.nextLine());
        Orders order = findOrderById(id);
        if(order != null){
            System.out.println(order.toString());
        }else {
            System.out.println("Order not found");
        }


    }

    public void cancelOrderById() {
        System.out.println("Enter the id of the order you want to cancel");
        int orderId = Integer.parseInt(scanner.nextLine());
        Orders order = findOrderById(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        if (order.getStatus() == Enum.statusOder.Canceled) {
            System.out.println("Order is already canceled.");
            return;
        }

        Customer customer = order.getCustomer();
        customer.setBalance(customer.getBalance() + order.getTotal());

        for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.setQuantity(product.getQuantity() + quantity);
        }

        order.setStatus(Enum.statusOder.Canceled);

        System.out.println("Order ID: " + orderId + " has been canceled and a refund of " + order.getTotal() + " has been issued to the customer.");
    }

    public void deliverPendingOrders() {
        boolean foundPending = false;
        for (Orders order : ShopData.orders) {
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
        Orders order = findOrderById(orderId);

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
}
