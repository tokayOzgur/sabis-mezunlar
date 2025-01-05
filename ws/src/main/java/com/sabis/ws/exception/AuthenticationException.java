package com.sabis.ws.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sabis.ws.shared.Messages;

/**
 * @author: tokay
 */
@ResponseStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super(Messages.getMessageForLocale("sabis.authentication.error.message", LocaleContextHolder.getLocale()));
    }

}
