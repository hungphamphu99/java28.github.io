public class Main {
    public static void main(String[] args) {
        Account account = new Account("techmaster", "hoclacoviec", 10000.0);
        Login login = new Login(account);
        login.login();

        Menu menu = new Menu(account);
        menu.showMenu();
    }
}
