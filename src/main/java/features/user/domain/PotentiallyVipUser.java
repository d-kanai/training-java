package features.user.domain;

import java.util.UUID;

public class PotentiallyVipUser extends User {
    private PotentiallyVipUser(UUID id, String name, UserPlan userPlan) {
        super(id, name, userPlan);
    }

    public static PotentiallyVipUser reconstruct(UUID uuid, String name, UserPlan userPlan) {
        return new PotentiallyVipUser(
                uuid,
                name,
                userPlan
        );
    }

    public VipUser upgradeToVip() {
        return VipUser.reconstruct(
                this.id,
                this.name,
                UserPlan.VIP
        );
    }

}
