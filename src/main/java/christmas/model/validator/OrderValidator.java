package christmas.model.validator;

import christmas.constant.ErrorMessage;
import christmas.constant.Menu;

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

    public void validateMenu(Menu menu) {
        validateNothing(menu);
    }

    private void validateNothing(Menu menu) {
        if (menu == Menu.NOTHING) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }

    public void validateCount(int count) {
        validatePositiveInteger(count);
    }

    private void validatePositiveInteger(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }
}