package features.user.domain;

import java.util.UUID;

public class VipUser extends User {
    private VipUser(UUID id, String name, UserPlan userPlan) {
        super(id, name, userPlan);
    }

    public static VipUser reconstruct(UUID uuid, String name, UserPlan userPlan) {
        return new VipUser(
                uuid,
                name,
                userPlan
        );
    }


}
