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

/**
 * Created by woemler on 10/20/14.
 */
public class TextMessageConverter extends AbstractHttpMessageConverter<Object> {
	
	//public static final MediaType MEDIA_TYPE = new MediaType("text", "plain", Charset.forName("utf-8"));
	private String delimiter = "\t";
	private MediaType mediaType;
	
	public TextMessageConverter(MediaType mediaType){
		super(mediaType);
		this.mediaType = mediaType;

	}
	
	public void setDelimiter(String delimiter){
		this.delimiter = delimiter;
	}

	@Override protected boolean supports(Class<?> aClass) {
		if (Resource.class.equals(aClass) || Resources.class.equals(aClass)){
			return true;
		} else {
			return false;
		}
	}

	@Override protected void writeInternal(Object o, HttpOutputMessage output)
			throws IOException, HttpMessageNotWritableException {
		
		output.getHeaders().setContentType(this.mediaType);
		OutputStream out = output.getBody();
		PrintWriter writer = new PrintWriter(out);
		if (o.getClass().equals(Resources.class)){
			boolean showHeader = true;
			for (Resource<RestEntity> resource: (Resources<Resource<RestEntity>>) o){
				RestEntity entity = resource.getContent();
				writer.write(entity.toText(this.delimiter, showHeader));
				showHeader = false;
			}
		} else {
			RestEntity entity = ((Resource<RestEntity>) o).getContent();
			writer.write(entity.toText(this.delimiter, true));
		}
		writer.close();
		
	}

	@Override protected Object readInternal(Class<?> aClass, HttpInputMessage httpInputMessage)
			throws IOException, HttpMessageNotReadableException {
		return null;
	}
}
