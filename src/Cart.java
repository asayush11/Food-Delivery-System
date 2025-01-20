package src;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Item> items;
    private Restaurant restaurant;
    public Cart(){
        this.items = new ArrayList<>();
    }
    public void addItem(String name, int quantity) {
        for (MenuItem item : restaurant.getMenuItems()) {
            if (item.getName().equals(name) && item.isAvailable()) {
                Item orderItem = new Item(item, quantity);
                items.add(orderItem);
                System.out.println("Item " + item.getName() + " added successfully in the cart");
                return;
            }
        }
        System.out.println("Item " + name + " not found in the menu of " + restaurant.getName());
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public void removeItem(String name) {
        for (Item item : items) {
            if (item.getMenuItem().getName().equals(name)) {
                items.remove(item);
                System.out.println("Item " + name + " removed successfully from the cart");
                return;
            }
        }
        System.out.println("Item " + name + " not found in the cart");
    }
    public void editItem(String name, int quantity) {
        for (Item item : items) {
            if (item.getMenuItem().getName().equals(name)) {
                item.setQuantity(quantity);
                System.out.println("Item " + name + " updated successfully in the cart");
                return;
            }
        }
        System.out.println("Item " + name + " not found in the cart");
    }
    public void clear(){
        this.items.clear();
        this.restaurant = null;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<Item> getItems() {
        return items;
    }
}
