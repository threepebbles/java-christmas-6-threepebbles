package christmas.domain.validator;

import christmas.constant.ErrorMessage;
import christmas.domain.Menu;
import christmas.domain.Order;

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

    public void validateOrder(Order order) {
        validateMenu(order.getMenu());
        validateCount(order.getCount());
    }

    private void validateMenu(Menu menu) {
        validateNotOnMenu(menu);
    }

    private void validateNotOnMenu(Menu menu) {
        if (menu == null || menu == Menu.NOTHING) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER.getMessage());
        }
    }

    private void validateCount(int count) {
        validatePositiveInteger(count);
    }

    private void validatePositiveInteger(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER.getMessage());
        }
    }
}