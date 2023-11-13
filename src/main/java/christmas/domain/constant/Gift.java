package christmas.domain.constant;

import christmas.controller.dto.GiftDTO;

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

    public GiftDTO toDTO() {
        return new GiftDTO(name, 1);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
