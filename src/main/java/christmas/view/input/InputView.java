package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.InputType;
import christmas.model.Date;
import christmas.model.Order;
import christmas.view.input.validator.InputValidatorFinder;
import java.util.function.Function;

public class InputView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String ENTER_DAY_OF_VISIT_TEXT = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
            + LINE_SEPARATOR
            + "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String ENTER_ORDER_TEXT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private final InputValidatorFinder inputValidatorFinder;
    private final InputConverter inputConverter;

    public InputView() {
        inputValidatorFinder = new InputValidatorFinder();
        inputConverter = new InputConverter();
    }

    public Date askDayOfVisit() {
        return (Date) retryUntilSuccess(
                inputView -> {
                    String day = scanDayOfVisit();
                    inputValidatorFinder.findValidatorByInputType(InputType.DAY_OF_VISIT)
                            .validate(day);
                    return inputConverter.createDate(day);
                });
    }

    private String scanDayOfVisit() {
        System.out.println(ENTER_DAY_OF_VISIT_TEXT);
        return Console.readLine();
    }

    public Order askOrder() {
        return (Order) retryUntilSuccess(
                inputView -> {
                    String order = inputView.scanOrder();
                    inputValidatorFinder.findValidatorByInputType(InputType.ORDER)
                            .validate(order);
                    return inputConverter.createOrder(order);
                });
    }

    private String scanOrder() {
        System.out.println(ENTER_ORDER_TEXT);
        return Console.readLine();
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private Object retryUntilSuccess(Function<InputView, Object> function) {
        while (true) {
            try {
                return function.apply(this);
            } catch (IllegalArgumentException e) {
                printMessage(e.getMessage());
            }
        }
    }
}