package christmas.service;

import christmas.controller.InputController;
import christmas.controller.OutputController;
import christmas.domain.Date;

public class EventPlannerService {
    InputController inputController;
    OutputController outputController;

    public EventPlannerService(InputController inputController, OutputController outputController) {
        this.inputController = inputController;
        this.outputController = outputController;
    }

    public void run() {
        Date date = inputController.askDayOfVisit();
        System.out.println(date.getDay());
    }
}
