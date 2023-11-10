package christmas;

import christmas.controller.InputController;
import christmas.controller.OutputController;
import christmas.service.EventPlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputController inputController = new InputController(new InputView());
        OutputController outputController = new OutputController(new OutputView());
        EventPlannerService eventPlannerService = new EventPlannerService(inputController, outputController);
        eventPlannerService.run();
    }
}
