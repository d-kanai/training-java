package features.user.domain;

import features.moneyFlow.domain.MoneyFlows;

import java.util.UUID;

public class StandardUser extends User {
    private StandardUser(UUID id, String name, UserPlan userPlan) {
        super(id, name, userPlan);
    }

    public static StandardUser reconstruct(UUID uuid, String name, UserPlan userPlan) {
        return new StandardUser(
                uuid,
                name,
                userPlan
        );
    }

    public PotentiallyVipUser challengeVip(MoneyFlows moneyFlows) {
        int i = moneyFlows.sumOfUsed();
        if (i < 10000) throw new RuntimeException("VIPになる条件を満たしていません");
        return PotentiallyVipUser.reconstruct(
                this.id(),
                this.name(),
                this.userPlan()
        );
    }
}
