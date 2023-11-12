package christmas.model;

import christmas.constant.MenuType;
import christmas.model.validator.OrdersValidator;
import java.util.Comparator;
import java.util.List;

public class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders.stream()
                .sorted(Comparator.comparing(Order::getMenuName))
                .toList();
        
        OrdersValidator.getInstance().validateOrders(this);
    }

    public int countByMenuType(MenuType menuType) {
        return orders.stream()
                .filter(order -> order.getMenuType() == menuType)
                .mapToInt(Order::getCount)
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