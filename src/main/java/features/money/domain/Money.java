package features.money.domain;

import java.util.UUID;

public class Money {
    public UUID id;
    public final UUID userId;
    public final int value;

    private Money(UUID id, UUID userId, int value) {
        this.id = id;
        this.userId = userId;
        this.value = value;
    }

    public static Money charge(UUID userId, int value) {
        return new Money(UUID.randomUUID(), userId, value);
    }
}
