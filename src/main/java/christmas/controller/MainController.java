package christmas.controller;

import christmas.domain.Date;
import christmas.domain.EventPlan;
import christmas.domain.Orders;
import christmas.service.EventPlanningService;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import java.time.DateTimeException;
import java.util.function.Function;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    private final EventPlanningService eventPlanningService;

    public MainController(InputView inputView, OutputView outputView, EventPlanningService eventPlanningService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventPlanningService = eventPlanningService;
    }

    public void run() {
        Date date = askDate();
        Orders orders = askOrders();

        EventPlan eventPlan = eventPlanningService.createEventPlan(date, orders);
        
        outputView.updateEventPlanView(eventPlan.toDTO());
        outputView.renderEventPlanView();
    }

    private Date askDate() {
        return (Date) retryUntilSuccess(inputView,
                inputView -> Date.createDate(inputView.askDateInputDTO())
        );
    }

    private Orders askOrders() {
        return (Orders) retryUntilSuccess(inputView,
                inputView -> Orders.createOrders(inputView.askOrdersInputDTO())
        );
    }

    private Object retryUntilSuccess(InputView inputView, Function<InputView, Object> function) {
        while (true) {
            try {
                return function.apply(inputView);
            } catch (DateTimeException | IllegalArgumentException e) {
                inputView.printMessage(e.getMessage());
            }
        }
    }
}