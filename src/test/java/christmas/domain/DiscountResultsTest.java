package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.constant.EventType;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class DiscountResultsTest {
    @Test
    void 총할인_가격_계산_테스트() {
        DiscountResults discountResults = new DiscountResults(
                new ArrayList<>() {{
                    add(new DiscountResult(EventType.WEEKEND_DISCOUNT, 2023));
                    add(new DiscountResult(EventType.SPECIAL_DISCOUNT, 1000));
                    add(new DiscountResult(EventType.GIFT, 25000));
                }}
        );
        int expected = 2023 + 1000 + 25000;

        // when
        int actual = discountResults.calculateTotalDiscount();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 증정_메뉴_가격을_제외한_총할인_가격_계산_테스트() {
        DiscountResults discountResults = new DiscountResults(
                new ArrayList<>() {{
                    add(new DiscountResult(EventType.WEEKEND_DISCOUNT, 2023));
                    add(new DiscountResult(EventType.SPECIAL_DISCOUNT, 1000));
                    add(new DiscountResult(EventType.GIFT, 25000));
                }}
        );
        int expected = 2023 + 1000;

        // when
        int actual = discountResults.calculateTotalDiscountWithoutGift();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}