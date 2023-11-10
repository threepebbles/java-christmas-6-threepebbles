package christmas.model.validator;

import christmas.constant.ErrorMessage;
import christmas.model.Menu;
import christmas.model.Order;
import java.util.Map;

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

    public void validate(Order order) {
        Map<Menu, Integer> menuCounter = order.getMenuCounter();
        menuCounter.keySet().forEach(this::validateOnMenu);
        menuCounter.values().forEach(this::validatePositiveInteger);

        validateOrderSize(order);
        validateHasOnlyBeverage(order);
        validateDuplication(order);
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

    private void validateOrderSize(Order order) {
        int MAX_ORDER_SIZE = 20;
        if (order.totalCountOfMenu() >= MAX_ORDER_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }

    private void validateHasOnlyBeverage(Order order) {
        if (order.hasOnlyBeverage()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }

    private void validateDuplication(Order order) {
        if (order.getMenuCounter().keySet().stream()
                .distinct().count() != order.getMenuCounter().size()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }
}