package features.moneyFlow;

import features.moneyFlow.application.CurrentMoneyUsecase;
import features.user.domain.User;
import features.user.UserDataBuilder;
import helpers.TestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentMoneyUsecaseTestFlow extends TestBase {

    @Test
    void チャージ残高を取得する() {
        //given
        User loginUser = new UserDataBuilder().please();
        new MoneyFlowDataBuilder(loginUser.id()).please();
        new MoneyFlowDataBuilder(loginUser.id()).please();
        //when
        int totalValue = new CurrentMoneyUsecase().run(loginUser.id());
        //then
        assertEquals(20000, totalValue);
    }

}


