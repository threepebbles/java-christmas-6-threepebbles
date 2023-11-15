package christmas.service;

import christmas.domain.Date;
import christmas.domain.EventPlan;
import christmas.domain.Orders;
import christmas.service.planner.DefaultPlanner;
import christmas.service.planner.EventPlanner;
import christmas.service.planner.Planner;

public class EventPlanningService {
    private static final int MINIMUM_REQUIRED_AMOUNT_TO_APPLY_EVENTS = 10000;

    public EventPlan createEventPlan(Date date, Orders orders) {
        Planner planner = matchPlanner(date, orders);
        return new EventPlan(
                planner.getDate(),
                planner.getOrders(),
                planner.calculateTotalPriceBeforeDiscount(),
                planner.requestGift(),
                planner.calculateDiscountResults(),
                planner.calculateTotalDiscount(),
                planner.calculateExpectedPriceAfterDiscount(),
                planner.requestEventBadge());
    }

    private Planner matchPlanner(Date date, Orders orders) {
        if (orders.calculateTotalPrice() >= MINIMUM_REQUIRED_AMOUNT_TO_APPLY_EVENTS) {
            return new EventPlanner(date, orders);
        }
        return new DefaultPlanner(date, orders);
    }
}