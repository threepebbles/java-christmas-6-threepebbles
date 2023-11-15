package christmas.domain.dto.output;

public class GiftDTO {
    private final String giftName;
    private final int count;

    public GiftDTO(String giftName, int count) {
        this.giftName = giftName;
        this.count = count;
    }

    public String getGiftName() {
        return giftName;
    }

    public int getCount() {
        return count;
    }
}