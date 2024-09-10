package data;

import entities.salesmanagement.Customer;
import entities.salesmanagement.Orders;
import entities.salesmanagement.Product;
import entities.salesmanagement.Staff;

import java.util.ArrayList;
import java.util.List;

public class ShopData {
    private static  List<Product> products = new ArrayList<>();
    private static  List<Staff> staffs = new ArrayList<>();
    private static  List<Customer> customers = new ArrayList<>();
    private static  List<Orders> orders = new ArrayList<>();

    public ShopData() {

    }

    public static List<Product> getProducts() {
        return products;
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static List<Staff> getStaffs() {
        return staffs;
    }

    public static void addStaff(Staff staff) {
        staffs.add(staff);
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static List<Orders> getOrders() {
        return orders;
    }

    public static void addOrder(Orders order) {
        orders.add(order);
    }

    public static void removeStaff(Staff staff) {
        staffs.remove(staff);
    }
    @Override
    public String toString() {
        return "Shop [Products: " + products.size() + ", Staffs: " + staffs.size() + ", Customers: " + customers.size() + ", Orders: " + orders.size() + "]";
    }
}
