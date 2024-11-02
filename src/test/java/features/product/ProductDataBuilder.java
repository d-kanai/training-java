package features.product;

import features.product.domain.Product;
import features.product.domain.ProductRepository;

public class ProductDataBuilder {
    private int price = 1000;

    public Product please() {
        Product product = Product.create("book", price);
        new ProductRepository().save(product);
        return product;
    }

    public ProductDataBuilder price(int price) {
        this.price = price;
        return this;
    }
}
