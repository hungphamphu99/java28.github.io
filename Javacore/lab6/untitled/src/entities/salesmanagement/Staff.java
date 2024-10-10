package entities.salesmanagement;

import data.ShopData;
import entities.login.User;

public class Staff extends User {
    private double balance;

    public Staff(String username, String password, String role, String name, String email, String address, String phone) {
        super(username, password, role, name, email, address, phone);
        this.balance = 0.0;
        ShopData.staffs.add(this);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name="+super.getName()+
                "Username="+super.getUsername()+
                "Password="+super.getPassword()+
                "email="+super.getEmail()+
                "address="+super.getAddress()+
                "phone="+super.getPhone()+
                '}';
    }
}
