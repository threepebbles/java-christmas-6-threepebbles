package christmas.domain.dto.output;

import java.util.List;

public class OrdersDTO {
    private final List<OrderDTO> orders;

    public OrdersDTO(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }
}