package christmas.controller;

import christmas.model.Date;
import christmas.model.DiscountDetails;
import christmas.model.EventBadge;
import christmas.model.EventPlanner;
import christmas.model.Gift;
import christmas.model.Order;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

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

        outputView.updateEventPlanHeaderScreen(date);
        outputView.updateOrderScreen(order);
        if (order.calculateTotalPrice() < MINIMUM_REQUIRED_AMOUNT_TO_APPLY_EVENTS) {
            planningWithoutEvents(date, order);
            renderEventPlan();
            return;
        }
        planningEvents(date, order);
        renderEventPlan();
    }

    private void planningEvents(Date date, Order order) {
        EventPlanner eventPlanner = new EventPlanner(date, order);

        outputView.updateTotalPriceBeforeDiscountScreen(eventPlanner.calculateTotalPriceBeforeDiscount());
        outputView.updateGiftScreen(eventPlanner.calculateGift());
        outputView.updateDiscountDetailsScreen(eventPlanner.calculateDiscountDetails());
        outputView.updateTotalDiscountScreen(eventPlanner.calculateTotalDiscount());
        outputView.updateExpectedPayAfterDiscountScreen(eventPlanner.calculateExpectedPayAfterDiscount());
        outputView.updateEventBadgeScreen(eventPlanner.calculateEventBadge());
    }

    private void planningWithoutEvents(Date date, Order order) {
        outputView.updateTotalPriceBeforeDiscountScreen(0);
        outputView.updateGiftScreen(Gift.NOTHING);
        outputView.updateDiscountDetailsScreen(DiscountDetails.createEmptyDiscountDetails());
        outputView.updateTotalDiscountScreen(0);
        outputView.updateExpectedPayAfterDiscountScreen(order.calculateTotalPrice());
        outputView.updateEventBadgeScreen(EventBadge.NOTHING);
    }

    private void renderEventPlan() {
        renderWithLineSeparator(outputView::renderEventPlanHeaderScreen, 1);
        renderWithLineSeparator(outputView::renderOrderScreen, 1);
        renderWithLineSeparator(outputView::renderTotalPriceBeforeDiscountScreen, 1);
        renderWithLineSeparator(outputView::renderGiftScreen, 1);
        renderWithLineSeparator(outputView::renderDiscountDetailsScreen, 1);
        renderWithLineSeparator(outputView::renderTotalDiscountScreen, 1);
        renderWithLineSeparator(outputView::renderExpectedPayAfterDiscountScreen, 1);
        outputView.renderEventBadgeScreen();
    }

    public void renderWithLineSeparator(Runnable toRun, int lineSeparatorCount) {
        toRun.run();
        outputView.printLineSeparator(lineSeparatorCount);
    }
}