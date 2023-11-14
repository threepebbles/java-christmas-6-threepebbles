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
        String menuName = menuCount.get(0);
        String count = menuCount.get(1);
        validateBlank(menuName);
        validateBlank(count);
        validateInteger(count);
    }

    private void validateBlank(String target) {
        if (target.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER.getMessage());
        }
    }

    private void validateInteger(String count) {
        try {
            Integer.parseInt(count);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER.getMessage());
        }
    }
}