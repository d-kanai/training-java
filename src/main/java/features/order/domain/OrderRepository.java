package features.order.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    public static List<Order> records = new ArrayList();

    public void save(Order order) {
        records.add(order);
    }
}
