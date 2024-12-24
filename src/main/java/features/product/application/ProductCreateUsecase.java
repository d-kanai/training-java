package features.product.application;

import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.presentation.ProductCreateInput;

public class ProductCreateUsecase {

    public void run(ProductCreateInput input) {
        if (input.price < 0) {
            throw new RuntimeException("マイナスは登録できません");
        }
        Product product = new Product(input.name, input.price);
        new ProductRepository().save(product);
    }
}
