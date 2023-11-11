package christmas.model;

import christmas.constant.Menu;
import christmas.constant.MenuType;
import christmas.model.validator.OrderValidator;

public class Order {
    private final Menu menu;
    private final int count;

    public Order(Menu menu, int count) {
        OrderValidator.getInstance().validateMenu(menu);
        OrderValidator.getInstance().validateCount(count);

        this.menu = menu;
        this.count = count;
    }

    public int calculatePrice() {
        return menu.getPrice() * count;
    }

    public MenuType getMenuType() {
        return menu.getMenuType();
    }

    public String getMenuName() {
        return menu.getName();
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }
}