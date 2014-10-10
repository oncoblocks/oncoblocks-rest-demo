package org.oncoblocks.restdemo.test.models;

import org.oncoblocks.restdemo.demo.PrettyPrintData;
import org.oncoblocks.restdemo.demo.RandomDataGenerator;
import org.oncoblocks.restdemo.models.EntrezGene;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;


/**
 * Created by woemler on 9/23/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/test-application-context.xml"})
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
