package features.product;

import features.product.domain.Product;
import features.product.domain.ProductRepository;

import java.util.UUID;

public class ProductDataBuilder {
    private int price = 1000;
    private Product.Status status = Product.Status.DRAFT;

    public Product please() {
        Product product = Product.reconstruct(UUID.randomUUID(), "book", price, status);
        new ProductRepository().save(product);
        return product;
    }

    public ProductDataBuilder price(int price) {
        this.price = price;
        return this;
    }

    public ProductDataBuilder status(Product.Status status) {
        this.status = status;
        return this;
    }
}
