package christmas.domain;

import christmas.domain.constant.MenuType;
import christmas.domain.dto.input.OrderInputDTO;
import christmas.domain.dto.output.OrderDTO;
import christmas.domain.validator.OrderValidator;

public class Order {
    private final Menu menu;
    private final int count;

    public Order(Menu menu, int count) {
        this.menu = menu;
        this.count = count;

        OrderValidator.getInstance().validateOrder(this);
    }

    public static Order createOrder(OrderInputDTO orderInputDTO) {
        Menu menu = Menu.findMenuByName(orderInputDTO.getMenuName());
        int count = orderInputDTO.getCount();
        return new Order(menu, count);
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