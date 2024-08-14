package io.study.springbootlayered.web.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.study.springbootlayered.web.base.Events;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class EventConfig {

    public final ApplicationEventPublisher publisher;

    @Bean
    public InitializingBean eventInitializer() {
        return () -> Events.setPublisher(publisher);
    }
}
