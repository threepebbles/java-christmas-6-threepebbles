package christmas.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.domain.Date;
import christmas.model.domain.DiscountDetails;
import christmas.model.domain.DiscountType;
import christmas.model.domain.EventBadge;
import christmas.model.domain.Gift;
import christmas.model.domain.Order;
import christmas.model.domain.discount.Discount;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OutputViewTest {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    private final OutputView outputView = new OutputView(new Presenter());
    private ByteArrayOutputStream output;

    @BeforeEach
    void setup() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void restore() {
        System.setOut(System.out);
        output.reset();
    }

    @DisplayName("이벤트 플래너 헤더 메세지 출력이 제대로 되는지 테스트한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20, 31})
    void 헤더_메세지_출력_테스트(int day) {
        // given
        Date date = new Date(day);

        String expected =
                String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", day) + LINE_SEPARATOR;

        // when
        outputView.updateEventPlanHeaderScreen(date);
        outputView.renderEventPlanHeaderScreen();
        String actual = output.toString();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주문 메뉴가 정상적으로 출력되는지 테스트한다.")
    @Test
    void 주문_메뉴_출력_테스트() {
        // given
        Order order = new Order();
        order.addMenu("티본스테이크", 2);
        order.addMenu("제로콜라", 2);
        String expected = "<주문 메뉴>" + LINE_SEPARATOR
                + "제로콜라 2개" + LINE_SEPARATOR
                + "티본스테이크 2개" + LINE_SEPARATOR;

        // when
        outputView.updateOrderScreen(order);
        outputView.renderOrderScreen();
        String actual = output.toString();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("할인 전 총주문 금액이 정상적으로 출력되는지 테스트한다.")
    @Test
    void 할인_전_총_주문_금액_출력_테스트() {
        // given
        int amount = 100203;
        String expected = "<할인 전 총주문 금액>" + LINE_SEPARATOR
                + "100,203원" + LINE_SEPARATOR;

        // when
        outputView.updateTotalPriceBeforeDiscountScreen(amount);
        outputView.renderTotalPriceBeforeDiscountScreen();
        String actual = output.toString();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("증정 메뉴가 있는 경우 정상적으로 출력되는지 테스트한다.")
    @Test
    void 증정_메뉴_있는_경우_출력_테스트() {
        // given
        Gift gift = Gift.CHAMPAGNE;
        String expected = "<증정 메뉴>" + LINE_SEPARATOR
                + "샴페인 1개" + LINE_SEPARATOR;

        // when
        outputView.updateGiftScreen(gift);
        outputView.renderGiftScreen();
        String actual = output.toString();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("증정 메뉴가 없는 경우 정상적으로 출력되는지 테스트한다.")
    @Test
    void 증정_메뉴_없는_경우_출력_테스트() {
        // given
        Gift gift = Gift.NOTHING;
        String expected = "<증정 메뉴>" + LINE_SEPARATOR
                + "없음" + LINE_SEPARATOR;

        // when
        outputView.updateGiftScreen(gift);
        outputView.renderGiftScreen();
        String actual = output.toString();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("혜택 내역이 있는 경우 정상적으로 출력되는지 테스트한다.")
    @Test
    void 혜택_내역_있는_경우_출력_테스트() {
        // given
        List<Discount> discounts = new ArrayList<>() {{
            add(new Discount(DiscountType.CHRISTMAS_D_DAY, 1010));
            add(new Discount(DiscountType.SPECIAL, 2020));
            add(new Discount(DiscountType.GIFT, 3030));
        }};
        DiscountDetails discountDetails = new DiscountDetails(discounts);
        String expected = "<혜택 내역>" + LINE_SEPARATOR
                + "크리스마스 디데이 할인: -1,010원" + LINE_SEPARATOR
                + "특별 할인: -2,020원" + LINE_SEPARATOR
                + "증정 이벤트: -3,030원" + LINE_SEPARATOR;

        // when
        outputView.updateDiscountDetailsScreen(discountDetails);
        outputView.renderDiscountDetailsScreen();
        String actual = output.toString();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("총혜택 금액이 정상적으로 출력되는지 테스트한다.")
    @Test
    void 총혜택_금액_출력_테스트() {
        // given
        int totalDiscount = 102030;
        String expected = "<총혜택 금액>" + LINE_SEPARATOR
                + "-102,030원" + LINE_SEPARATOR;

        // when
        outputView.updateTotalDiscountScreen(totalDiscount);
        outputView.renderTotalDiscountScreen();
        String actual = output.toString();

        // then;
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("할인 후 예상 결제 금액이 정상적으로 출력되는지 테스트한다.")
    @Test
    void 할인_후_예상_결제_금액_출력_테스트() {
        // given
        int expectedPay = 52000;
        String expected = "<할인 후 예상 결제 금액>" + LINE_SEPARATOR
                + "52,000원" + LINE_SEPARATOR;

        // when
        outputView.updateExpectedPayAfterDiscountScreen(expectedPay);
        outputView.renderExpectedPayAfterDiscountScreen();
        String actual = output.toString();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("12월 이벤트 배지가 정상적으로 출력되는지 테스트한다.")
    @Test
    void 이벤트_배지_출력_테스트() {
        // given
        EventBadge eventBadge = EventBadge.STAR;
        String expected = "<12월 이벤트 배지>" + LINE_SEPARATOR
                + "별" + LINE_SEPARATOR;

        // when
        outputView.updateEventBadgeScreen(eventBadge);
        outputView.renderEventBadgeScreen();
        String actual = output.toString();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}