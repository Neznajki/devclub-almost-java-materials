package dev.club.materials.good;

public class OrderService {

    private final PricingService pricingService;
    private final NotificationService notificationService;

    public void processOrder(Order order) {
        validate(order);

        double total = pricingService.calculateTotal(order);
        notificationService.sendOrderEmail(order.getUser());

        System.out.println("Total price: " + total);
    }

    private void validate(Order order) {
        if (order == null) throw new IllegalArgumentException("Order null");
        if (order.getUser() == null) throw new IllegalArgumentException("User missing");
    }
}
