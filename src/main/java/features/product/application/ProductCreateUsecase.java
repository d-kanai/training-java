package features.product.application;

import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.presentation.ProductCreateInput;

public class ProductCreateUsecase {

    public void run(ProductCreateInput input) {
        Product product = Product.create(input.name, input.price);
        new ProductRepository().save(product);
    }
}
