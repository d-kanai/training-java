package features.moneyFlow;

import features.moneyFlow.application.CurrentMoneyUsecase;
import features.user.domain.User;
import helpers.TestDataFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentMoneyUsecaseTestFlow {

    @Test
    void チャージ残高を取得する() {
        //given
        User loginUser = TestDataFactory.createUser();
        TestDataFactory.createMoneyFlow(loginUser.id);
        TestDataFactory.createMoneyFlow(loginUser.id);
        //when
        int totalValue = new CurrentMoneyUsecase().run(loginUser.id);
        //then
        assertEquals(20000, totalValue);
    }

}


