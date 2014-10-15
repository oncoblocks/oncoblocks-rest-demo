package org.oncoblocks.restdemo.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by woemler on 10/14/14.
 */
public class ResourceNotFoundException extends RestException {
	
	public ResourceNotFoundException(Integer code, String message, String developerMessage, String moreInfoUrl){
		super(HttpStatus.NOT_FOUND, code, message, developerMessage, moreInfoUrl);
	}
	
}
