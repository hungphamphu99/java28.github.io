import data.ShopData;
import data.UniversityData;
import data.UsersData;
import entities.universitymanagement.*;
import entities.universitymanagement.Class;
import view.login.LoginMenu;
import entities.login.User;
import entities.salesmanagement.Customer;
import entities.salesmanagement.Product;
import entities.salesmanagement.Staff;
import utils.Enum;

public class Main {
    public static void main(String[] args) {
        UniversityData.initialize();
        ShopData.initialize();
        UsersData.initialize();



        LoginMenu loginMenu = new LoginMenu();
        loginMenu.mainMenu();
    }
}
