/**
 * 
 */
package org.bedework.sometime.database.migrations;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

/**
 * {@link Configuration} for {@link FlywayDatabaseMigrationsTest}.
 * 
 * @author Nicholas Blair
 */
@Configuration
class TestConfiguration {

	@Bean Flyway flyway() {
		SingleConnectionDataSource dataSource = new SingleConnectionDataSource("jdbc:hsqldb:mem:bw", "sa", "", true);
		Flyway flyway = new Flyway();
		flyway.setDataSource(dataSource);
		flyway.setLocations("db/migration", "db/hsqldb");
		return flyway;
	}
}
