package christmas.domain;

public class Discount {
    private final DiscountType discountType;
    private final int amount;

    public Discount(DiscountType discountType, int amount) {
        this.discountType = discountType;
        this.amount = amount;
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
