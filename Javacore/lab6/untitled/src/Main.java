import data.ShopData;
import data.UniversityData;
import data.UsersData;

import view.login.LoginMenu;


public class Main {
    public static void main(String[] args) {
        UniversityData.initialize();
        ShopData.initialize();
        UsersData.initialize();

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.mainMenu();
    }
}
