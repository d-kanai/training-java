package helpers;

import features.order.domain.DomainEvent;
import shared.IEventPublisher;

public class FakeEventPublisher implements IEventPublisher {
    public int callCount;
    public DomainEvent argsDomainEvent;


    @Override
    public void publish(DomainEvent domainEvent) {
        callCount++;
        this.argsDomainEvent = domainEvent;
    }
}
