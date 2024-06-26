package features.user;

import features.moneyFlow.domain.MoneyFlowRepository;
import features.user.application.UpgradeToVipUsecase;
import features.user.domain.User;
import features.user.domain.UserPlan;
import features.user.domain.UserRepository;
import features.moneyFlow.MoneyFlowDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UpgradeToVipUsecaseTest {

    @BeforeEach
    void setup() {
        UserRepository.records = new ArrayList<>();
        MoneyFlowRepository.records = new ArrayList<>();
    }

    @Test
    void _1万以上購入してVIPになる() {
        //given
        User loginUser = new UserDataBuilder().please();
        new MoneyFlowDataBuilder(loginUser.id()).setPrice(10000).please();
        new MoneyFlowDataBuilder(loginUser.id()).setPrice(-5000).please();
        new MoneyFlowDataBuilder(loginUser.id()).setPrice(-5000).please();
        //when
        new UpgradeToVipUsecase().run(loginUser.id());
        //then
        assertEquals(1, UserRepository.records.size());
        assertEquals(UserPlan.VIP, UserRepository.records.get(0).userPlan());
    }

    @Test
    void _1万未満の購入履歴ではVIPになれない() {
        //given
        User loginUser = new UserDataBuilder().please();
        new MoneyFlowDataBuilder(loginUser.id()).setPrice(10000).please();
        new MoneyFlowDataBuilder(loginUser.id()).setPrice(-5000).please();
        new MoneyFlowDataBuilder(loginUser.id()).setPrice(-4999).please();
        //when
        try {
            new UpgradeToVipUsecase().run(loginUser.id());
        } catch (RuntimeException e) {
            //then
            assertEquals("VIPになる条件を満たしていません", e.getMessage());
            return;
        }
        fail("unexpected test fail");
    }
}
