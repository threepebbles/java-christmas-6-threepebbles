package christmas.model;

import christmas.constant.MenuType;
import christmas.model.validator.OrderValidator;
import java.util.Comparator;
import java.util.List;

public class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        OrderValidator.getInstance().validateOrders(orders);

        this.orders = orders.stream()
                .sorted(Comparator.comparing(o -> o.menu().getName()))
                .toList();
    }

    public int countByMenuType(MenuType menuType) {
        return orders.stream()
                .filter(order -> order.menu().getMenuType() == menuType)
                .mapToInt(Order::count)
                .sum();
    }

    public int calculateTotalPrice() {
        return orders.stream()
                .mapToInt(Order::calculatePrice)
                .sum();
    }

    public List<Order> getOrders() {
        return orders;
    }
}