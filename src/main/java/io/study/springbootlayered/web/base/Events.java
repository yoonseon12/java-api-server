package io.study.springbootlayered.web.base;

import org.springframework.context.ApplicationEventPublisher;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Events {
    private static ApplicationEventPublisher eventPublisher;

    public static void setPublisher(ApplicationEventPublisher applicationEventPublisher) {
        Events.eventPublisher = applicationEventPublisher;
    }

    public static void raise(Object event) {
        eventPublisher.publishEvent(event);
    }

}
