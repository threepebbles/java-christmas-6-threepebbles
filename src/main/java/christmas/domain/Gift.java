package christmas.domain;

public enum Gift {
    CHAMPAGNE("샴페인", 25000),
    NOTHING("", 0);

    public static final String CURRENCY_UNIT = "원";
    private final String name;
    private final int price;

    Gift(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Gift valueOf(int totalPriceBeforeDiscount) {
        final int THRESHOLD = 120000;
        if (totalPriceBeforeDiscount >= THRESHOLD) {
            return Gift.CHAMPAGNE;
        }
        return NOTHING;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
