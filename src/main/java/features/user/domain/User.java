package features.user.domain;

import java.util.UUID;

public class User {
    private final UUID id;
    private final Email email;
    private Plan plan;

    public static User reconstruct(UUID id, String email, Plan plan) {
        return new User(id, email, plan);
    }

    public UUID id() {
        return id;
    }

    public void upgradeToVip() {
        //TODO: 状態毎の型？
        this.plan = Plan.VIP;
    }

    public enum Plan {
        NORMAL,
        VIP;

        public static User.Plan fromString(String status) {
            if (status == null) throw new IllegalArgumentException("Status cannot be null");
            try {
                return User.Plan.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unknown status: " + status, e);
            }
        }
    }


    private User(UUID id, String email, Plan plan) {
        this.id = id;
        this.email = Email.create(email);
        this.plan = plan;
    }

    public static User signup(String email) {
        return new User(UUID.randomUUID(), email, Plan.NORMAL);
    }

    public Plan plan() {
        return plan;
    }

    public Email email() {
        return email;
    }
}
