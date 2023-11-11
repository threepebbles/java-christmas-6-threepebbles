package christmas.model.domain;

import christmas.model.validator.OrderValidator;
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

    public Order(Map<Menu, Integer> menuCounter) {
        this.menuCounter = new TreeMap<>(
                Comparator.comparing(Menu::getName)
        );
        this.menuCounter.putAll(menuCounter);

        OrderValidator.getInstance().validate(this);
    }

    public boolean hasOnlyBeverage() {
        return menuCounter.keySet().stream()
                .filter(menu -> menu.getMenuType() == MenuType.BEVERAGE)
                .distinct().count()
                == menuCounter.keySet().size();
    }


    public int totalCountOfMenu() {
        return menuCounter.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int countByMenuType(MenuType menuType) {
        return menuCounter.keySet().stream()
                .filter(menu -> menu.getMenuType() == menuType)
                .mapToInt(menu -> menuCounter.get(menu))
                .sum();
    }

    public int calculateTotalPrice() {
        return menuCounter.keySet().stream()
                .mapToInt(menu -> menu.getPrice() * menuCounter.get(menu))
                .sum();
    }

    public void addMenu(String name, int count) {
        Menu menu = Menu.findMenuByName(name);
        menuCounter.put(menu, count);
    }

    public Map<Menu, Integer> getMenuCounter() {
        return menuCounter;
    }
}