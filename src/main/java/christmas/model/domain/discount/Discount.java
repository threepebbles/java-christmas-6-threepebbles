package christmas.model.domain.discount;

import christmas.model.domain.DiscountType;

public class Discount {
    protected DiscountType discountType;
    protected int amount;

    public Discount(DiscountType discountType, int amount) {
        this.discountType = discountType;
        this.amount = amount;
    }

    public Discount(DiscountType discountType) {
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