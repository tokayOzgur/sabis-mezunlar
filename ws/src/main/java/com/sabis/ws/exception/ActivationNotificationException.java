package com.sabis.ws.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.sabis.ws.shared.Messages;

public class ActivationNotificationException extends RuntimeException {
    public ActivationNotificationException() {
        super(Messages.getMessageForLocale("sabis.create.user.email.failure", LocaleContextHolder.getLocale()));
    }

}
