package christmas.domain.dto.input;

import java.util.List;

public class OrdersInputDTO {
    private List<OrderInputDTO> orders;

    public OrdersInputDTO(List<OrderInputDTO> orders) {
        this.orders = orders;
    }

    public List<OrderInputDTO> getOrders() {
        return orders;
    }
}
