package christmas.domain;

import christmas.constant.Menu;
import christmas.constant.MenuType;
import christmas.domain.DTO.OrderDTO;
import christmas.domain.validator.OrderValidator;

public class Order {
    private final Menu menu;
    private final int count;

    public Order(Menu menu, int count) {
        this.menu = menu;
        this.count = count;

        OrderValidator.getInstance().validateOrder(this);
    }

    public OrderDTO toDTO() {
        return new OrderDTO(getMenuName(), count);
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