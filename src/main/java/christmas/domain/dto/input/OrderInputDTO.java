package christmas.domain.dto.input;

public class OrderInputDTO {
    private String menuName;
    private int count;

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
