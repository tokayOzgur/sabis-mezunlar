package com.sabis.ws.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import com.sabis.ws.shared.Messages;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

/**
 * @author: tokay
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super(Messages.getMessageForLocale("sabis.resource.notfound.error.message",
                LocaleContextHolder.getLocale()));
    }

    public ResourceNotFoundException(String meessageKey) {
        super(Messages.getMessageForLocale(meessageKey,
                LocaleContextHolder.getLocale()));
    }
}
