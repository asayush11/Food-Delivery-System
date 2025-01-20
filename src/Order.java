package src;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String id;
    private final Restaurant restaurant;
    private final Customer customer;
    private final List<Item> items;
    private OrderStatus status;
    private DeliveryAgent deliveryAgent;

    public Order(String id, Restaurant restaurant, Customer customer) {
        this.id = id;
        this.restaurant = restaurant;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }
    public void addItem(String name, int quantity) {
        for (MenuItem item : restaurant.getMenuItems()) {
            if (item.getName().equals(name) && item.isAvailable()) {
                Item orderItem = new Item(item, quantity);
                items.add(orderItem);
                System.out.println("Item " + item.getName() + " added successfully in the order");
                return;
            }
        }
        System.out.println("Item " + name + " not found in the menu of " + restaurant.getName());
    }

    public void updateOrderStatus(OrderStatus status) {
        this.status = status;
        System.out.println("Order status updated to " + status);
    }
    public void assignDeliveryAgent(DeliveryAgent deliveryAgent) {
        this.deliveryAgent = deliveryAgent;
        System.out.println("Order assigned to delivery agent " + deliveryAgent.getName());
        updateOrderStatus(OrderStatus.PREPARING);
    }
    public void displayOrderDetails() {
        System.out.println("Order Details:");
        System.out.println("Order ID: " + id);
        System.out.println("Restaurant: " + restaurant.getName());
        System.out.println("Customer: " + customer.getName());
        System.out.println("Order Status: " + status);
        System.out.println("Items:");
        for (Item item : items) {
            System.out.println(item.getMenuItem().getName() + " " + item.getQuantity());
        }
    }
    public String getRestaurantName() {
        return restaurant.getName();
    }
    public String getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public DeliveryAgent getDeliveryAgent() {
        return deliveryAgent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

}
