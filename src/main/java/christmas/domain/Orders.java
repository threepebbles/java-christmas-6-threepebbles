package christmas.domain;

import christmas.constant.MenuType;
import christmas.domain.DTO.OrdersDTO;
import christmas.domain.validator.OrdersValidator;
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

    public OrdersDTO toDTO() {
        return new OrdersDTO(orders.stream().map(Order::toDTO).toList());
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