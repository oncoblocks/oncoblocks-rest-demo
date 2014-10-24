package org.oncoblocks.restdemo.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.oncoblocks.restdemo.models.RestEnvelope;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
		
			if (object instanceof RestEnvelope) {
				Object entity = ((RestEnvelope) object).getEntity();
				Set<String> fieldSet = ((RestEnvelope) object).getFieldSet();
				if (fieldSet != null && !fieldSet.isEmpty()) {
					FilterProvider filters = new SimpleFilterProvider().addFilter("restEntityFilter",
							SimpleBeanPropertyFilter.filterOutAllExcept(fieldSet)).setFailOnUnknownId(false);
					objectMapper.setFilters(filters);
				} else {
					FilterProvider filters = new SimpleFilterProvider().addFilter("restEntityFilter", 
							SimpleBeanPropertyFilter.serializeAllExcept()).setFailOnUnknownId(false);
					objectMapper.setFilters(filters);
				}
				objectMapper.writeValue(jsonGenerator, entity);

			} else if (object == null){
				jsonGenerator.writeNull();
			} else {
				objectMapper.writeValue(jsonGenerator, object);
			}
			
		} catch (JsonProcessingException e){
			e.printStackTrace();
			throw new HttpMessageNotWritableException("Could not write JSON: " + e.getMessage());
		}

	}
}
