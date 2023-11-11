package christmas;

import christmas.controller.MainController;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import christmas.view.output.Presenter;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView(new Presenter());
        MainController mainController = new MainController(inputView, outputView);
        mainController.run();
    }
}