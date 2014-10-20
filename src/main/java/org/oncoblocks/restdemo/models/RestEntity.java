package org.oncoblocks.restdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

/**
 * Created by woemler on 10/20/14.
 */
public abstract class RestEntity {
	
	@JsonIgnore 
	public LinkedHashMap<String,Object> getAttributes() throws IllegalAccessException {
		LinkedHashMap<String,Object> attributes = new LinkedHashMap<>();
		for (Field field: this.getClass().getDeclaredFields()){
			field.setAccessible(true);
			attributes.put(field.getName(), field.get(this));
		}
		return attributes;
	}
	
	
}
