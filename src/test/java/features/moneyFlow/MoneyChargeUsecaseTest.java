package features.moneyFlow;

import features.moneyFlow.application.MoneyChargeUsecase;
import features.moneyFlow.domain.MoneyFlowRepository;
import features.moneyFlow.presentation.MoneyChargeInput;
import features.user.domain.User;
import features.user.UserDataBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MoneyChargeUsecaseTest {

    @Test
    void お金をチャージする() {
        //given
        User loginUser = new UserDataBuilder().please();
        MoneyChargeInput input = new MoneyChargeInput(30000);
        //when
        new MoneyChargeUsecase().run(loginUser.id, input);
        //then
        assertEquals(30000, MoneyFlowRepository.records.get(0).value());
    }

    @Test
    void _3万円より上は一度にチャージできない() {
        //given
        User loginUser = new UserDataBuilder().please();
        MoneyChargeInput input = new MoneyChargeInput(30001);
        //when
        try {
            new MoneyChargeUsecase().run(loginUser.id, input);
        } catch (RuntimeException e) {
            //then
            assertEquals("1度に3万円までしかチャージできません", e.getMessage());
            return;
        }
        fail("unexpected test fail");
    }

}


