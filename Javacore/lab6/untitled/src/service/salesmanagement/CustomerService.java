package service.salesmanagement;

import entities.salesmanagement.*;
import data.ShopData;
import utils.Enum;
import utils.Enum.statusOder;
import utils.Validator;

import java.text.DecimalFormat;
import java.util.*;

public class CustomerService {
    Scanner scanner = new Scanner(System.in);
    ProductService productService = new ProductService();
    CartService cartService = new CartService();


    public Customer findCustomerById(int id) {
        for (Customer customer : ShopData.customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public void displayCustomer() {
        System.out.println("Enter Customer ID:");
        int id = Integer.parseInt(scanner.nextLine());
        Customer customer = findCustomerById(id);
        if (customer == null) {
            System.out.println("Customer Not Found");
        } else {
            System.out.println("Customer Found:\n" + customer);
        }
    }

    public void addProductToCartByCustomer(Customer customer, int productId) {
        Product product = productService.findProduct(productId);
        System.out.println("Enter quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());

        boolean success = cartService.addProductToCart(customer, product, quantity);
        if (success) {
            System.out.println("Product " + product.getName() + " added to cart.");
        } else {
            System.out.println("Failed to add product to cart.");
        }
    }

    public void showProductsAndBuyImmediately(Customer customer, int productId) {
        Product product = productService.findProduct(productId);

        if (product == null || product.getStatus() != Enum.statusProduct.In_Stock) {
            System.out.println("Product not found or is out of stock.");
            return;
        }

        System.out.println("Enter quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());

        if (product.getQuantity() >= quantity) {
            double totalCost = product.getPrice() * quantity;
            PaymentStrategy paymentStrategy = null;

            while (paymentStrategy == null) {
                System.out.println("Choose payment method: \n1. E-Wallet \n2. Cash on Delivery");
                int paymentChoice = Integer.parseInt(scanner.nextLine());

                // Select payment strategy based on user's choice
                if (paymentChoice == 1) {
                    if (customer.getBalance() >= totalCost) {
                        paymentStrategy = new EWalletPayment();
                    } else {
                        System.out.println("Insufficient balance in E-Wallet.");
                    }
                } else if (paymentChoice == 2) {
                    paymentStrategy = new CashOnDeliveryPayment();
                } else {
                    System.out.println("Invalid payment method. Please choose again.");
                }
            }

            // If we have a valid payment strategy, process the payment
            if (paymentStrategy != null) {
                customer.pay(totalCost, paymentStrategy);

                // Deduct balance if using E-Wallet
                if (paymentStrategy instanceof EWalletPayment) {
                    customer.setBalance(customer.getBalance() - totalCost);
                    System.out.println("Payment successful via E-Wallet. Remaining balance: " + customer.getBalance());
                } else {
                    System.out.println("Payment will be processed upon delivery (Cash on Delivery).");
                }

                // Create an order after successful payment
                Orders order = new Orders(customer, Map.of(product, quantity), statusOder.Pending, totalCost, paymentStrategy);
                customer.addOrder(order);

                // Reduce the product's quantity in stock
                product.setQuantity(product.getQuantity() - quantity);

                System.out.println("Purchase successful. Ordered " + quantity + " " + product.getName() + " for " + totalCost + ".");
            }
        } else {
            System.out.println("Not enough stock for product: " + product.getName());
        }
    }


    public void openCartToBuyByCustomer(Customer customer) {
        cartService.viewCart(customer);

        Map<Product, Integer> products = customer.getCart().getProducts();
        if (products.isEmpty()) {
            return;
        }

        // Display the products in the cart
        int i = 1;
        Map<Integer, Product> productMap = new HashMap<>(); // Map product number to the product for selection
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            // Check if the product is either Deleted or Out of Stock
            if (product.getStatus() == Enum.statusProduct.Deleted) {
                System.out.println(i + ". " + product.getName() + " - This product has been deleted and cannot be purchased.");
            } else if (product.getStatus() == Enum.statusProduct.Out_Stock) {
                System.out.println(i + ". " + product.getName() + " - This product is out of stock and cannot be purchased.");
            } else {
                System.out.println(i + ". " + product.getName() + " - Quantity: " + quantity + " - Price: " + product.getPrice());
                productMap.put(i, product); // Add valid products to the selection map
            }
            i++;
        }

        while (true) {
            try {
                System.out.println("Select a product to purchase by its number (0 to cancel): ");
                System.out.println("Buy all products(-1)");
                int choice = Integer.parseInt(scanner.nextLine());// Attempt to parse the trimmed input

                if (choice == 0) {
                    System.out.println("Purchase cancelled.");
                    return;
                }else if (choice == -1) {
                    System.out.println("Buy all products.");
                    buyAllProductsToCart(customer);
                    return;
                }
                // Validate the selected product
                if (productMap.containsKey(choice)) {
                    manageProductInCart(customer,productMap,products,choice);
                }


            }catch (Exception e) {
                System.out.println("Invalid choice.");
            }
        }
    }
    public void manageProductInCart(Customer customer, Map<Integer, Product> productMap, Map<Product, Integer> products, Integer choice) {
        while (true) {
            System.out.println("1. Edit quantity");
            System.out.println("2. Remove product");
            System.out.println("3. Buy product");
            System.out.println("0. Go back to product selection");

            try {
                int choice_2 = Integer.parseInt(scanner.nextLine());

                switch (choice_2) {
                    case 0:
                        System.out.println("Going back to product selection.");
                        return; // Exit the method to go back to the previous menu
                    case 1:
                        System.out.println("Editing quantity for: " + productMap.get(choice).getName());
                        editProductQuantityInCart(customer, choice);
                        break;
                    case 2:
                        System.out.println("Removing product: " + productMap.get(choice).getName());
                        removeProductFromCartByCustomer(customer, choice);
                        return; // Exit after removing the product
                    case 3:
                        System.out.println("Buying product: " + productMap.get(choice).getName());
                        buyOneProduct(customer, productMap, products, choice);
                        return; // Exit after buying the product
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }


    public void buyOneProduct(Customer customer, Map<Integer, Product> productMap, Map<Product, Integer> products, Integer choice) {

        if (choice == 0) {
            System.out.println("Purchase cancelled.");
            return;
        }

        // Validate the selected product
        if (!productMap.containsKey(choice)) {
            System.out.println("Invalid choice.");
            return;
        }

        // Get the selected product
        Product selectedProduct = productMap.get(choice);
        int selectedQuantity = products.get(selectedProduct);
        double totalCost = selectedProduct.getPrice() * selectedQuantity;

        System.out.println("Proceed to purchase " + selectedProduct.getName() + "? (yes/no)");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            // Choose a payment method
            boolean validPaymentMethod = false;
            boolean paymentSuccess = false;
            PaymentStrategy paymentStrategy = null;

            while (!validPaymentMethod) {
                System.out.println("Choose payment method: \n1. E-Wallet \n2. Cash on Delivery");
                int paymentChoice = Integer.parseInt(scanner.nextLine());

                if (paymentChoice == 1) {
                    // E-Wallet payment
                    if (customer.getBalance() >= totalCost) {
                        paymentStrategy = new EWalletPayment();
                        customer.pay(totalCost, paymentStrategy);
                        customer.setBalance(customer.getBalance() - totalCost); // Deduct the e-wallet balance
                        paymentSuccess = true;
                        validPaymentMethod = true;
                        System.out.println("Payment successful via E-Wallet. Remaining balance: " + customer.getBalance());
                    } else {
                        System.out.println("Insufficient balance to complete the purchase.");
                        validPaymentMethod = true; // Exit loop since there's no other option to pay
                    }
                } else if (paymentChoice == 2) {
                    // Cash on Delivery payment
                    paymentStrategy = new CashOnDeliveryPayment();
                    customer.pay(totalCost, paymentStrategy);
                    paymentSuccess = true;
                    validPaymentMethod = true;
                } else {
                    System.out.println("Invalid payment method. Please choose again.");
                }
            }

            if (paymentSuccess) {
                // Create an order after successful payment
                Orders order = new Orders(customer, Map.of(selectedProduct, selectedQuantity), statusOder.Pending, totalCost, paymentStrategy);
                customer.addOrder(order);

                // Reduce the product's quantity after purchase
                selectedProduct.setQuantity(selectedProduct.getQuantity() - selectedQuantity);

                // Remove the product from the cart
                cartService.removeProductFromCart(customer, selectedProduct);

                System.out.println("Purchase successful. Total cost: " + totalCost);
            }else {
                System.out.println("Purchase cancelled.");
            }

        } else {
            System.out.println("Purchase cancelled.");
        }
    }


    public void removeProductFromCartByCustomer(Customer customer, Integer productId) {


        // Ask the customer to select a product to remove

        Product product = productService.findProduct(productId);

        if (product == null || !customer.getCart().getProducts().containsKey(product)) {
            System.out.println("Product not found in the cart.");
            return;
        }

        // Remove the product from the cart
        boolean success = cartService.removeProductFromCart(customer,product);
        if (success) {
            System.out.println("Product " + product.getName() + " removed from the cart.");
        } else {
            System.out.println("Failed to remove product from cart.");
        }
    }

    public void editProductQuantityInCart(Customer customer, Integer productId) {
        Cart cart = customer.getCart();


        if (cart.getProducts().isEmpty()) {
            System.out.println("Your cart is empty. There are no products to edit.");
            return;
        }



        Product productInCart = null;
        for (Product product : cart.getProducts().keySet()) {
            if (product.getId() == productId) {
                productInCart = product;
                break;
            }
        }

        if (productInCart == null) {
            System.out.println("Product not found in the cart.");
            return;
        }

        if (productInCart.getStatus() == Enum.statusProduct.Deleted) {
            System.out.println("The product " + productInCart.getName() + " has been deleted and cannot be edited.");
            return;
        } else if (productInCart.getStatus() == Enum.statusProduct.Out_Stock) {
            System.out.println("The product " + productInCart.getName() + " is out of stock and cannot be edited.");
            return;
        }

        System.out.println("Enter the new quantity:");
        int newQuantity = Validator.inputInteger(scanner);

        if (newQuantity <= 0) {
            System.out.println("Invalid quantity. It should be greater than 0.");
            return;
        }

        if (newQuantity > productInCart.getQuantity()) {
            System.out.println("Not enough stock for product: " + productInCart.getName());
            return;
        }

        cart.getProducts().put(productInCart, newQuantity);
        System.out.println("Updated the quantity of " + productInCart.getName() + " to " + newQuantity + " in your cart.");

        System.out.println("Do you want to proceed to payment? (yes/no)");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("yes")) {
            openCartToBuyByCustomer(customer);
        } else {
            System.out.println("You can review your cart later.");
        }
    }


    public void buyAllProductsToCart(Customer customer) {

        double totalCost = 0.0;
        Map<Product, Integer> validProducts = new HashMap<>();
        List<String> errorMessages = new ArrayList<>();

        // Check product availability and calculate the total cost
        for (Map.Entry<Product, Integer> entry : customer.getCart().getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.getStatus() == Enum.statusProduct.Deleted) {
                errorMessages.add("Product " + product.getName() + " has been deleted and cannot be purchased.");
            } else if (product.getStatus() == Enum.statusProduct.Out_Stock) {
                errorMessages.add("Product " + product.getName() + " is out of stock and cannot be purchased.");
            } else if (product.getQuantity() < quantity) {
                errorMessages.add("Not enough stock for product: " + product.getName());
            } else {
                validProducts.put(product, quantity);
                totalCost += product.getPrice() * quantity;
            }
        }

        // If there are errors, display them and stop the process
        if (!errorMessages.isEmpty()) {
            for (String message : errorMessages) {
                System.out.println(message);
            }
            return;
        }

        System.out.println("The total cost is: " + totalCost + ". Do you want to proceed with the purchase? (yes/no)");
        String choice = scanner.nextLine();

        if (!choice.equalsIgnoreCase("yes")) {
            System.out.println("Purchase cancelled.");
            return;
        }

        // Choose payment method
        boolean validPaymentMethod = false;
        boolean paymentSuccess = false;
        PaymentStrategy paymentStrategy = null;

        while (!validPaymentMethod) {
            System.out.println("Choose payment method: \n1. E-Wallet \n2. Cash on Delivery \n3. Cancel");
            int paymentChoice = Integer.parseInt(scanner.nextLine());

            if (paymentChoice == 1) {
                if (customer.getBalance() >= totalCost) {
                    paymentStrategy = new EWalletPayment();
                    customer.pay(totalCost, paymentStrategy);
                    customer.setBalance(customer.getBalance() - totalCost); // Deduct balance
                    paymentSuccess = true;
                    validPaymentMethod = true;
                    System.out.println("Payment successful via E-Wallet. Remaining balance: " + customer.getBalance());
                } else {
                    System.out.println("Insufficient balance to complete the purchase.");
                    validPaymentMethod = true;
                }
            } else if (paymentChoice == 2) {
                paymentStrategy = new CashOnDeliveryPayment();
                customer.pay(totalCost, paymentStrategy);
                paymentSuccess = true;
                validPaymentMethod = true;
            } else {
                System.out.println("Invalid payment method. Please choose again.");
                return;
            }
        }

        if (paymentSuccess) {
            // Create order after successful payment
            Orders order = new Orders(customer, validProducts, statusOder.Pending, totalCost, paymentStrategy);
            customer.addOrder(order);

            // Update product stock and clear the cart
            for (Map.Entry<Product, Integer> entry : validProducts.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                product.setQuantity(product.getQuantity() - quantity);
            }

            customer.getCart().clearCart();
            System.out.println("Purchase successful! Total cost: " + totalCost);
        } else {
            System.out.println("Purchase failed due to insufficient funds.");
        }
    }


    public void depositByCustomer(Customer customer) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        while (true) {
            try {
                System.out.print("Enter the amount you want to deposit: ");
                String input = scanner.nextLine();
                double amount = Double.parseDouble(input);

                if (amount > 0) {
                    customer.deposit(amount);
                    System.out.println("You have deposited " + df.format(amount) + " into your account. Current balance: " + df.format(customer.getBalance()));
                    break;
                } else {
                    System.out.println("Invalid amount. Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }

    public void withdrawByCustomer(Customer customer) {
        System.out.print("Enter the amount you want to withdraw: ");
        double amount = scanner.nextDouble();
        if (customer.withdraw(amount)) {
            System.out.println("You have withdrawn " + amount + " from your account. Remaining balance: " + customer.getBalance());
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }


    public void cancelOrderByCustomer(Customer customer) {
        List<Orders> orders = customer.getOrders();

        if (orders.isEmpty()) {
            System.out.println("You have no orders to cancel.");
            return;
        }

        System.out.println("Select a pending order to cancel (0 to exit):");
        int i = 1;
        List<Orders> pendingOrders = new ArrayList<>();
        for (Orders order : orders) {
            if (order.getStatus() == statusOder.Pending) {
                System.out.println(i + ". Order ID: " + order.getId() + ", Total Cost: " + order.getTotal() + ", Status: " + order.getStatus());
                pendingOrders.add(order);
                i++;
            }
        }

        if (pendingOrders.isEmpty()) {
            System.out.println("No pending orders available to cancel.");
            return;
        }

        int choice = Validator.inputInteger(scanner);
        if (choice == 0) {
            System.out.println("Cancelation aborted.");
            return;
        }

        if (choice < 1 || choice > pendingOrders.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Orders selectedOrder = pendingOrders.get(choice - 1);

        customer.setBalance(customer.getBalance() + selectedOrder.getTotal());

        for (Map.Entry<Product, Integer> entry : selectedOrder.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.setQuantity(product.getQuantity() + quantity);
        }

        selectedOrder.setStatus(statusOder.Canceled);

        System.out.println("Order canceled successfully. Refunded amount: " + selectedOrder.getTotal());
    }

    public void buyByCustomer(Customer customer) {
        productService.displayInStockProducts();
        while (true) {
            try {
                System.out.println("Enter the product ID you want to see more information about: ");
                int productId = Integer.parseInt(scanner.nextLine());
                boolean isValidProduct = productService.displayProduct_2(productId);

                // If the product ID is not found, prompt the user again
                if (!isValidProduct) {
                    continue;
                }

                while (true) {
                    try {
                        System.out.println("1. Buy product immediately");
                        System.out.println("2. Add product to the cart");
                        System.out.println("0. Back");
                        int choice = Integer.parseInt(scanner.nextLine());
                        switch (choice) {
                            case 1:
                                System.out.println("1. Buy product immediately");
                                showProductsAndBuyImmediately(customer, productId);
                                return;
                            case 2:
                                System.out.println("2. Add product to the cart");
                                addProductToCartByCustomer(customer, productId);
                                return;
                            case 0:
                                return;
                            default:
                                System.out.println("Invalid choice. Please enter 1, 2, or 0.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number: 1, 2, or 0.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid product ID.");
            }
        }
    }


    public void updateInformationCustomer() {
        System.out.print("Enter customer ID: ");
        int customerId = Validator.inputInteger(scanner);
        Customer customer = findCustomerById(customerId);

        if (customer == null) {
            System.out.println("customer with ID " + customerId + " not found.");
            return;
        }

        System.out.println("Current information: ");
        System.out.println(customer);

        System.out.println("Press Enter without typing anything to keep the current information.");

        // Update Name
        System.out.print("Enter new name (" + customer.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            customer.setName(newName);
            System.out.println("Name  updated successfully.");
        }

        // Update Email
        System.out.print("Enter new email (" + customer.getEmail() + "): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            customer.setEmail(newEmail);
            System.out.println("Email updated successfully.");
        }

        // Update Address
        System.out.print("Enter new address (" + customer.getAddress() + "): ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            customer.setAddress(newAddress);
            System.out.println("Address updated successfully.");
        }

        // Update Phone
        System.out.print("Enter new phone number (" + customer.getPhone() + "): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            customer.setPhone(newPhone);
            System.out.println("Phone updated successfully.");
        }

        System.out.println("Updated information: ");
        System.out.println(customer);
    }

    public void displayOrdersOfCustomerInformation(Customer customer){
        System.out.println(customer.getOrders());
        if (customer.getOrders().isEmpty()) {
            return;
        }
        while (true){
            try {
                System.out.println("1. Canncel Oder"+
                        "\n2. Back");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        cancelOrderByCustomer(customer);
                        break;
                    case 2:
                        return;
                }
            }catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public void displayCustomerInfo(Customer customer) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String formattedBalance = df.format(customer.getBalance());
        System.out.println("User{id=" + customer.getId() +
                ", username='" + customer.getUsername() + '\'' +
                ", role='" + customer.getRole() + '\'' +
                ", name='" + customer.getName() + '\'' +
                ", email='" + customer.getEmail() + '\'' +
                ", address='" + customer.getAddress() + '\'' +
                ", phone='" + customer.getPhone() + '\'' +
                "}, Balance: " + formattedBalance +
                ", Orders: " + customer.getOrders().size());
    }
}
