package service.salesmanagement;

public class CashOnDeliveryPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Payment " + amount + " upon delivery.");    }
}
