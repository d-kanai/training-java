package features.moneyFlow;

import features.moneyFlow.application.MoneyChargeUsecase;
import features.moneyFlow.domain.MoneyFlowRepository;
import features.moneyFlow.presentation.MoneyChargeInput;
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
        assertEquals(10000, MoneyFlowRepository.records.get(0).value());
    }

    @Test
    void _1万円より上は一度にチャージできない() {
        //given
        User loginUser = TestDataFactory.createUser();
        MoneyChargeInput input = new MoneyChargeInput(10001);
        //when
        try {
            new MoneyChargeUsecase().run(loginUser.id, input);
        } catch (RuntimeException e) {
            //then
            assertEquals("1度に1万円までしかチャージできません", e.getMessage());
            return;
        }
        fail("unexpected test fail");
    }

}


