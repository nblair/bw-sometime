/**
 * 
 */
package org.bedework.sometime.database.migrations;

import javax.inject.Inject;

import org.flywaydb.core.Flyway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests for {@link FlywayDatabaseMigrations}.
 * 
 * @author Nicholas Blair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfiguration.class)
public class FlywayDatabaseMigrationsTest {

	@Inject private Flyway flyway;
	
	/**
	 * Executes {@link FlywayDatabaseMigrations#runMigrations()}.
	 */
	@Test
	public void runMigrations_test() {
		FlywayDatabaseMigrations migrations = new FlywayDatabaseMigrations();
		migrations.setFlyway(flyway);
		
		migrations.runMigrations();
	}
}
