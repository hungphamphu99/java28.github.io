package data;

import entities.salesmanagement.Customer;
import entities.salesmanagement.Orders;
import entities.salesmanagement.Product;
import entities.salesmanagement.Staff;
import utils.Enum;

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
    public static void initialize() {
        Product product1 = new Product("iphone", Enum.statusProduct.In_Stock, 10, 10.0, "Iphone");
        Product product2 = new Product("macbook", Enum.statusProduct.In_Stock, 20, 10.0, "Macbook");
        Product product3 = new Product("android", Enum.statusProduct.In_Stock, 30, 10.0, "Android");
        Product product4 = new Product("androidv2", Enum.statusProduct.Out_Stock, 30, 10.0, "Android");



        Customer customer1 = new Customer("customer1", "customer1", "customer", "customer1", "customer1", "customer1", "customer1");
        Customer customer2 = new Customer("customer2", "customer2", "customer", "customer2", "customer1", "customer1", "customer1");
        customer1.setBalance(10000.1);
        customer2.setBalance(10000.1);



        Staff staff1 = new Staff("staff", "staff", "staff", "staff", "staff", "staff", "staff");

    }
}
