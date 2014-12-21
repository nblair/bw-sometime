/**
 * 
 */
package org.bedework.sometime.web.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * {@link Configuration} that provides the JDBC {@link DataSource}.
 * 
 * @author Nicholas Blair
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

	@Value("${dataSource.jndiName}")
	private String jndiName;
	/**
	 * 
	 * @return a {@link DataSource} retrieved via JNDI
	 */
	@Bean
    public DataSource dataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource(jndiName);
        return dataSource;
    }
	/**
	 * 
	 * @param dataSource
	 * @return our {@link PlatformTransactionManager}
	 */
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
