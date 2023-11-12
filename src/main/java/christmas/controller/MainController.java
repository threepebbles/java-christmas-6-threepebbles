package christmas.controller;

import christmas.domain.Date;
import christmas.domain.EventPlan;
import christmas.domain.Orders;
import christmas.service.EventPlanningService;
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

        EventPlan eventPlan = eventPlanningService.createEventPlan(date, orders);
        outputView.updateEventPlanView(eventPlan);
        outputView.renderEventPlanView();
    }
}