package src;

public class CODPayment implements Payment {
    public void makePayment(Double amount) {
        System.out.println("Payment made by Cash on Delivery");
    }
}
