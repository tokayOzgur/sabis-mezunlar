
package com.sabis.ws.exception;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * @author tokay
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
	private int status;

	private String message;

	private String path;

	private long timestam = new Date().getTime();

	private Map<String, String> validationErrors = null;

}
