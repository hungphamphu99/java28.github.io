

import View.Menu;

import service.UserService;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        userService.registerUser("hung","hung@gmail.com","1234Hung-");
        Menu menu = new Menu(userService);
        menu.displayMainMenu();
    }
}
