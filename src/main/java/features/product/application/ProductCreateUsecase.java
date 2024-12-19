package features.product.application;

import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.presentation.ProductCreateInput;

// ・Add Column migration
// ・Enum in Domain, not All constants dir
// ・named constructor

public class ProductCreateUsecase {

    public void run(ProductCreateInput input) {
        Product product = Product.createDraft(input.name, input.price);
        new ProductRepository().save(product);
    }
}
