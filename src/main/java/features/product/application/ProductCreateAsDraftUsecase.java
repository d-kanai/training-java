package features.product.application;

import features.product.domain.DraftProduct;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.presentation.ProductCreateInput;

public class ProductCreateAsDraftUsecase {

    public void run(ProductCreateInput input) {
        DraftProduct product = DraftProduct.create(input.name, input.price);
        new ProductRepository().save(product);
    }
}
