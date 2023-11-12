package christmas.model.event;

import christmas.constant.DiscountType;

public class DiscountEvent {
    protected DiscountType discountType;
    protected int amount;

    public DiscountEvent(DiscountType discountType, int amount) {
        this.discountType = discountType;
        this.amount = amount;
    }

    public DiscountEvent(DiscountType discountType) {
        this.discountType = discountType;
    }

    public String getName() {
        return discountType.getName();
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public int getAmount() {
        return amount;
    }
}