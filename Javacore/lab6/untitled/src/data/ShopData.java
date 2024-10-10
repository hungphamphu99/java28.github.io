package data;

import entities.salesmanagement.Customer;
import entities.salesmanagement.Order;
import entities.salesmanagement.Product;
import entities.salesmanagement.Staff;
import utils.Enum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShopData {
    public static  List<Product> products = new ArrayList<>();
    public static  List<Staff> staffs = new ArrayList<>();
    public static  List<Customer> customers = new ArrayList<>();
    public static  List<Order> orders = new ArrayList<>();

    public ShopData() {

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
        Product product4 = new Product("android_v2", Enum.statusProduct.Out_Stock, 30, 10.0, "Android");


        Customer customer1 = new Customer("customer1", "customer1", "customer", "customer1", "customer1", "customer1", "customer1");
        Customer customer2 = new Customer("customer2", "customer2", "customer", "customer2", "customer1", "customer1", "customer1");
        customer1.setBalance(new BigDecimal("10000.10"));
        customer2.setBalance(new BigDecimal("10000.10"));


        Staff staff1 = new Staff("staff", "staff", "staff", "staff", "staff", "staff", "staff");

    }
}
