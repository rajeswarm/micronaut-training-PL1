package com.learn.mn.i18n;

import io.micronaut.context.MessageSource;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.i18n.ResourceBundleMessageSource;
import jakarta.inject.Singleton;

@Factory
public class MessageSourceFactory {
	
	@Singleton
    MessageSource createMessageSource() {
        return new ResourceBundleMessageSource("com.learn.mn.i18n.messages");
    }
}
