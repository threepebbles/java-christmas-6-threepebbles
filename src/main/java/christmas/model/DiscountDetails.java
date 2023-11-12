package christmas.model;

import christmas.constant.EventType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DiscountDetails {
    List<DiscountResult> details;

    public DiscountDetails(List<DiscountResult> details) {
        this.details = details.stream()
                .sorted(Comparator.comparing(DiscountResult::getPriority))
                .toList();
    }

    public static DiscountDetails createEmptyDiscountDetails() {
        return new DiscountDetails(new ArrayList<>());
    }


    public int calculateTotalDiscount() {
        return details.stream()
                .mapToInt(DiscountResult::getAmount)
                .sum();
    }

    public int calculateTotalDiscountWithoutGift() {
        return details.stream()
                .filter(DiscountResult -> DiscountResult.getEventType() != EventType.GIFT)
                .mapToInt(DiscountResult::getAmount)
                .sum();
    }

    public List<DiscountResult> getDetails() {
        return details;
    }
}