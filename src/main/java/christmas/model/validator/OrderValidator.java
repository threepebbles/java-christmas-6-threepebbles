package christmas.model.validator;

import christmas.constant.ErrorMessage;
import christmas.constant.MenuType;
import christmas.model.Menu;
import christmas.model.Order;
import java.util.List;

public class OrderValidator {
    private static OrderValidator orderValidator;

    private OrderValidator() {
    }

    public static OrderValidator getInstance() {
        if (orderValidator == null) {
            orderValidator = new OrderValidator();
        }
        return orderValidator;
    }

    public void validateOrders(List<Order> orders) {
        validateDuplication(orders);
        orders.forEach(order -> validatePositiveInteger(order.count()));
        validateHasOnlyBeverage(orders);
        orders.forEach(order -> validateOnMenu(order.menu()));
        validateOrderSize(orders);
    }

    private static void validateDuplication(List<Order> orders) {
        if (orders.stream()
                .map(Order::menu)
                .distinct()
                .count() != orders.size()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }

    private void validateOnMenu(Menu menu) {
        if (menu == Menu.NOTHING) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }

    private void validatePositiveInteger(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }

    private void validateOrderSize(List<Order> orders) {
        int MAX_ORDER_SIZE = 20;
        if (orders.stream()
                .mapToInt(Order::count)
                .sum() > MAX_ORDER_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }

    private void validateHasOnlyBeverage(List<Order> orders) {
        if (hasOnlyBeverage(orders)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }

    private boolean hasOnlyBeverage(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.menu().getMenuType() == MenuType.BEVERAGE)
                .count() == orders.size();
    }
}