package src;

public class Customer extends User {
    private final String address;
    private final Cart cart;
    public Customer(String name, String email, String phone, String id, String address) {
        super(name, email, phone, id);
        this.address = address;
        this.cart = new Cart();
    }
    public void selectRestaurant(Restaurant restaurant) {
        cart.setRestaurant(restaurant);
        System.out.println("Restaurant " + restaurant.getName() + " selected");
    }
    public void addItemToCart(String name, int quantity) {
        cart.addItem(name, quantity);
    }
    public void removeItemFromCart(String name) {
        cart.removeItem(name);
    }
    public void editItemInCart(String name, int quantity) {
        cart.editItem(name, quantity);
    }
    private void clearCart() {
        this.cart.clear();
    }
    private void checkout(Payment payment) {
        double total = 0.0;
        for (Item item : cart.getItems()) {
            total += item.getMenuItem().getPrice() * item.getQuantity();
        }
        System.out.println("Total amount to be paid: " + total);
        payment.makePayment(total);
    }
    public String placeOrder(FoodDeliverySystem foodDeliverySystem, Payment payment) {
        checkout(payment);
        String orderID = foodDeliverySystem.addOrder(this.cart, this);
        clearCart();
        return orderID;
    }
    public void cancelOrder(String id, FoodDeliverySystem foodDeliverySystem) {
        foodDeliverySystem.cancelOrder(id);
    }
    public String getAddress() {
        return address;
    }
    public void displayOrderDetails(FoodDeliverySystem foodDeliverySystem) {
        foodDeliverySystem.displayOrderDetailsForUser(getId());
    }

}
