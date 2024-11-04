package shared;

import features.order.domain.DomainEvent;

public class EventPublisher implements IEventPublisher {
    public void publish(DomainEvent domainEvent) {
        System.out.println("event published: " + domainEvent.eventName);
    }
}
