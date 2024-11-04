package shared;

import features.order.domain.DomainEvent;

public interface IEventPublisher {
    void publish(DomainEvent domainEvent);
}
