package christmas.controller.validator;

import christmas.constant.ErrorMessage;
import christmas.utils.Parser;
import java.util.List;

public class OrderValidator implements InputValidator {
    public static OrderValidator orderValidator;

    private OrderValidator() {
    }

    public static OrderValidator getInstance() {
        if (orderValidator == null) {
            orderValidator = new OrderValidator();
        }
        return orderValidator;
    }

    @Override
    public void validate(String target) {
        String COMMA = ",";
        List<String> menuCounts = Parser.parseWithDelimiter(target, COMMA);
        menuCounts.stream()
                .forEach(this::validateFormat);
    }

    private void validateFormat(String target) {
        String HYPHEN = "-";
        List<String> menuCount = Parser.parseWithDelimiter(target, HYPHEN);
        if (menuCount.size() != 2) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
        String count = menuCount.get(1);
        validateInteger(count);
    }

    private void validateInteger(String count) {
        try {
            Integer.parseInt(count);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
        }
    }
}
