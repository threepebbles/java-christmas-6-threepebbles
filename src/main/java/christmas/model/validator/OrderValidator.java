package christmas.model.validator;

import christmas.constant.ErrorMessage;
import christmas.constant.MenuType;
import christmas.model.Menu;
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

    public void validateMenuCounter(Map<Menu, Integer> menuCounter) {
        menuCounter.keySet().forEach(this::validateOnMenu);
        menuCounter.values().forEach(this::validatePositiveInteger);

        validateOrderSize(menuCounter);
        validateHasOnlyBeverage(menuCounter);
        validateDuplication(menuCounter);
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

    private void validateOrderSize(Map<Menu, Integer> menuCounter) {
        int MAX_ORDER_SIZE = 20;
        if (totalCountOfMenu(menuCounter) >= MAX_ORDER_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }

    private int totalCountOfMenu(Map<Menu, Integer> menuCounter) {
        return menuCounter.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void validateHasOnlyBeverage(Map<Menu, Integer> menuCounter) {
        if (hasOnlyBeverage(menuCounter)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }

    private boolean hasOnlyBeverage(Map<Menu, Integer> menuCounter) {
        return menuCounter.keySet().stream()
                .filter(menu -> menu.getMenuType() == MenuType.BEVERAGE)
                .distinct().count()
                == menuCounter.keySet().size();
    }

    private void validateDuplication(Map<Menu, Integer> menuCounter) {
        if (menuCounter.keySet().stream()
                .distinct().count() != menuCounter.size()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }
}