package christmas.controller;

import christmas.model.Date;
import christmas.model.Orders;
import christmas.model.planner.DefaultPlanner;
import christmas.model.planner.EventPlanner;
import christmas.model.planner.Planner;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Date date = inputView.askDate();
        Orders orders = inputView.askOrders();

        startPlanning(date, orders);
        renderEventPlan();
    }

    private void startPlanning(Date date, Orders orders) {
        outputView.updateEventPlanHeaderScreen(date);
        outputView.updateOrderScreen(orders);
        outputView.updateTotalPriceBeforeDiscountScreen(orders.calculateTotalPrice());
        EventPlanner eventPlanner = new EventPlanner(date, orders);
        if (eventPlanner.isRequired(orders.calculateTotalPrice())) {
            planningEvents(eventPlanner);
            return;
        }
        DefaultPlanner defaultPlanner = new DefaultPlanner(orders);
        planningEvents(defaultPlanner);
    }

    private void planningEvents(Planner planner) {
        outputView.updateGiftScreen(planner.calculateGift());
        outputView.updateDiscountDetailsScreen(planner.calculateDiscountDetails());
        outputView.updateTotalDiscountScreen(planner.calculateTotalDiscount());
        outputView.updateExpectedPayAfterDiscountScreen(planner.calculateExpectedPayAfterDiscount());
        outputView.updateEventBadgeScreen(planner.calculateEventBadge());
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