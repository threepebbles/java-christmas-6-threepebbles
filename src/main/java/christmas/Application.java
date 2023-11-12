package christmas;

import christmas.controller.MainController;
import christmas.service.EventPlanningService;
import christmas.view.input.InputConverter;
import christmas.view.input.InputValidatorFinder;
import christmas.view.input.InputView;
import christmas.view.output.OutputPresenter;
import christmas.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new InputValidatorFinder(), new InputConverter());
        OutputView outputView = new OutputView(new OutputPresenter());
        EventPlanningService eventPlanningService = new EventPlanningService();
        
        MainController mainController = new MainController(inputView, outputView, eventPlanningService);
        mainController.run();
    }
}