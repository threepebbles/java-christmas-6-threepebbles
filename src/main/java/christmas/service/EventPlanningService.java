package christmas.service;

import christmas.domain.Date;
import christmas.domain.EventPlan;
import christmas.domain.Orders;
import christmas.service.planner.DefaultPlanner;
import christmas.service.planner.EventPlanner;
import christmas.service.planner.Planner;

public class EventPlanningService {
    public EventPlan createEventPlan(Date date, Orders orders) {
        Planner planner = matchPlanner(date, orders);
        return new EventPlan(
                planner.getDate(),
                planner.getOrders(),
                planner.calculateTotalPriceBeforeDiscount(),
                planner.requestGift(),
                planner.calculateDiscountResults(),
                planner.calculateTotalDiscount(),
                planner.calculateExpectedAmountAfterDiscount(),
                planner.requestEventBadge());
    }

    private Planner matchPlanner(Date date, Orders orders) {
        EventPlanner eventPlanner = new EventPlanner(date, orders);
        if (eventPlanner.isEnoughAmount(orders.calculateTotalPrice())) {
            return eventPlanner;
        }
        return new DefaultPlanner(date, orders);
    }
}