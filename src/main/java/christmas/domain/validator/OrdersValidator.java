package christmas.domain.validator;

import christmas.constant.ErrorMessage;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.constant.MenuType;
import java.util.List;

public class OrdersValidator {
    private static OrdersValidator ordersValidator;

    private OrdersValidator() {
    }

    public static OrdersValidator getInstance() {
        if (ordersValidator == null) {
            ordersValidator = new OrdersValidator();
        }
        return ordersValidator;
    }

    public void validateOrders(Orders orders) {
        validateDuplication(orders.getOrders());
        validateHasOnlyBeverage(orders.getOrders());
        validateOrderSize(orders.getOrders());
    }

    private static void validateDuplication(List<Order> orders) {
        if (orders.stream()
                .map(Order::getMenu)
                .distinct()
                .count() != orders.size()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER.getMessage());
        }
    }

    private void validateOrderSize(List<Order> orders) {
        int MAX_ORDER_SIZE = 20;
        if (orders.stream()
                .mapToInt(Order::getCount)
                .sum() > MAX_ORDER_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER.getMessage());
        }
    }

    private void validateHasOnlyBeverage(List<Order> orders) {
        if (hasOnlyBeverage(orders)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER.getMessage());
        }
    }

    private boolean hasOnlyBeverage(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getMenuType() == MenuType.BEVERAGE)
                .count() == orders.size();
    }
}