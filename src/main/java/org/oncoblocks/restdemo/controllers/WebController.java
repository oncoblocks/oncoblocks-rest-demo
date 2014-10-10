package org.oncoblocks.restdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by woemler on 10/6/14.
 */

@Controller
public class WebController {

	/**
	 * Redirects to the Swagger API docs
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String apiDocs(){
		return "redirect:/docs/index.html";
	}
	
}
