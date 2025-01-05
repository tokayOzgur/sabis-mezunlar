package com.sabis.ws.exception;

import java.util.Collections;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;

import com.sabis.ws.shared.Messages;

public class NotUniqueUsernameException extends RuntimeException {
    public NotUniqueUsernameException() {
        super(Messages.getMessageForLocale("sabis.constraints.username.notunique", LocaleContextHolder.getLocale()));
    }

    public Map<String, String> getValidationErrors() {
        return Collections.singletonMap("username",
                Messages.getMessageForLocale("sabis.constraints.username.notunique",
                        LocaleContextHolder.getLocale()));
    }
}