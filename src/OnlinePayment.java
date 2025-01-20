package src;

public class OnlinePayment implements Payment {
    public void makePayment(Double amount) {
        System.out.println("Payment made by Online Payment");
    }
}
