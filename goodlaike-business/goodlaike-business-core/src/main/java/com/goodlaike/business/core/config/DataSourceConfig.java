package com.goodlaike.business.core.config;

import javax.annotation.PreDestroy;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @author Jail Hu
 */
@Configuration
@PropertySource("classpath:core-config.properties")
public class DataSourceConfig extends DataSource {
	
	@PreDestroy
	protected void destory() {
		super.close();
	}
}
