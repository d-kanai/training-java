package features.order.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderRepository {
    public static List<Order> records = new ArrayList();

    public void save(Order order) {
        records.add(order);
    }

    public List<Order> findByUserId(UUID loginUserId) {
        return records.stream().filter(order -> order.userId == loginUserId).collect(Collectors.toList());
    }
}
