package org.oncoblocks.restdemo.test.models;

import org.junit.Test;
import org.oncoblocks.restdemo.config.ApplicationConfig;
import org.oncoblocks.restdemo.config.DataSourceConfig;
import org.oncoblocks.restdemo.config.SwaggerConfig;
import org.oncoblocks.restdemo.demo.PrettyPrintData;
import org.oncoblocks.restdemo.demo.RandomDataGenerator;
import org.oncoblocks.restdemo.models.EntrezGene;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;


/**
 * Created by woemler on 9/23/14.
 */

@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class, SwaggerConfig.class, DataSourceConfig.class})
public class EntrezGeneTests {
	
	@Test
	public void testRandomData(){

		for (int i = 0; i < 6; i++){
			EntrezGene gene = RandomDataGenerator.randomEntrezGene();
			Assert.notNull(gene);
			PrettyPrintData.printEntrezGene(gene);
		}
		
		
	}
	
}
