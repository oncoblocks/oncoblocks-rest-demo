package org.oncoblocks.restdemo.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by woemler on 10/15/14.
 */
public class RequestFailureException extends RestException{
	public RequestFailureException(Integer code, String message, String developerMessage, String moreInfoUrl) {
		super(HttpStatus.BAD_REQUEST, code, message, developerMessage, moreInfoUrl);
	}
}
