package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import christmas.domain.constant.EventType;
import christmas.service.planner.EventPlanner;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventPlannerTest {
    private LocalDate localDate;
    private Date date;
    private Orders orders;
    private EventPlanner eventPlanner;

    @BeforeEach
    void init() {
        // given
        localDate = LocalDate.of(2023, 12, 25);
        date = new Date(localDate);
        orders = new Orders(
                List.of(
                        new Order(Menu.CAESAR_SALAD, 3),    // 8000 * 3
                        new Order(Menu.BARBECUE_LIP, 2),    // 54000 * 2
                        new Order(Menu.ZERO_COKE, 4)        // 3000 * 4
                )
        );
        eventPlanner = new EventPlanner(date, orders);

        /*
         * 할인 전 총주문 금액: 144,000원
         * 증정 메뉴: 샴페인 1개
         * 혜택 내역
         * 크리스마스 디데이 할인 3,400원
         * 특별 할인: 1,000원
         * 증정 이벤트: 25,000원
         * 총혜택 금액: 29,400원
         * 할인 후 예상 결제 금액 : 144,000 - 4,400 = 139,600;
         * 이벤트 배지: 산타
         */
    }

    @Test
    void 할인_전_총주문_계산_테스트() {
        int expected = 144000;

        // when
        int totalPriceBeforeDiscount = eventPlanner.calculateTotalPriceBeforeDiscount();

        // then
        assertThat(totalPriceBeforeDiscount).isEqualTo(expected);
    }

    @Test
    void 증정메뉴_계산_테스트() {
        Gift expected = Gift.CHAMPAGNE;

        // when
        Gift gift = eventPlanner.requestGift();

        // then
        assertThat(gift).isEqualTo(expected);
    }

    @Test
    void 혜택내역_계산_테스트() {
        // when
        DiscountResults discountResults = eventPlanner.calculateDiscountResults();

        /* 혜택 내역
         * 크리스마스 디데이 할인: 3,400원
         * 특별 할인: 1,000원
         * 증정 이벤트: 25,000원
         */

        // then
        assertThatCode(() -> {
                    DiscountResult christmasDiscount = discountResults
                            .getDiscountResults()
                            .get(0);
                    if (christmasDiscount.getEventType() == EventType.CHRISTMAS_D_DAY_DISCOUNT
                            && christmasDiscount.getAmount() != 3400) {
                        throw new IllegalArgumentException("크리스마스 디데이 할인이 제대로 안 들어갔다.");
                    }
                    DiscountResult specialDiscount = discountResults
                            .getDiscountResults()
                            .get(1);
                    if (specialDiscount.getEventType() == EventType.SPECIAL_DISCOUNT
                            && specialDiscount.getAmount() != 1000) {
                        throw new IllegalArgumentException("특별 할인이 제대로 안 들어갔다.");
                    }
                    DiscountResult giftEvent = discountResults
                            .getDiscountResults()
                            .get(2);
                    if (giftEvent.getEventType() == EventType.GIFT
                            && giftEvent.getAmount() != 25000) {
                        throw new IllegalArgumentException("증정 이벤트 할인이 제대로 안 들어갔다.");
                    }
                }
        ).doesNotThrowAnyException();
    }

    @Test
    void 총혜택_금액_계산_테스트() {
        int expected = 29400;
        // when
        int totalDiscount = eventPlanner.calculateTotalDiscount();

        // then
        assertThat(totalDiscount).isEqualTo(expected);
    }

    @Test
    void 할인_후_예상_결제_금액_계산_테스트() {
        int expected = 139600;
        //when
        int actual = eventPlanner.calculateExpectedPriceAfterDiscount();
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 이벤트_배지_테스트() {
        EventBadge expected = EventBadge.SANTA;
        // when
        EventBadge actual = eventPlanner.requestEventBadge();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}