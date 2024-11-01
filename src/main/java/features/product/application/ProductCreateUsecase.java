package features.product.application;

import features.product.domain.ProductRepository;
import features.product.presentation.ProductCreateInput;

public class ProductCreateUsecase {

    public void run(ProductCreateInput input) {
        new ProductRepository().save(input);
    }
}
