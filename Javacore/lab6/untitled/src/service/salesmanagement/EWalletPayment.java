package service.salesmanagement;

public class EWalletPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Payment " + amount + " using e-wallet " );    }
}