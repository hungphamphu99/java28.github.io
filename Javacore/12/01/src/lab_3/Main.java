package lab_3;

import lab_3.data.Database;
import lab_3.view.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Database.data();
        menu.displayMenu();
    }
}