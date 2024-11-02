package features.user.domain;

import java.util.UUID;

public class User {
    private final UUID id;
    private final String email;
    private Plan plan;

    public UUID id() {
        return id;
    }

    public void upgradeToVip() {
        this.plan = Plan.VIP;
    }

    public enum Plan {
        NORMAL,
        VIP
    }


    public User(UUID id, String email, Plan plan) {
        this.id = id;
        this.email = email;
        this.plan = plan;
    }

    public static User signup(String email) {
        return new User(UUID.randomUUID(), email, Plan.NORMAL);
    }

    public Plan plan() {
        return plan;
    }

    public String email() {
        return email;
    }
}
