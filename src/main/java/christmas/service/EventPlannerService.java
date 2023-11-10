package christmas.service;

import christmas.controller.InputController;
import christmas.controller.OutputController;
import christmas.domain.Date;
import christmas.domain.Order;

public class EventPlannerService {
    InputController inputController;
    OutputController outputController;

    public EventPlannerService(InputController inputController, OutputController outputController) {
        this.inputController = inputController;
        this.outputController = outputController;
    }

    public void run() {
        Date date = inputController.askDayOfVisit();
        System.out.println("day: " + date.getDay());
        Order order = inputController.askOrder();
        order.getMenuCounter().forEach((menu, count) -> {
            System.out.print("menu: " + menu.toString());
            System.out.println(", count: " + count);
        });
    }
}
