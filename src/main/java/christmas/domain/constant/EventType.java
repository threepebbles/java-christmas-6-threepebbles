package christmas.domain.constant;

public enum EventType {
    CHRISTMAS_D_DAY_DISCOUNT(1, "크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT(2, "평일 할인"),
    WEEKEND_DISCOUNT(3, "주말 할인"),
    SPECIAL_DISCOUNT(4, "특별 할인"),
    GIFT(5, "증정 이벤트");

    private final int priority;
    private final String name;

    EventType(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }
}
