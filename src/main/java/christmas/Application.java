package christmas;

import christmas.controller.EventPlanningController;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import christmas.view.output.Presenter;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView(new Presenter());
        EventPlanningController eventPlanningController = new EventPlanningController(inputView, outputView);
        eventPlanningController.run();
    }
}