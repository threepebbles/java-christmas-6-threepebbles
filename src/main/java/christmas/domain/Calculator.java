package christmas.domain;

import java.util.Map;

public class Calculator {
    // 주문한 메뉴 금액들의 합
    public int calculateTotalPriceBeforeDiscount(Order order) {
        int sum = 0;
        Map<Menu, Integer> menuCounter = order.getMenuCounter();
        for (Menu menu : menuCounter.keySet()) {
            sum += menu.getPrice() * menuCounter.get(menu);
        }
        return sum;
    }
}
