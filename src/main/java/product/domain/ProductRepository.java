package product.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductRepository {
    public static List<Product> records = new ArrayList();

    public boolean save(Product product) {
        if (product.id == null) {
            records.add(product);
        } else {
            Product product1 = records.stream().filter(record -> record.id == product.id).findFirst().get();
            product1 = product;
        }
        return true;
    }

    public Optional<Product> findById(UUID productId) {
        Optional<Product> first = records.stream().filter(product -> product.id == productId).findFirst();
        return first;
    }
}
