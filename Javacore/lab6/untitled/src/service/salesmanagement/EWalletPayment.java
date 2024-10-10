package service.salesmanagement;

import java.math.BigDecimal;

public class EWalletPayment implements PaymentStrategy {

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Payment of " + amount.setScale(2, BigDecimal.ROUND_HALF_UP) + " using e-wallet.");

    }
}
