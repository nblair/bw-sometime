/**
 * 
 */
package org.bedework.sometime.impl.resources;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * {@link RowMapper} for {@link DatabaseDelegateCalendarAccountImpl}s.
 * 
 * See migration 'V3__create_resource-table.sql' for table definition.
 * 
 * @author Nicholas Blair
 */
class DatabaseDelegateCalendarAccountRowMapper implements
    RowMapper<DatabaseDelegateCalendarAccountImpl> {

  /* (non-Javadoc)
   * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
   */
  @Override
  public DatabaseDelegateCalendarAccountImpl mapRow(ResultSet rs, int rowNum) throws SQLException {
    DatabaseDelegateCalendarAccountImpl account = new DatabaseDelegateCalendarAccountImpl(
        rs.getLong("resource_id"), rs.getString("name"),
        rs.getString("description"), rs.getString("mail"), rs.getString("owner_external_id"));
    return account;
  }

}
