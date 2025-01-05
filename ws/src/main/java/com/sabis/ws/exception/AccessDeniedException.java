package com.sabis.ws.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sabis.ws.shared.Messages;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super(Messages.getMessageForLocale("sabis.access.denied.error.message", LocaleContextHolder.getLocale()));
    }

}
