package product;

import org.junit.jupiter.api.Test;
import product.domain.Product;
import product.domain.ProductRepository;
import product.presentation.ProductCreateInput;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductCreateUsecaseTest {

    @Test
    void Signup成功() {
        //given
        ProductCreateInput input = new ProductCreateInput("book", 1000);
        //when
        boolean actual = new ProductCreateUsecase().run(input);
        //then
        assertEquals(true, actual);
        assertEquals(1, ProductRepository.records.size());
    }

    private class ProductCreateUsecase {
        public boolean run(ProductCreateInput input) {
            Product product = Product.draft(input);
            return new ProductRepository().save(product);
        }
    }

}

