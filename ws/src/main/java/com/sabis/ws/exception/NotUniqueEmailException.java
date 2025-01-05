package com.sabis.ws.exception;

import java.util.Collections;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;

import com.sabis.ws.shared.Messages;

/**
 * @author: tokay
 */
public class NotUniqueEmailException extends RuntimeException {
    public NotUniqueEmailException() {
        super(Messages.getMessageForLocale("sabis.constraints.email.notunique", LocaleContextHolder.getLocale()));
    }

    public Map<String, String> getValidationErrors() {
        return Collections.singletonMap("email", Messages.getMessageForLocale("sabis.constraints.email.notunique",
                LocaleContextHolder.getLocale()));
    }
}
