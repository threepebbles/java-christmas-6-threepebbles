package christmas.service;

import christmas.controller.InputController;
import christmas.controller.OutputController;
import christmas.domain.Date;
import christmas.domain.EventBadge;
import christmas.domain.Gift;
import christmas.domain.Order;
import christmas.domain.discount.Discount;
import christmas.domain.discount.DiscountType;
import christmas.domain.discount.Discounter;
import java.util.List;

public class EventPlannerService {
    InputController inputController;
    OutputController outputController;

    public EventPlannerService(InputController inputController, OutputController outputController) {
        this.inputController = inputController;
        this.outputController = outputController;
    }

    public void run() {
        Date date = inputController.askDayOfVisit();
        Order order = inputController.askOrder();

        planningEvent(order, date);
    }

    private void planningEvent(Order order, Date date) {
        outputController.printEventStatisticsHeader(date);
        // 1. 주문 받은 메뉴 목록 출력
        outputController.printOrder(order);

        // 2. 할인 전 총 주문 금액 계산 및 출력
        int totalPriceBeforeDiscount = order.calculateTotalPriceBeforeDiscount();
        outputController.printTotalPriceBeforeDiscount(totalPriceBeforeDiscount);

        // 3. 증정 메뉴 존재 유무 판단 출력
        Gift gift = Gift.valueOf(totalPriceBeforeDiscount);
        outputController.printGift(gift);

        // 4. 할인 혜택 내역 계산 및 출력
        Discounter discounter = new Discounter();
        List<Discount> discountDetails = discounter.calculateDiscountDetails(date, order);
        outputController.printDiscountDetails(discountDetails);

        // 5. 총 혜택 금액 계산 및 출력
        int totalDiscount = calculateTotalDiscount(discountDetails);
        outputController.printTotalDiscount(totalDiscount);

        // 6. 할인 후 예상 결제 금액
        int expectedPayAfterDiscount = totalPriceBeforeDiscount - calculateTotalDiscountWithoutGift(discountDetails);
        outputController.printExpectedPayAfterDiscount(expectedPayAfterDiscount);

        // 7. 12월 이벤트 배지
        EventBadge eventBadge = EventBadge.valueOf(totalDiscount);
        outputController.printEventBadge(eventBadge);
    }

    private int calculateTotalDiscountWithoutGift(List<Discount> discounts) {
        return discounts.stream()
                .filter(discount -> discount.getDiscountType() != DiscountType.GIFT)
                .mapToInt(Discount::getAmount)
                .sum();
    }

    private int calculateTotalDiscount(List<Discount> discounts) {
        return discounts.stream()
                .mapToInt(Discount::getAmount)
                .sum();
    }
}
