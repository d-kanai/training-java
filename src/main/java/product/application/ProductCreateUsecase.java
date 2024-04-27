package product.application;

import product.domain.Product;
import product.domain.ProductRepository;
import product.presentation.ProductCreateInput;

import java.util.UUID;

public class ProductCreateUsecase {
    public Product run(UUID loginUserId, ProductCreateInput input) {
        Product product = Product.draft(loginUserId, input);
        new ProductRepository().save(product);
        return product;
    }
}
