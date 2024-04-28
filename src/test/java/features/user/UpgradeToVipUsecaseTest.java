package features.user;

import features.user.application.UpgradeToVipUsecase;
import features.user.domain.User;
import features.user.domain.UserPlan;
import features.user.domain.UserRepository;
import helpers.TestDataFactory;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpgradeToVipUsecaseTest {

    @Test
    void VIPになる() {
        //given
        User loginUser = TestDataFactory.createUser();
        //when
        new UpgradeToVipUsecase().run(loginUser.id);
        //then
        assertEquals(1, UserRepository.records.size());
        assertEquals(UserPlan.VIP, UserRepository.records.get(0).userPlan);
    }
}
