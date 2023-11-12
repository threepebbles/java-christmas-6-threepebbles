package christmas.controller;

import christmas.Service.EventPlanningService;
import christmas.model.Date;
import christmas.model.EventPlanDTO;
import christmas.model.Orders;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    EventPlanningService eventPlanningService;

    public MainController(InputView inputView, OutputView outputView, EventPlanningService eventPlanningService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventPlanningService = eventPlanningService;
    }

    public void run() {
        Date date = inputView.askDate();
        Orders orders = inputView.askOrders();

        EventPlanDTO eventPlanDTO = eventPlanningService.createEventPlan(date, orders);
        updateEventPlanView(eventPlanDTO);
        renderEventPlanView();
    }

    private void updateEventPlanView(EventPlanDTO eventPlanDTO) {
        outputView.updateEventPlanHeaderScreen(eventPlanDTO.getDate());
        outputView.updateOrderScreen(eventPlanDTO.getOrders());
        outputView.updateTotalPriceBeforeDiscountScreen(eventPlanDTO.getTotalPriceBeforeDiscount());
        outputView.updateGiftScreen(eventPlanDTO.getGift());
        outputView.updateDiscountDetailsScreen(eventPlanDTO.getDiscountDetails());
        outputView.updateTotalDiscountScreen(eventPlanDTO.getTotalDiscount());
        outputView.updateExpectedPayAfterDiscountScreen(eventPlanDTO.getExpectedPayAfterDiscount());
        outputView.updateEventBadgeScreen(eventPlanDTO.getEventBadge());
    }

    private void renderEventPlanView() {
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