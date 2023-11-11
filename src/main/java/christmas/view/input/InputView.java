package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.domain.Date;
import christmas.model.domain.Menu;
import christmas.model.domain.Order;
import christmas.utils.Parser;
import christmas.view.input.validator.DayOfVisitInputValidator;
import christmas.view.input.validator.OrderInputValidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class InputView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String COMMA = ",";
    public static final String HYPHEN = "-";
    public static final String ENTER_DAY_OF_VISIT_TEXT = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
            + LINE_SEPARATOR
            + "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String ENTER_ORDER_TEXT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public Date askDayOfVisit() {
        return (Date) retryUntilSuccess(
                inputView -> {
                    String day = scanDayOfVisit();
                    DayOfVisitInputValidator.getInstance().validate(day);
                    return createDate(day);
                });
    }

    private String scanDayOfVisit() {
        System.out.println(ENTER_DAY_OF_VISIT_TEXT);
        return Console.readLine();
    }

    private Date createDate(String userInput) {
        return new Date(Integer.parseInt(userInput));
    }

    public Order askOrder() {
        return (Order) retryUntilSuccess(
                inputView -> {
                    String order = inputView.scanOrder();
                    OrderInputValidator.getInstance().validate(order);
                    return createOrder(order);
                });
    }

    private String scanOrder() {
        System.out.println(ENTER_ORDER_TEXT);
        return Console.readLine();
    }

    private Order createOrder(String userInput) {
        Map<Menu, Integer> menuCounter = convertToMenuCounter(userInput);
        return new Order(menuCounter);
    }

    private Map<Menu, Integer> convertToMenuCounter(String userInput) {
        Map<Menu, Integer> menuCounter = new HashMap<>();
        List<String> menuCountFormats = Parser.parseWithDelimiter(userInput, COMMA);
        menuCountFormats.forEach(menuCountFormat -> {
            List<String> menuCountBundle = Parser.parseWithDelimiter(menuCountFormat, HYPHEN);
            String menuName = menuCountBundle.get(0);
            int count = Integer.parseInt(menuCountBundle.get(1));
            menuCounter.put(Menu.findMenuByName(menuName), count);
        });
        return menuCounter;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public Object retryUntilSuccess(Function<InputView, Object> function) {
        while (true) {
            try {
                return function.apply(this);
            } catch (IllegalArgumentException e) {
                printMessage(e.getMessage());
            }
        }
    }
}