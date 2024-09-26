package ex_2;

import ex_2.data.Database;
import ex_2.views.Menu;


public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        database.inputData();
        Menu menu = new Menu();
        menu.showMenu();
    }
}
