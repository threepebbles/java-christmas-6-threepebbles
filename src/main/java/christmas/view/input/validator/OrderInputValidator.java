package christmas.view.input.validator;

import christmas.constant.ErrorMessage;
import christmas.utils.Parser;
import java.util.List;

public class OrderInputValidator implements InputValidator {
    public static OrderInputValidator orderInputValidator;

    private OrderInputValidator() {
    }

    public static OrderInputValidator getInstance() {
        if (orderInputValidator == null) {
            orderInputValidator = new OrderInputValidator();
        }
        return orderInputValidator;
    }

    @Override
    public void validate(String target) {
        String COMMA = ",";
        List<String> menuCounts = Parser.parseWithDelimiter(target, COMMA);
        menuCounts.forEach(this::validateMenuCountFormat);
    }

    private void validateMenuCountFormat(String target) {
        String HYPHEN = "-";
        List<String> menuCount = Parser.parseWithDelimiter(target, HYPHEN);
        if (menuCount.size() != 2) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER.getMessage());
        }
        String count = menuCount.get(1);
        validateInteger(count);
    }

    private void validateInteger(String count) {
        try {
            Integer.parseInt(count);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER.getMessage());
        }
    }
}
