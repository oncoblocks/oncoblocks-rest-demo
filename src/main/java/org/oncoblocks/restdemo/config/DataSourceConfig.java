package org.oncoblocks.restdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by woemler on 10/8/14.
 */

@Configuration
public class DataSourceConfig {
	
	@Bean
	public DataSource dataSource(){
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScripts("classpath:db/schema.sql", 
						"classpath:db/gene_insert.sql", 
						"classpath:db/cell_lines_insert.sql", 
						"classpath:db/expression_insert.sql")
				.build();
	}

}
