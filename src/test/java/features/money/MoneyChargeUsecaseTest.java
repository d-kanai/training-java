package features.money;

import features.money.application.MoneyChargeUsecase;
import features.money.domain.MoneyRepository;
import features.money.presentation.MoneyChargeInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.domain.ProductStatus;
import features.user.domain.User;
import helpers.TestDataFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MoneyChargeUsecaseTest {

    @Test
    void お金をチャージする() {
        //given
        User loginUser = TestDataFactory.createUser();
        MoneyChargeInput input = new MoneyChargeInput(10000);
        //when
        new MoneyChargeUsecase().run(loginUser.id, input);
        //then
        assertEquals(10000, MoneyRepository.records.get(0).value());
    }

}


