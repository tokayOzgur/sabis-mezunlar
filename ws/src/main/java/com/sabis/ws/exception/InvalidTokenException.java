package com.sabis.ws.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.sabis.ws.shared.Messages;

/**
 * @author: tokay
 */
public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super(Messages.getMessageForLocale("sabis.activate.user.invalid.token", LocaleContextHolder.getLocale()));
    }
}
