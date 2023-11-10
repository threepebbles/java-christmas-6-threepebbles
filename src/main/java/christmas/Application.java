package christmas;

import christmas.controller.EventPlanningController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        EventPlanningController eventPlanningController = new EventPlanningController(inputView, outputView);
        eventPlanningController.run();
    }
}