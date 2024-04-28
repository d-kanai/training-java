package features.user.domain;

import features.moneyFlow.domain.MoneyFlows;
import features.user.presentation.SignupInput;

import java.util.UUID;

public class User {
    public UUID id;
    public final String name;
    public UserPlan userPlan;
    public String email;

    private User(UUID id, String name, UserPlan userPlan) {
        this.id = id;
        this.name = name;
        this.userPlan = userPlan;
    }

    public static User signup(SignupInput signupInput) {
        return new User(
                UUID.randomUUID(),
                signupInput.name,
                UserPlan.STANDARD
        );
    }

    public static User reconstruct(UUID uuid, String name, UserPlan userPlan) {
        return new User(
                uuid,
                name,
                userPlan
        );
    }

    public void upgradeToVip(MoneyFlows moneyFlows) {
        if (moneyFlows.currentValue() < 10000) throw new RuntimeException("VIPになる条件を満たしていません");
        userPlan = UserPlan.VIP;
    }
}
