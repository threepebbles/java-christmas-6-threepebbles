package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.view.OutputView;
import java.util.Map;

public class OutputController {
    private final OutputView outputView;

    public OutputController(OutputView outputView) {
        this.outputView = outputView;
    }

    public void printEventStatisticsHeader() {
        outputView.printEventStatisticsHeader();
    }

    public void printOrder(Order order) {
        outputView.printOrder(createOrderText(order));
    }

    String createOrderText(Order order) {
        StringBuilder sb = new StringBuilder();
        Map<Menu, Integer> menuCounter = order.getMenuCounter();
        menuCounter.forEach((menu, count) -> {
            sb.append(menu.getName()).append(" ");
            sb.append(count).append("개")
                    .append("\n");
        });
        return sb.toString();
    }
}