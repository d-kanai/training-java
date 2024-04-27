package product.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static List<Product> records = new ArrayList();


    public boolean save(Product product) {
        records.add(product);
        return true;
    }
}
