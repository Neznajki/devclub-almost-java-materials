package dev.club.materials.spaghetti;

public class OrderService {

    public void processOrder(Order order) {

        if (order != null) {
            if (order.getUser() != null) {
                if (order.getUser().isActive()) {

                    if (order.getItems() != null && order.getItems().size() > 0) {

                        double total = 0;

                        for (Item item : order.getItems()) {
                            if (item.getPrice() > 0) {
                                total += item.getPrice();
                            } else {
                                System.out.println("Invalid price");
                            }
                        }

                        if (total > 100) {
                            total = total * 0.9;
                        }

                        if (order.getUser().getEmail() != null) {
                            System.out.println("Sending email to " + order.getUser().getEmail());
                        }

                        System.out.println("Total price: " + total);

                    } else {
                        System.out.println("Order has no items");
                    }

                } else {
                    System.out.println("User inactive");
                }
            } else {
                System.out.println("User missing");
            }
        } else {
            System.out.println("Order null");
        }
    }
}
