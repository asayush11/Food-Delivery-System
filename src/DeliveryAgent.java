package src;

public class DeliveryAgent extends User {
    private final String vehicleNumber;

    public DeliveryAgent(String name, String email, String phone, String id, String vehicleNumber) {
        super(name, email, phone, id);
        this.vehicleNumber = vehicleNumber;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public void displayOrderDetails(FoodDeliverySystem foodDeliverySystem) {
        foodDeliverySystem.displayOrderDetailsForDeliveryAgent(getId());
    }

    public void deliverOrder(FoodDeliverySystem foodDeliverySystem) {
        foodDeliverySystem.markOrderAsDelivered(this);
    }

}
