package org.oncoblocks.restdemo.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by woemler on 10/15/14.
 */
public class MalformedEntityException extends RestException {
	public MalformedEntityException(Integer code, String message, String developerMessage, String moreInfoUrl) {
		super(HttpStatus.NOT_ACCEPTABLE, code, message, developerMessage, moreInfoUrl);
	}
}
