package com.cgi.api.dao.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
@EntityScan(value = {"com.cgi.api.dao.entity"})
@EnableMongoRepositories(value = {"com.cgi.api.dao.repository"})
public class DaoConfiguration {

}
