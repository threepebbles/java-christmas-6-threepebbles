package christmas.Service;

import christmas.Service.planner.DefaultPlanner;
import christmas.Service.planner.EventPlanner;
import christmas.Service.planner.Planner;
import christmas.model.Date;
import christmas.model.EventPlan;
import christmas.model.Orders;

public class EventPlanningService {
    public EventPlan createEventPlan(Date date, Orders orders) {
        Planner planner = matchPlanner(date, orders);
        return new EventPlan(
                planner.getDate(),
                planner.getOrders(),
                planner.calculateTotalPriceBeforeDiscount(),
                planner.calculateGift(),
                planner.calculateDiscountDetails(),
                planner.calculateTotalDiscount(),
                planner.calculateExpectedPayAfterDiscount(),
                planner.calculateEventBadge());
    }

    private Planner matchPlanner(Date date, Orders orders) {
        EventPlanner eventPlanner = new EventPlanner(date, orders);
        if (eventPlanner.isEnoughAmount(orders.calculateTotalPrice())) {
            return eventPlanner;
        }
        return new DefaultPlanner(date, orders);
    }
}