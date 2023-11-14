package christmas.domain;

public enum EventBadge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NOTHING("", 0);

    private final String name;
    private final int threshold;

    EventBadge(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public static EventBadge getEventBadgeByTotalDiscount(int totalDiscount) {
        for (EventBadge eventBadge : values()) {
            if (totalDiscount >= eventBadge.threshold) {
                return eventBadge;
            }
        }
        return NOTHING;
    }

    public String getName() {
        return name;
    }
}