package org.oncoblocks.restdemo.util;

import org.oncoblocks.restdemo.models.RestEntity;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;

/**
 * HttpMessageConverter implementation for converting Spring HATEOAS Resource-wrapped classes into
 *   plain delimited-text responses. 
 * 
 * Created by woemler on 10/20/14.
 */
public class TextMessageConverter extends AbstractHttpMessageConverter<Object> {
	
	private String delimiter = "\t";
	private MediaType mediaType;
	
	public TextMessageConverter(MediaType mediaType){
		super(mediaType);
		this.mediaType = mediaType;

	}
	
	public void setDelimiter(String delimiter){
		this.delimiter = delimiter;
	}

	@Override 
	protected boolean supports(Class<?> aClass) {
		if (Resource.class.equals(aClass) || Resources.class.equals(aClass)){
			return true;
		} else {
			return false;
		}
	}

	@Override 
	protected void writeInternal(Object o, HttpOutputMessage output)
			throws IOException, HttpMessageNotWritableException {
		
		output.getHeaders().setContentType(this.mediaType);
		OutputStream out = output.getBody();
		PrintWriter writer = new PrintWriter(out);
		
		if (o.getClass().equals(Resources.class)){

			// List of Resource objects
			boolean showHeader = true;
			for (Resource<Object> resource: (Resources<Resource<Object>>) o){
				Object entity = resource.getContent();
				String entityString;
				try {
					entityString = printEntityRecord(entity, this.delimiter, showHeader);
				} catch (IllegalAccessException e){
					e.printStackTrace();
					entityString = "# Invalid record.";
				}
				writer.write(entityString);
				showHeader = false;
			}
			
		} else {

			// Single Resource object
			Object entity = ((Resource<RestEntity>) o).getContent();
			String entityString;
			try {
				entityString = printEntityRecord(entity, this.delimiter, true);
			} catch (IllegalAccessException e){
				e.printStackTrace();
				entityString = "# Invalid record.";
			}
			writer.write(entityString);
			
		}
		
		writer.close();
		
	}

	@Override 
	protected Object readInternal(Class<?> aClass, HttpInputMessage httpInputMessage)
			throws IOException, HttpMessageNotReadableException {
		return null;
	}

	private String printEntityRecord(Object entity, String delimiter, boolean showHeader) throws IllegalAccessException{
		StringBuffer buffer = new StringBuffer();
		if (showHeader){
			for (Field field: entity.getClass().getDeclaredFields()){
				buffer.append(field.getName() + delimiter);
			}
			buffer.append("\n");
		}
		for (Field field: entity.getClass().getDeclaredFields()){
			field.setAccessible(true);
			buffer.append(field.get(entity) + delimiter);
		}
		buffer.append("\n");
		return buffer.toString();
	}
	
}
