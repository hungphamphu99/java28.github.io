package service.salesmanagement;

import java.math.BigDecimal;

public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Updated to use BigDecimal for precise monetary calculations
    public void executePayment(BigDecimal amount) {
        paymentStrategy.pay(amount);
    }
}
