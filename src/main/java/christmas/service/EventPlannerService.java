package christmas.service;

import christmas.controller.InputController;
import christmas.controller.OutputController;
import christmas.domain.Calculator;
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

        // 이벤트 적용하기
        outputController.printEventStatisticsHeader();
        // 1. 주문 받은 메뉴 목록 출력
        outputController.printOrder(order);
        // 2. 할인 전 총 주문 금액 계산 및 출력
        Calculator calculator = new Calculator();
        outputController.printTotalPriceBeforeDiscount(calculator.calculateTotalPriceBeforeDiscount(order));
    }
}
