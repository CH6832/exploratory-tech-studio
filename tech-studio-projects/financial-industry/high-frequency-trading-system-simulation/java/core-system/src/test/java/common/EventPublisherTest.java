package common;

import com.hft.common.EventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class EventPublisherTest {

    private EventPublisher eventPublisher;
    private EventSubscriber mockSubscriber;

    @BeforeEach
    public void setup() {
        eventPublisher = new EventPublisher();
        mockSubscriber = mock(EventSubscriber.class);
    }

    @Test
    public void testSubscribe() {
        eventPublisher.subscribe(mockSubscriber);
        eventPublisher.publishEvent("Test Event");

        verify(mockSubscriber, times(1)).onEvent("Test Event");
    }

    @Test
    public void testUnsubscribe() {
        eventPublisher.subscribe(mockSubscriber);
        eventPublisher.unsubscribe(mockSubscriber);
        eventPublisher.publishEvent("Test Event");

        verify(mockSubscriber, never()).onEvent("Test Event");
    }

    @Test
    public void testPublishEvent() {
        eventPublisher.subscribe(mockSubscriber);
        eventPublisher.publishEvent("Test Event");

        verify(mockSubscriber, times(1)).onEvent("Test Event");
    }

    @Test
    public void testNoSubscribers() {
        eventPublisher.publishEvent("Test Event");

        // Since no subscribers are registered, nothing should happen
        // No verification needed, but ensure no exceptions are thrown
    }
}
