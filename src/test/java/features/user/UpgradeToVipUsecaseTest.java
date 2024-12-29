package features.user;

import features.user.application.UpgradeToVipUsecase;
import features.user.application.UserSignupUsecase;
import features.user.domain.User;
import features.user.presentation.UserSignupInput;
import helpers.BaseTest;
import org.junit.jupiter.api.Test;
import shared.Records;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UpgradeToVipUsecaseTest extends BaseTest {

    @Test
    void upgradeToVip() {
        //given
        User loginUser = new UserDataBuilder().please();
        //when
        new UpgradeToVipUsecase().run(loginUser.id());
        //then
        Map record = db.findFirst("users");
        assertEquals(User.Plan.VIP.toString(), record.get("plan"));
    }

}