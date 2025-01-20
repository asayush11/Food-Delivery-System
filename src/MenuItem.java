package src;

public class MenuItem {
    private final String id;
    private final String name;
    private double price;
    private final String description;
    private final boolean isVegetarian;
    private boolean isAvailable;

    public MenuItem(String id, String name, double price, String description, boolean isVegetarian) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.isVegetarian = isVegetarian;
        this.isAvailable = true;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getId() {
        return id;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
