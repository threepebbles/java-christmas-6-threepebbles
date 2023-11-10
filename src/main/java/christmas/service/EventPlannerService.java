package christmas.service;

import christmas.controller.InputController;
import christmas.controller.OutputController;
import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.discount.Discount;
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
        System.out.println("day: " + date.getDay());
        Order order = inputController.askOrder();

        // 이벤트 적용하기
        outputController.printEventStatisticsHeader();
        // 1. 주문 받은 메뉴 목록 출력
        outputController.printOrder(order);

        // 2. 할인 전 총 주문 금액 계산 및 출력
        int totalPriceBeforeDiscount = order.calculateTotalPriceBeforeDiscount();
        outputController.printTotalPriceBeforeDiscount(totalPriceBeforeDiscount);

        // 3. 증정 메뉴 존재 유무 판단 출력
        Menu gift = requestGift(totalPriceBeforeDiscount);
        outputController.printGift(gift);

        // 4. 할인 혜택 내역 계산 및 출력
        Discounter discounter = new Discounter();
        List<Discount> discounts = discounter.calculateAllDiscounts(date, order);
        outputController.printDiscountDetails(discounts);

        // 5. 총 혜택 금액 계산 및 출력
        int totalDiscount = calculateTotalDiscount(discounts);
        outputController.printTotalDiscount(totalDiscount);
    }

    private int calculateTotalDiscount(List<Discount> discounts) {
        return discounts.stream()
                .mapToInt(Discount::getAmount)
                .sum();
    }

    private Menu requestGift(int totalPriceBeforeDiscount) {
        final int THRESHOLD = 120000;
        if (totalPriceBeforeDiscount >= THRESHOLD) {
            return Menu.CHAMPAGNE;
        }
        return null;
    }
}
