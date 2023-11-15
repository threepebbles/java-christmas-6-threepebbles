package christmas.domain.dto.input;

public class OrderInputDTO {
    private final String menuName;
    private final int count;

    public OrderInputDTO(String menuName, int count) {
        this.menuName = menuName;
        this.count = count;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getCount() {
        return count;
    }
}