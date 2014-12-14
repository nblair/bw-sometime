/**
 * 
 */
package org.bedework.sometime.web.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;

/**
 * {@link Configuration} that provides Oracle specific {@link Bean}s.
 * This configuration is activated with the "oracle" Spring {@link Profile}.
 * 
 * @author Nicholas Blair
 */
@Configuration
@Profile("oracle")
public class OracleDatabaseConfiguration {

	/**
	 * 
	 * @param dataSource
	 * @return the {@link DataFieldMaxValueIncrementer} providing schedule owner ids
	 */
	@Bean @Autowired @Qualifier("owners")
	public DataFieldMaxValueIncrementer ownerIdSequence(DataSource dataSource) {
		return new OracleSequenceMaxValueIncrementer(dataSource, "ownerid_seq");
	}
	/**
	 * 
	 * @param dataSource
	 * @return the {@link DataFieldMaxValueIncrementer} providing statistic event ids
	 */
	@Bean @Autowired @Qualifier("statistics")
	public DataFieldMaxValueIncrementer eventIdSequence(DataSource dataSource) {
		return new OracleSequenceMaxValueIncrementer(dataSource, "eventid_seq");
	}
	/**
	 * 
	 * @param dataSource
	 * @return the {@link DataFieldMaxValueIncrementer} providing reminder ids
	 */
	@Bean @Autowired @Qualifier("reminders")
	public DataFieldMaxValueIncrementer reminderIdSequence(DataSource dataSource) {
		return new OracleSequenceMaxValueIncrementer(dataSource, "reminderid_seq");
	}
}
