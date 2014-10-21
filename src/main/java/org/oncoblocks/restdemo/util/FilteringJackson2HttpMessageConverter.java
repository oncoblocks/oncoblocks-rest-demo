package org.oncoblocks.restdemo.util;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.oncoblocks.restdemo.models.RestEntity;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by woemler on 10/21/14.
 */

@Component
public class FilteringJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

	private boolean prefixJson = false;
	
	@Override 
	public void setPrefixJson(boolean prefixJson) {
		this.prefixJson = prefixJson;
		super.setPrefixJson(prefixJson);
	}

	@Override 
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		ObjectMapper objectMapper = getObjectMapper();
		JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(outputMessage.getBody());
		
		try {
			if (this.prefixJson) {
				jsonGenerator.writeRaw("{} && ");
			}
			Set<String> fieldSet = new HashSet<>();
			fieldSet.add("ccleName");
			objectMapper.addMixInAnnotations(RestEntity.class, PropertyFilterMixin.class);
			FilterProvider filters = new SimpleFilterProvider()
					.addFilter("filterPropertiesByName", SimpleBeanPropertyFilter.filterOutAllExcept(fieldSet));
			objectMapper.setFilters(filters);
			objectMapper.writeValue(jsonGenerator, object);
			
		} catch (JsonProcessingException e){
			throw new HttpMessageNotWritableException("Could not write JSON: " + e.getMessage());
		}
		
		
	}
}


@JsonFilter("filterPropertiesByName")
class PropertyFilterMixin { }
