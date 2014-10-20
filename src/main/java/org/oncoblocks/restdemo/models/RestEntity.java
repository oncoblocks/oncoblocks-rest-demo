package org.oncoblocks.restdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedHashMap;

/**
 * Created by woemler on 10/20/14.
 */
public interface RestEntity {
	
	String toText(String delimiter, boolean showHeader);
	@JsonIgnore LinkedHashMap<String,Object> getAttributes();
	
	
}
