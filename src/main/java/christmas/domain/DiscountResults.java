package christmas.domain;

import christmas.domain.constant.EventType;
import christmas.domain.dto.output.DiscountResultsDTO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DiscountResults {
    private final List<DiscountResult> discountResults;

    public DiscountResults(List<DiscountResult> discountResults) {
        this.discountResults = discountResults.stream()
                .sorted(Comparator.comparing(DiscountResult::getPriority))
                .toList();
    }

    public static DiscountResults createEmptyDiscountResults() {
        return new DiscountResults(new ArrayList<>());
    }

    public DiscountResultsDTO toDTO() {
        return new DiscountResultsDTO(discountResults.stream().map(DiscountResult::toDTO).toList());
    }

    public int calculateTotalDiscount() {
        return discountResults.stream()
                .mapToInt(DiscountResult::getAmount)
                .sum();
    }

    public int calculateTotalDiscountWithoutGift() {
        return discountResults.stream()
                .filter(DiscountResult -> DiscountResult.getEventType() != EventType.GIFT)
                .mapToInt(DiscountResult::getAmount)
                .sum();
    }

    public List<DiscountResult> getDiscountResults() {
        return discountResults;
    }
}