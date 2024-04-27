package product.application;

import product.domain.Product;
import product.domain.ProductRepository;
import product.presentation.ProductCreateInput;

import java.util.UUID;

public class ProductCreateUsecase {
    public boolean run(UUID loginUserId, ProductCreateInput input) {
        Product product = Product.draft(loginUserId, input);
        return new ProductRepository().save(product);
    }
}
