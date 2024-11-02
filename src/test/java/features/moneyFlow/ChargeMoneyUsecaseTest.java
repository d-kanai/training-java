package features.moneyFlow;

import features.moneyFlows.application.ChargeMoneyUsecase;
import features.moneyFlows.presentation.ChargeMoneyInput;
import helpers.BaseTest;
import org.junit.jupiter.api.Test;
import shared.Records;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChargeMoneyUsecaseTest extends BaseTest {

    @Test
    void チャージする() {
        //given
        ChargeMoneyInput input = new ChargeMoneyInput(1000);
        //when
        new ChargeMoneyUsecase().run(input);
        //then
        Records records = db.find("select * from moneyFlows");
        assertEquals(1, records.size());
        assertEquals(1000, records.first().get("value"));
    }

}

