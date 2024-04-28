package features.money;

import features.user.domain.User;
import helpers.TestDataFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentMoneyUsecaseTest {

    @Test
    void チャージ残高を取得する() {
        //given
        User loginUser = TestDataFactory.createUser();
        TestDataFactory.createMoney(loginUser.id);
        TestDataFactory.createMoney(loginUser.id);
        //when
        int totalValue = new CurrentMoneyUsecase().run(loginUser.id);
        //then
        assertEquals(20000, totalValue);
    }

}


