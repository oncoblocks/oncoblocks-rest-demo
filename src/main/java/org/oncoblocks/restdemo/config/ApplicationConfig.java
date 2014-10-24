package org.oncoblocks.restdemo.config;

import org.oncoblocks.restdemo.util.FilteringJackson2HttpMessageConverter;
import org.oncoblocks.restdemo.util.TextMessageConverter;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by woemler on 10/8/14.
 */

@Configuration
@Import({DataSourceConfig.class})
@EnableWebMvc
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@ComponentScan(basePackages = {"org.oncoblocks.restdemo"})
public class ApplicationConfig extends WebMvcConfigurerAdapter {
	
	/*
	@Override
	public void addInterceptors(final InterceptorRegistry registry){
		registry.addInterceptor(new FieldFilterInterceptor());
	}
	*/
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
		registry.addResourceHandler("*.html").addResourceLocations("/");
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}
	
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean(name = "mapper")
	public PropertiesFactoryBean mapper(){
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocations(
				new ClassPathResource("config/data-source.properties"), 
				new ClassPathResource("config/app.properties"));
		return bean;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter< ?>> converters) {

		// XML
		List<MediaType> mediaType = new ArrayList<>();
		mediaType.add(MediaType.APPLICATION_XML);

		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
		xmlConverter.setSupportedMediaTypes(mediaType);

		XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
		xmlConverter.setMarshaller(xstreamMarshaller);
		xmlConverter.setUnmarshaller(xstreamMarshaller);

		converters.add(xmlConverter);

		// JSON
		FilteringJackson2HttpMessageConverter jsonConverter = new FilteringJackson2HttpMessageConverter();
		jsonConverter.setPrettyPrint(true);
		converters.add(jsonConverter);
		
		// Text
		TextMessageConverter textConverter = 
				new TextMessageConverter(new MediaType("text", "plain", Charset.forName("utf-8")));
		textConverter.setDelimiter("\t");
		converters.add(textConverter);

		TextMessageConverter csvConverter = 
				new TextMessageConverter(new MediaType("text", "csv", Charset.forName("utf-8")));
		csvConverter.setDelimiter(",");
		converters.add(csvConverter);
		
	}


	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer){
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
		configurer.favorPathExtension(true);
		configurer.ignoreAcceptHeader(true);
		configurer.mediaType("txt", MediaType.TEXT_PLAIN);
		configurer.mediaType("csv", new MediaType("text", "csv"));
	}
	
}
