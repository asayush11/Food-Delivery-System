package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodDeliverySystem {
    private static FoodDeliverySystem instance;
    private final Map<String, Order> orders;
    private final Map<String, Restaurant> restaurants;
    private final Map<String, DeliveryAgent> deliveryAgents;
    private final Map<String, Customer> customers;

    private FoodDeliverySystem() {
        orders = new HashMap<>();
        restaurants = new HashMap<>();
        deliveryAgents = new HashMap<>();
        customers = new HashMap<>();
    }

    public synchronized static FoodDeliverySystem getInstance() {
        if(instance == null) {
            instance = new FoodDeliverySystem();
        }
        return instance;
    }

    // Restaurant
    public Restaurant addRestaurant(String name, String address, String phone) {
        String id = "RES" + "-" + (restaurants.size() + 1);
        Restaurant restaurant = new Restaurant(name, address, phone, id);
        restaurants.put(id, restaurant);
        System.out.println("Restaurant " + name + " added successfully");
        return restaurant;
    }

    public void displayMenu(String id) {
        Restaurant restaurant = restaurants.get(id);
        if (restaurant == null) {
            System.out.println("Restaurant " + id + " not found");
            return;
        }
        restaurant.displayMenu();
    }

    public List<Order> getOrdersForRestaurant(Restaurant restaurant) {
        List<Order> ordersForRestaurant = new ArrayList<>();
        for(Order order : orders.values()) {
            if (order.getRestaurant().equals(restaurant) && order.getStatus() == OrderStatus.PREPARING) {
                ordersForRestaurant.add(order);
            }
        }
        return ordersForRestaurant;
    }

    public void orderOutForDelivery(String orderID) {
        Order order = orders.get(orderID);
        if (order == null) {
            System.out.println("Order " + orderID + " not found");
            return;
        }
        order.updateOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
        System.out.println("Order " + orderID + " out for delivery");
        orders.put(orderID, order);
    }

    // Delivery Agent
    public DeliveryAgent addDeliveryAgent(String name, String email, String phone, String vehicleNumber) {
        String id = "DA" + "-" + (deliveryAgents.size() + 1);
        DeliveryAgent deliveryAgent = new DeliveryAgent(name, email, phone, id, vehicleNumber);
        deliveryAgents.put(id, deliveryAgent);
        System.out.println("Delivery Agent " + name + " added successfully");
        return deliveryAgent;
    }

    private List<Order> getOrdersForDeliveryAgent(DeliveryAgent deliveryAgent) {
        List<Order> ordersForDeliveryAgent = new ArrayList<>();
        for(Order order : orders.values()) {
            if (order.getDeliveryAgent() != null && order.getDeliveryAgent().equals(deliveryAgent) && order.getStatus() != OrderStatus.DELIVERED) {
                ordersForDeliveryAgent.add(order);
            }
        }
        return ordersForDeliveryAgent;
    }

    // Customer
    public Customer addCustomer(String name, String email, String phone, String address) {
        String id = "CUS" + "-" + (customers.size() + 1);
        Customer customer = new Customer(name, email, phone, id, address);
        customers.put(id, customer);
        System.out.println("Customer " + name + " added successfully");
        return customer;
    }

    public void cancelOrder(String id) {
        Order order = orders.get(id);
        if (order == null) {
            System.out.println("Order " + id + " not found");
            return;
        }
        order.updateOrderStatus(OrderStatus.CANCELLED);
        System.out.println("Order " + id + " cancelled successfully");
    }
    public String addOrder(Cart cart, Customer customer) {
        String id = "ORD" + "-" + (orders.size() + 1);
        Order order = new Order(id , cart.getRestaurant(), customer);
        for (Item item : cart.getItems()) {
            order.addItem(item.getMenuItem().getName(), item.getQuantity());
        }
        System.out.println("Order placed successfully " + id);
        order.updateOrderStatus(OrderStatus.CONFIRMED);
        orders.put(order.getId(), order);
        return id;
    }
    public void markOrderAsDelivered(DeliveryAgent user) {
        for(Order order : orders.values()){
            if (order.getDeliveryAgent().equals(user) && order.getStatus() == OrderStatus.OUT_FOR_DELIVERY) {
                order.updateOrderStatus(OrderStatus.DELIVERED);
                System.out.println("Order " + order.getId() + " delivered successfully");
                return;
            }
        }
    }

    public void displayOrderDetailsForUser(String userID) {
        for (Order order : orders.values()) {
            if (order.getCustomer().getId().equals(userID)) {
                order.displayOrderDetails();
            }
        }
    }
    public void displayOrderDetailsForRestaurant(String restaurantID) {
        for (Order order : orders.values()) {
            if (order.getRestaurant().getID().equals(restaurantID)) {
                order.displayOrderDetails();
            }
        }
    }
    public void displayOrderDetailsForDeliveryAgent(String deliveryAgentID) {
        for (Order order : orders.values()) {
            if (order.getDeliveryAgent().getId().equals(deliveryAgentID)) {
                order.displayOrderDetails();
            }
        }
    }

    public DeliveryAgent assignDeliveryAgent(String orderID) {
        Order order = orders.get(orderID);
        if (order == null) {
            System.out.println("Order " + orderID + " not found");
            return null;
        }
        for (DeliveryAgent deliveryAgent : deliveryAgents.values()) {
            if (getOrdersForDeliveryAgent(deliveryAgent).isEmpty()) {
                order.assignDeliveryAgent(deliveryAgent);
                return deliveryAgent;
            }
        }
        System.out.println("No delivery agent available to assign to order " + orderID);
        return null;
    }
}
