package christmas.domain;

import christmas.constant.ErrorMessage;
import java.util.HashMap;
import java.util.Map;

public class Order {
    Map<Menu, Integer> menuCounter;

    public Order() {
        menuCounter = new HashMap<>();
    }

    public void addMenu(String name, int count) {
        Menu menu = Menu.findMenuByName(name);
        validateOnMenu(menu);
        validateDuplication(menu);

        menuCounter.put(menu, count);
    }

    private void validateOnMenu(Menu menu) {
        if (menu == null) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }

    private void validateDuplication(Menu menu) {
        if (menuCounter.get(menu) != null) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }

    public int countByMenuType(MenuType menuType) {
        int count = 0;
        for (Menu menu : menuCounter.keySet()) {
            if (menu.getMenuType() == menuType) {
                count += menuCounter.get(menu);
            }
        }
        return count;
    }

    public int calculateTotalPriceBeforeDiscount() {
        int sum = 0;
        for (Menu menu : menuCounter.keySet()) {
            sum += menu.getPrice() * menuCounter.get(menu);
        }
        return sum;
    }

    public Map<Menu, Integer> getMenuCounter() {
        return menuCounter;
    }
}