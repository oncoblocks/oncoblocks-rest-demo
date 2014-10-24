package org.oncoblocks.restdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

/**
 * Wrapper class for GET responses to REST API endpoints, allowing for field filtering.  Message 
 *   converters intercept the request, read the fieldSet values and serializes only the selected 
 *   attributes of the contained entity object.
 * 
 * Created by woemler on 10/22/14.
 */
public class RestEnvelope<T> {
	
	private Set<String> fieldSet;
	private T entity;

	public RestEnvelope(T entity) {
		this.entity = entity;
	}

	public T getEntity() {
		return entity;
	}

	@JsonIgnore
	public Set<String> getFieldSet() {
		return fieldSet;
	}

	public void setFieldSet(Set<String> fieldSet) {
		this.fieldSet = fieldSet;
	}

	public void setFields(String fields) {
		Set<String> fieldSet = new HashSet<>();
		if (fields != null) {
			for (String field : fields.split(",")) {
				fieldSet.add(field);
			}
		}
		this.fieldSet = fieldSet;
	}
}
