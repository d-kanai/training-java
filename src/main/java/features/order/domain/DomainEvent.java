package features.order.domain;

public class DomainEvent {
    public final String eventName;
    private final Object payload;

    public DomainEvent(String eventName, Object payload) {
        this.eventName = eventName;
        this.payload = payload;
    }
}
