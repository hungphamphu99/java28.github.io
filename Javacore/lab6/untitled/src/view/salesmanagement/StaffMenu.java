package view.salesmanagement;

import entities.login.User;
import data.ShopData;
import service.login.UserService;
import service.salesmanagement.CustomerService;
import service.salesmanagement.OrdersService;
import service.salesmanagement.ProductService;

import java.util.Scanner;

public class StaffMenu {
    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserService();
    OrdersService ordersService = new OrdersService();
    ProductService productService = new ProductService();
    CustomerService customerService = new CustomerService();


    public void display(User user) {
        while (true) {
            try {
                System.out.println("----- Staff Menu -----");
                System.out.println("1. Display Products Information");
                System.out.println("2. Display Orders Information");
                System.out.println("3. Display Customer Information");
                System.out.println("4. Edit Orders ");
                System.out.println("5. Edit Product ");
                System.out.println("6. Edit Customer ");
                System.out.println("7. Logout");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("----- Products Information -----");
                        if (ShopData.getProducts().isEmpty()) {
                            System.out.println("No products available.");
                        } else {
                            ShopData.getProducts().forEach(System.out::println);
                        }
                        break;

                    case 2:
                        System.out.println("2. Display Orders Information");
                        ShopData.getOrders().forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("3. Display Customer Information");
                        ShopData.getCustomers().forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println("4. Edit Orders");
                        editOder();
                        break;
                    case 5:
                        System.out.println("5. Edit Product");
                        editProduct();
                        break;
                    case 6:
                        System.out.println("6. Update Customer ");
                        customerService.updateInformationCustomer();
                        break;
                    case 7:
                        userService.logout();
                        System.out.println("LOGOUT SUCCESSFULLY");
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }


    public void editOder(){
       while (true) {
           try {
               System.out.println("----- Oder Menu -----");
                System.out.println("1. Deliver Orders");
                System.out.println("2. Cancel Order By Id");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("1. Deliver Orders");
                        ordersService.deliverPendingOrders();
                        break;
                    case 2:
                        System.out.println("2. Cancel Order By Id");
                        ordersService.cancelOrderById();
                        break;
                }
                break;
           }catch (NumberFormatException e){
               System.out.println("Please enter a valid number.");
           }
       }
    }
    public void editProduct(){
        while (true) {
            try {
                System.out.println("----- Edit Product Menu -----");
                System.out.println("1. Add Product");
                System.out.println("2. Remove Product");
                System.out.println("3. Update Product");
                System.out.println("4. Back to Main Menu");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("1. Add Product");
                        productService.addProduct();
                        break;
                    case 2:
                        System.out.println("2. Delete Product");
                        productService.deleteProductById();
                        break;
                    case 3:
                        System.out.println("3. Update Product");
                        productService.updateProduct();
                        break;
                    case 4:
                        System.out.println("4. Back to Main Menu");
                        return;
                }
            }catch (NumberFormatException e){
                System.out.println("Please enter a valid number.");
            }
        }
    }


}
