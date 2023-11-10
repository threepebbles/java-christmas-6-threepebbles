package christmas.domain;

import christmas.constant.ErrorMessage;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Order {
    Map<Menu, Integer> menuCounter;

    public Order() {
        menuCounter = new TreeMap<>(
                Comparator.comparing(Menu::getName)
        );
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
        return menuCounter.keySet().stream()
                .filter(menu -> menu.getMenuType() == menuType)
                .mapToInt(menu -> menuCounter.get(menu))
                .sum();
    }

    public int calculateTotalPriceBeforeDiscount() {
        return menuCounter.keySet().stream()
                .mapToInt(menu -> menu.getPrice() * menuCounter.get(menu))
                .sum();
    }

    public Map<Menu, Integer> getMenuCounter() {
        return menuCounter;
    }
}