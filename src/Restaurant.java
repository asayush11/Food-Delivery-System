package src;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private final String name;
    private final String address;
    private final String phone;
    private final String ID;
    private final List<MenuItem> menuItems;

    public Restaurant(String name, String address, String phone, String ID) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.ID = ID;
        this.menuItems = new ArrayList<>();
    }
    public String addItems(String name, double price, String description, boolean isVegetarian) {
        String id = "ITM" + "-" + (menuItems.size() + 1);
        MenuItem item = new MenuItem(id, name, price, description, isVegetarian);
        menuItems.add(item);
        System.out.println("Item " + name + " added successfully in the menu of " + this.name);
        return id;
    }
    public void displayMenu() {
        System.out.println("Menu of " + this.name);
        for (MenuItem item : menuItems) {
            System.out.println(item.getName() + " " + item.getPrice() + " " + item.getDescription() + " " + item.isVegetarian());
        }
    }
    public void removeItems(String id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                menuItems.remove(item);
                System.out.println("Item " + item.getName() + " removed successfully from the menu of " + this.name);
                return;
            }
        }
        System.out.println("Item with id " + id + " not found in the menu of " + this.name);
    }
    public void updatePrice(String id, double price) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                item.setPrice(price);
                System.out.println("Price of " + item.getName() + " updated successfully in the menu of " + this.name);
                return;
            }
        }
        System.out.println("Item with id " + id + " not found in the menu of " + this.name);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }
    public void orderPrepared(FoodDeliverySystem foodDeliverySystem) {
        List<Order> ordersForRestaurant = foodDeliverySystem.getOrdersForRestaurant(this);
        for (Order order : ordersForRestaurant) {
            System.out.println("Order " + order.getId() + " prepared");
            foodDeliverySystem.orderOutForDelivery(order.getId());
            }
    }
    public void menuItemOutOfStock(String menuItemID) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(menuItemID)) {
                item.setAvailable(false);
                System.out.println("Item " + item.getName() + " is out of stock");
                return;
            }
        }
        System.out.println("Item with id " + menuItemID + " not found in the menu of " + this.name);
    }


}
