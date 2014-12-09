/**
 * 
 */
package org.bedework.sometime.database.migrations;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.flywaydb.core.Flyway;


/**
 * Flyway backed class responsible for executing database migration.
 * 
 * Depends on injection of a {@link Flyway} instance.
 * 
 * @author Nicholas Blair
 */
@Named
public class FlywayDatabaseMigrations {

	private Flyway flyway;
	/**
	 * @param flyway the {@link Flyway} to be used by {@link #runMigrations()}
	 */
	@Inject
	public void setFlyway(Flyway flyway) {
		this.flyway = flyway;
	}
	/**
	 * @see Flyway#migrate()
	 */
	@PostConstruct
	public void runMigrations() {
		flyway.migrate();
	}
}
