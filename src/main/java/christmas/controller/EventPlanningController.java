package christmas.controller;

import christmas.model.domain.Date;
import christmas.model.domain.DiscountDetails;
import christmas.model.domain.EventBadge;
import christmas.model.domain.EventPlanner;
import christmas.model.domain.Gift;
import christmas.model.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanningController {
    private final InputView inputView;
    private final OutputView outputView;

    public EventPlanningController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Date date = inputView.askDayOfVisit();
        Order order = inputView.askOrder();

        startPlanning(date, order);
    }

    private void startPlanning(Date date, Order order) {
        final int MINIMUM_REQUIRED_AMOUNT_TO_APPLY_EVENTS = 10000;

        outputView.printEventStatisticsHeader(date);
        outputView.printOrder(order);
        if (order.calculateTotalPrice() < MINIMUM_REQUIRED_AMOUNT_TO_APPLY_EVENTS) {
            planningWithoutEvents(date, order);
            return;
        }
        planningEvents(date, order);
    }

    private void planningEvents(Date date, Order order) {
        EventPlanner eventPlanner = new EventPlanner(date, order);

        outputView.printTotalPriceBeforeDiscount(eventPlanner.calculateTotalPriceBeforeDiscount());
        outputView.printGift(eventPlanner.calculateGift());
        outputView.printDiscountDetails(eventPlanner.calculateDiscountDetails());
        outputView.printTotalDiscount(eventPlanner.calculateTotalDiscount());
        outputView.printExpectedPayAfterDiscount(eventPlanner.calculateExpectedPayAfterDiscount());
        outputView.printEventBadge(eventPlanner.calculateEventBadge());
    }

    private void planningWithoutEvents(Date date, Order order) {
        outputView.printTotalPriceBeforeDiscount(0);
        outputView.printGift(Gift.NOTHING);
        outputView.printDiscountDetails(DiscountDetails.createEmptyDiscountDetails());
        outputView.printTotalDiscount(0);
        outputView.printExpectedPayAfterDiscount(order.calculateTotalPrice());
        outputView.printEventBadge(EventBadge.NOTHING);
    }
}