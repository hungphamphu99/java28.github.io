package entities.salesmanagement;

import data.ShopData;
import utils.Enum;

public class Product {

    private int id;
    private static int nextId = 0;
    private String name;
    private Enum.statusProduct status;
    private int quantity;
    private double price;
    private String description;


    public Product( String name, Enum.statusProduct status, int quantity, double price, String description) {
        this.id = ++nextId;
        this.name = name;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        ShopData.addProduct(this);

    }

    public boolean inStock() {
        return status == Enum.statusProduct.In_Stock;
    }

    public boolean outStock() {
        return status == Enum.statusProduct.Out_Stock;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Enum.statusProduct getStatus() {
        return status;
    }

   public void setStatus(Enum.statusProduct status) {
        this.status = status;
   }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        if (this.quantity == 0) {
            this.status = Enum.statusProduct.Out_Stock;
        } else if (this.quantity > 0) {
            this.status = Enum.statusProduct.In_Stock;
        }
    }


    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setNextId(int nextId) {
        Product.nextId = nextId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product [ID: " + id + ", Name: " + name + ", Status: " + status + ", Quantity: " + quantity + ", Price: " + price + ", Description: " + description + "]";
    }
}
