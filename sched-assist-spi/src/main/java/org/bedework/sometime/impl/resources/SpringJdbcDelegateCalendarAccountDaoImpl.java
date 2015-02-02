/**
 * 
 */
package org.bedework.sometime.impl.resources;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.NotImplementedException;
import org.jasig.schedassist.IDelegateCalendarAccountDao;
import org.jasig.schedassist.model.ICalendarAccount;
import org.jasig.schedassist.model.IDelegateCalendarAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * {@link DataSource} backed implementation of {@link IDelegateCalendarAccountDao} that uses
 * Spring JDBC.
 * 
 * @see JdbcTemplate
 * @author Nicholas Blair
 */
public class SpringJdbcDelegateCalendarAccountDaoImpl implements IDelegateCalendarAccountDao {

  private JdbcTemplate jdbcTemplate;
  
  private static final String WILDCARD = "%";
  @Autowired
  public void setDataSource(DataSource ds) {
    this.jdbcTemplate = new JdbcTemplate(ds);
  }
  /* (non-Javadoc)
   * @see org.jasig.schedassist.IDelegateCalendarAccountDao#searchForDelegates(java.lang.String, org.jasig.schedassist.model.ICalendarAccount)
   */
  @Override
  public List<IDelegateCalendarAccount> searchForDelegates(String searchText, ICalendarAccount owner) {
    List<IDelegateCalendarAccount> delegates = searchForDelegates(searchText);
    for(IDelegateCalendarAccount delegate: delegates) {
      ((DatabaseDelegateCalendarAccountImpl) delegate).setAccountOwner(owner);
    }
    String searchTextInternal = searchText.replace(" ", WILDCARD);
    if(!searchTextInternal.endsWith(WILDCARD)) {
        searchTextInternal += WILDCARD;
    }
    
    final String query;
    final List<String> arguments;
    if(owner != null) {
      query = "select * from resources where ownerExternalId = ? and (name like ? or description like ?)";
      arguments = Arrays.asList(owner.getCalendarUniqueId(), searchTextInternal, searchTextInternal);
    } else {
      query = "select * from resources where name like ? or description like ?";
      arguments = Arrays.asList(searchTextInternal, searchTextInternal);
    }
    
    List<DatabaseDelegateCalendarAccountImpl> results = this.jdbcTemplate.query(query, 
        new DatabaseDelegateCalendarAccountRowMapper(), arguments.toArray());
    
    for(DatabaseDelegateCalendarAccountImpl delegate: results) {
      delegate.setAccountOwner(owner);
    }
    return delegates;
  }

  /* (non-Javadoc)
   * @see org.jasig.schedassist.IDelegateCalendarAccountDao#searchForDelegates(java.lang.String)
   */
  @Override
  public List<IDelegateCalendarAccount> searchForDelegates(String searchText) {
    return searchForDelegates(searchText, null);
  }

  /* (non-Javadoc)
   * @see org.jasig.schedassist.IDelegateCalendarAccountDao#getDelegate(java.lang.String)
   */
  @Override
  public IDelegateCalendarAccount getDelegate(String accountName) {
    List<DatabaseDelegateCalendarAccountImpl> results = this.jdbcTemplate.query("select * from resources where name=?", 
        new DatabaseDelegateCalendarAccountRowMapper(), accountName);
    if(results.size() == 1) {
      return results.get(0);
    }
    
    return null;
  }

  /* (non-Javadoc)
   * @see org.jasig.schedassist.IDelegateCalendarAccountDao#getDelegate(java.lang.String, org.jasig.schedassist.model.ICalendarAccount)
   */
  @Override
  public IDelegateCalendarAccount getDelegate(String accountName, ICalendarAccount owner) {
    DatabaseDelegateCalendarAccountImpl account = (DatabaseDelegateCalendarAccountImpl) getDelegate(accountName);
    if(account != null) {
      account.setAccountOwner(owner);
    }
    
    return account;
  }

  /* (non-Javadoc)
   * @see org.jasig.schedassist.IDelegateCalendarAccountDao#getDelegate(java.lang.String, java.lang.String)
   */
  @Override
  public IDelegateCalendarAccount getDelegate(String attributeName, String attributeValue) {
    throw new NotImplementedException("TODO implement getDelegate(String, String)");
  }

  /* (non-Javadoc)
   * @see org.jasig.schedassist.IDelegateCalendarAccountDao#getDelegateByUniqueId(java.lang.String)
   */
  @Override
  public IDelegateCalendarAccount getDelegateByUniqueId(String accountUniqueId) {
    return getDelegate(accountUniqueId);
  }

  /* (non-Javadoc)
   * @see org.jasig.schedassist.IDelegateCalendarAccountDao#getDelegateByUniqueId(java.lang.String, org.jasig.schedassist.model.ICalendarAccount)
   */
  @Override
  public IDelegateCalendarAccount getDelegateByUniqueId(String accountUniqueId,
      ICalendarAccount owner) {
    return getDelegate(accountUniqueId, owner);
  }

}
