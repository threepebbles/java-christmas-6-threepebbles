package christmas.constant;

public enum ErrorMessage {
    NOT_PROPER_DAY_FORMAT("유효하지 않은 날짜입니다."),
    NOT_PROPER_ORDER_FORMAT("유효하지 않은 주문입니다.");

    public static final String HEADER = "[Error] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return HEADER + message;
    }
}
