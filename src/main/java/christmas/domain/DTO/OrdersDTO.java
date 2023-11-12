package christmas.domain.DTO;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class OrdersDTO implements Iterable<OrderDTO> {
    private List<OrderDTO> orders;

    public OrdersDTO(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    @Override
    public Iterator<OrderDTO> iterator() {
        return orders.iterator();
    }

    @Override
    public void forEach(Consumer<? super OrderDTO> action) {
        orders.forEach(action);
    }
}
