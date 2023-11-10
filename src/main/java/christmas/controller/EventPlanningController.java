package christmas.controller;

import christmas.model.Date;
import christmas.model.DiscountDetails;
import christmas.model.EventBadge;
import christmas.model.Gift;
import christmas.model.Order;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;

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
        final int MINIMUM_REQUIRED_AMOUNT_TO_PLAN_EVENTS = 10000;

        if (order.calculateTotalPrice() < MINIMUM_REQUIRED_AMOUNT_TO_PLAN_EVENTS) {
            planningWithoutEvents(date, order);
            return;
        }
        planningEvents(date, order);
    }

    private void planningEvents(Date date, Order order) {
        outputView.printEventStatisticsHeader(date);
        outputView.printOrder(order);

        int totalPriceBeforeDiscount = order.calculateTotalPrice();
        outputView.printTotalPriceBeforeDiscount(totalPriceBeforeDiscount);

        Gift gift = Gift.valueOf(totalPriceBeforeDiscount);
        outputView.printGift(gift);

        DiscountDetails discountDetails = DiscountDetails.createDiscountDetails(date, order);
        outputView.printDiscountDetails(discountDetails);

        int totalDiscount = discountDetails.calculateTotalDiscount();
        outputView.printTotalDiscount(totalDiscount);

        int expectedPayAfterDiscount = totalPriceBeforeDiscount - discountDetails.calculateTotalDiscountWithoutGift();
        outputView.printExpectedPayAfterDiscount(expectedPayAfterDiscount);

        EventBadge eventBadge = EventBadge.valueOf(totalDiscount);
        outputView.printEventBadge(eventBadge);
    }

    private void planningWithoutEvents(Date date, Order order) {
        outputView.printEventStatisticsHeader(date);
        outputView.printOrder(order);
        outputView.printTotalPriceBeforeDiscount(0);
        outputView.printGift(Gift.NOTHING);
        outputView.printDiscountDetails(new DiscountDetails(new ArrayList<>()));
        outputView.printTotalDiscount(0);
        outputView.printExpectedPayAfterDiscount(order.calculateTotalPrice());
        outputView.printEventBadge(EventBadge.NOTHING);
    }
}