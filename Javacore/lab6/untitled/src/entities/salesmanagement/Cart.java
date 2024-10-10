package entities.salesmanagement;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    // Updated to use BigDecimal
    public BigDecimal getTotalCost() {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            BigDecimal productPrice = BigDecimal.valueOf(entry.getKey().getPrice());
            BigDecimal quantity = BigDecimal.valueOf(entry.getValue());
            total = total.add(productPrice.multiply(quantity));
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void showCart() {
        if (products.isEmpty()) {
            System.out.println("\n");
        } else {
            System.out.println("Your cart contains:");
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                System.out.println("Product: " + entry.getKey().getName() + ", Quantity: " + entry.getValue());
            }
        }
    }
}
