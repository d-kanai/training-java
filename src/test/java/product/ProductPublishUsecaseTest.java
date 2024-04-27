package product;

import org.junit.jupiter.api.Test;
import product.application.ProductCreateUsecase;
import product.domain.ProductRepository;
import product.presentation.ProductCreateInput;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductPublishUsecaseTest {

    @Test
    void 商品を公開する() {
        //given
        ProductCreateInput input = new ProductCreateInput("book", 1000);
        //when
//        boolean actual = new ProductCreateUsecase().run(input);
//        //then
//        assertEquals(true, actual);
//        assertEquals(1, ProductRepository.records.size());
    }

}

