package christmas.domain.DTO;

public class GiftDTO {
    private String giftName;
    private int count;

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
