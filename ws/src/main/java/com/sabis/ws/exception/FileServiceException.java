package com.sabis.ws.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sabis.ws.shared.Messages;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileServiceException extends RuntimeException {
    public FileServiceException(String message) {
        super(Messages.getMessageForLocale(message, LocaleContextHolder.getLocale()));
    }

}
