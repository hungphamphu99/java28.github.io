package view.salesmanagement;

import entities.login.User;
import entities.salesmanagement.*;
import service.login.UserService;
import service.salesmanagement.CustomerService;
import service.salesmanagement.ProductService;

import java.util.Scanner;

public class CustomerMenu {
    Scanner scanner = new Scanner(System.in);
    CustomerService customerService = new CustomerService();
    ProductService productService = new ProductService();
    UserService userService = new UserService();
    public void display(User user) {
        Customer customer = (Customer) user;

       while (true) {

           try {
               System.out.println("----- Customer Menu -----");
               System.out.println("1. Display  Customer Information");
               System.out.println("2. Display Orders Of Customer Information");
               System.out.println("3. Display Products ");
               System.out.println("4. Cart");
               System.out.println("5. Deposit and Withdraw Balance");
               System.out.println("0. Logout");
               int option = Integer.parseInt(scanner.nextLine());
               switch (option) {
                   case 1:
                       System.out.println("1. Display  Customer Information");
                       customerService.editCustomerInfo(customer);
                       break;
                   case 2:
                       System.out.println("2. Display Orders Of Customer Information");
                       customerService.displayOrdersOfCustomerInformation(customer);
                       break;
                   case 3:
                       System.out.println("3. Display Products");
                       customerService.buyByCustomer(customer);
                       break;

                   case 4:
                       System.out.println("4. Cart");
                       customerService.openCartToBuyByCustomer(customer);
                       break;
                   case 5:
                       System.out.println("5. Deposit and Withdraw Balance");
                       depositAndWithdrawMenu(customer);
                       break;
                   case 0:
                       System.out.println("0. Logout");
                       userService.logout();
                       return;


               }

           }catch (NumberFormatException e){
               System.out.println("Please enter a number");
           }
       }
    }



    public void depositAndWithdrawMenu(Customer customer) {
        while (true) {
            try {
                System.out.println("----- Deposit and Withdraw Balance -----");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3.Back Main Menu");
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        System.out.println("1. Deposit");
                        customerService.depositByCustomer(customer);
                        break;
                    case 2:
                        System.out.println("2. Withdraw");
                        customerService.withdrawByCustomer(customer);
                        break;
                    case 3:
                        System.out.println("Back Main Menu");
                        return;
                }


            }catch (NumberFormatException e){
                System.out.println("Please enter a number");
            }
        }
    }


}
