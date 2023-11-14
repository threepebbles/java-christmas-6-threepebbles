package christmas.domain.dto.output;

public class DiscountResultDTO {
    private String eventName;
    private int amount;

    public DiscountResultDTO(String eventName, int amount) {
        this.eventName = eventName;
        this.amount = amount;
    }

    public String getEventName() {
        return eventName;
    }

    public int getAmount() {
        return amount;
    }
}
