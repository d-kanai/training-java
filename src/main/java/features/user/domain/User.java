package features.user.domain;

import java.util.UUID;

public class User {
    private final UUID id;
    private final String email;
    private final Plan plan;

    public UUID id() {
        return null;
    }

    public enum Plan {
        NORMAL,
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
