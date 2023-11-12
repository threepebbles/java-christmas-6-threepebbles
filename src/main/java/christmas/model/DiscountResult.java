package christmas.model;

import christmas.constant.EventType;

public class DiscountResult {
    private final EventType eventType;
    private final int amount;

    public DiscountResult(EventType eventType, int amount) {
        this.eventType = eventType;
        this.amount = amount;
    }
    
    public int getPriority() {
        return eventType.getPriority();
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getEventName() {
        return eventType.getName();
    }

    public int getAmount() {
        return amount;
    }
}