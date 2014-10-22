package org.oncoblocks.restdemo.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Abstract superclass for domain models used in REST API.  Adds annotations and methods for more 
 *  precise serialization.
 *  
 * TODO: Possibly switch to JAXB annotations only.  Jackson supports it with an additional dependency: https://github.com/FasterXML/jackson-module-jaxb-annotations.  XStream might support this as well.  
 * 
 * Created by woemler on 10/20/14.
 */

@JsonAutoDetect(
		creatorVisibility = JsonAutoDetect.Visibility.NONE, 
		fieldVisibility = JsonAutoDetect.Visibility.NONE,
		getterVisibility = JsonAutoDetect.Visibility.NONE,
		isGetterVisibility = JsonAutoDetect.Visibility.NONE,
		setterVisibility = JsonAutoDetect.Visibility.NONE
)
public class RestEntity extends ResourceSupport {
	
	private Set<String> fieldSet;

	public void setFieldSet(Set<String> fieldSet) {
		this.fieldSet = fieldSet;
	}

	@JsonProperty
	public Map<String, Object> getAttributes(){
		
		Class<?> clazz = this.getClass();
		Map<String, Object> attributeMap = new LinkedHashMap<>();
		Set<String> fieldSet = new HashSet<>();
		
		if (this.fieldSet != null && !this.fieldSet.isEmpty()){
			fieldSet = this.fieldSet;
		}
		
		try {
			for (Field field : clazz.getDeclaredFields()) {
				if (fieldSet.isEmpty() || fieldSet.contains(field.getName())) {
					field.setAccessible(true);
					attributeMap.put(field.getName(), field.get(this));
				}
			}
		} catch (IllegalAccessException e){
			e.printStackTrace();
		}
		
		return attributeMap;
		
	}
	
}
