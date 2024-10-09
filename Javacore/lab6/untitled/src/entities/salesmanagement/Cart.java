package entities.salesmanagement;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products;

    public Cart() {
        this.products = new HashMap<>();
    }


    public boolean addProduct(Product product, int quantity) {
        if (product.inStock() && product.getQuantity() >= quantity) {
            products.put(product, products.getOrDefault(product, 0) + quantity);
            return true;
        } else {
            System.out.println("Not enough stock for product: " + product.getName());
            return false;
        }
    }

    public boolean removeProduct(Product product) {
        if (products.containsKey(product)) {
            products.remove(product);
            System.out.println(product.getName() + " removed from the cart.");
            return true;
        } else {
            System.out.println("Product is not in the cart.");
            return false;
        }
    }

    public void clearCart() {
        products.clear();
        System.out.println("Cart cleared.");
    }

    public double getTotalCost() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void showCart() {
        if (products.isEmpty()) {
            System.out.println();
        } else {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                System.out.println("Product: " + entry.getKey().getName() + ", Quantity: " + entry.getValue());
            }
        }
    }
}
