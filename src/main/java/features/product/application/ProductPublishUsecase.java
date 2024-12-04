package features.product.application;

import features.product.domain.DraftProduct;
import features.product.domain.ProductRepository;
import features.product.domain.PublishedProduct;
import features.product.presentation.ProductPublishInput;

public class ProductPublishUsecase {

    ProductRepository productRepository = new ProductRepository();

    public void run(ProductPublishInput input) {
        DraftProduct draftProduct = productRepository.findDraftById(input.getId());
        PublishedProduct publishedProduct = draftProduct.publish();
        productRepository.save(publishedProduct);
    }
}
