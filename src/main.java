package src;

public class main {
    public static void main(String[] args) {
        FoodDeliverySystem foodDeliverySystem = FoodDeliverySystem.getInstance();
        // Adding restaurants
        Restaurant restaurant1 = foodDeliverySystem.addRestaurant("Fine Dine", "RG Park", "342325353");
        Restaurant restaurant2 = foodDeliverySystem.addRestaurant("Fast Food", "MG Road", "342325354");
        Restaurant restaurant3 = foodDeliverySystem.addRestaurant("Street Food", "JP Nagar", "342325355");

        // Adding items to restaurant
        String res1_itm1 = restaurant1.addItems("Paneer Tikka", 200, "Cottage cheese marinated in spices and grilled", true);
        String res1_itm2 =  restaurant1.addItems("Chicken Tikka", 250, "Chicken pieces marinated in spices and grilled", false);
        String res1_itm3 = restaurant1.addItems("Chicken Tandoori", 300, "Chicken marinated in curd and spices and grilled", false);

        String res2_itm1 = restaurant2.addItems("Burger", 100, "Veg Patty with lettuce, cheese and sauce", true);
        String res2_itm2 = restaurant2.addItems("Fries", 50, "Fried potato fingers", true);

        String res3_itm1 = restaurant3.addItems("Pav Bhaji", 80, "Mixed vegetable curry served with bread", true);
        String res3_itm2 = restaurant3.addItems("Roasted Chicken", 50, "Chicken roasted on open flame", false);

        // Displaying menu
        foodDeliverySystem.displayMenu(restaurant1.getID());
        foodDeliverySystem.displayMenu(restaurant2.getID());
        foodDeliverySystem.displayMenu(restaurant3.getID());

        restaurant1.updatePrice(res1_itm2, 220);
        foodDeliverySystem.displayMenu(restaurant1.getID());

        // Adding customers
        Customer customer1 = foodDeliverySystem.addCustomer("John", "abcd@gmail.com", "342325356", "Geeta Nagar");
        Customer customer2 = foodDeliverySystem.addCustomer("Doe", "dff@gmail.com", "342325357", "MG Road");

        // Adding delivery agents
        DeliveryAgent deliveryAgent1 = foodDeliverySystem.addDeliveryAgent("Tom", "tom@gmail.com", "342325358", "LM12345");
        DeliveryAgent deliveryAgent2 = foodDeliverySystem.addDeliveryAgent("Jerry", "jerry@gmail.com", "32323435", "LM12346");

        // Placing orders
        customer1.selectRestaurant(restaurant1);
        customer1.addItemToCart("Paneer Tikka", 2);
        customer1.addItemToCart("Chicken Tikka", 1);
        String order1 = customer1.placeOrder(foodDeliverySystem, new CODPayment());

        customer2.selectRestaurant(restaurant2);
        customer2.addItemToCart("Burger", 2);
        customer2.addItemToCart("Fries", 1);
        customer2.removeItemFromCart("Fries");
        customer2.editItemInCart("Burger", 1);
        String order2 = customer2.placeOrder(foodDeliverySystem, new OnlinePayment());

        // Displaying order details
        customer1.displayOrderDetails(foodDeliverySystem);
        customer2.displayOrderDetails(foodDeliverySystem);

        // Assigning delivery agent
        DeliveryAgent delAgent1 = foodDeliverySystem.assignDeliveryAgent(order1);
        DeliveryAgent delAgent2 = foodDeliverySystem.assignDeliveryAgent(order2);

        // Updating order status
        restaurant1.orderPrepared(foodDeliverySystem);
        restaurant2.orderPrepared(foodDeliverySystem);

        // Order Delivered
        delAgent2.deliverOrder(foodDeliverySystem);
        delAgent1.deliverOrder(foodDeliverySystem);

    }
}
