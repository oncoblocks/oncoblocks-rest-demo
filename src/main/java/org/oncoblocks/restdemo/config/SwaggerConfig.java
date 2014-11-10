package org.oncoblocks.restdemo.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by woemler on 10/5/14.
 */


@Configuration
@EnableSwagger
@EnableWebMvc
public class SwaggerConfig {

	private SpringSwaggerConfig springSwaggerConfig;

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	@Bean
	public SwaggerSpringMvcPlugin customImplementation(){

		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
				.apiInfo(apiInfo())
				.includePatterns(".*api.*");
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"Oncoblocks REST API, draft 0.1",
				"Draft proposal of an Oncoblocks-like web service built with Spring MVC, with REST API documentation by Swagger."
				+ "  Note that only dummy data is returned.",
				"terms.html",
				"woemler@blueprintmedicines.com",
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0.html"
		);
		return apiInfo;
	}

}
