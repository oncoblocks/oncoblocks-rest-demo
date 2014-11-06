package org.oncoblocks.restdemo.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by woemler on 10/14/14.
 */

@ControllerAdvice
public class RestExceptionHandler {

	/**
	 * Exception handler for basic REST API errors, returning the appropriate HTTP status code and 
	 *  informative messages.
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = {
			ResourceNotFoundException.class, 
			MalformedEntityException.class, 
			RequestFailureException.class
	})
	public ResponseEntity<RestError> handleBasicExceptions(
			ResourceNotFoundException ex,
			WebRequest request){
		RestError restError = ex.getRestError();
		return new ResponseEntity<RestError>(restError, restError.getStatus());
	}
		
}
