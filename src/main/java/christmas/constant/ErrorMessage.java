package christmas.constant;

public enum ErrorMessage {
    NOT_PROPER_DAY("유효하지 않은 날짜입니다."),
    NOT_PROPER_ORDER("유효하지 않은 주문입니다.");

    public static final String HEADER = "[ERROR]";
    public static final String WHITE_SPACE = " ";
    public static final String RETRY = "다시 입력해 주세요.";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return HEADER + WHITE_SPACE
                + message + WHITE_SPACE
                + RETRY;
    }
}