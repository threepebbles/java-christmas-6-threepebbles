package christmas.domain.dto.output;

public class OrderDTO {
    private String menuName;
    private int count;

    public OrderDTO(String menuName, int count) {
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
