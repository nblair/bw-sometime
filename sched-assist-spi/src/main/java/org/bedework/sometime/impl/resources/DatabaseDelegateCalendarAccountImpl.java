/**
 * 
 */
package org.bedework.sometime.impl.resources;

import java.util.List;
import java.util.Map;

import org.jasig.schedassist.model.AbstractCalendarAccount;
import org.jasig.schedassist.model.ICalendarAccount;
import org.jasig.schedassist.model.IDelegateCalendarAccount;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;

/**
 * Manifestation of an {@link IDelegateCalendarAccount} stored in the database, managed by the 
 * data access objects in this package.
 * 
 * @author Nicholas Blair
 */
class DatabaseDelegateCalendarAccountImpl extends AbstractCalendarAccount implements IDelegateCalendarAccount {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private final long id;
  private final String ownerExternalId;
  private ICalendarAccount accountOwner;
  private final ListMultimap<String, String> attributes;
  /**
   * 
   * @param id
   * @param name used for both {@link #getCalendarUniqueId()} and {@link #getUsername()}
   * @param description used for {@link #getDisplayName()}
   * @param mail used for {@link #getEmailAddress()}
   * @param ownerExternalId the external id for the account owner (see {@link #getAccountOwnerAttribute()})
   */
  DatabaseDelegateCalendarAccountImpl(long id, String name, String description, String mail, String ownerExternalId) {
    this.id = id;
    this.username = name;
    this.calendarUniqueId = name;
    this.displayName = description;
    this.emailAddress = mail;
    this.ownerExternalId = ownerExternalId;
    this.eligible = true;
    
    ListMultimap<String, String> attr = ArrayListMultimap.create();
    attr.put("name", name);
    attr.put("description", description);
    attr.put("mail", mail);
    attr.put("ownerExternalId", ownerExternalId);
    
    this.attributes = attr;
    
  }
  
  /**
   * @return the id
   */
  long getId() {
    return id;
  }
  /**
   * {@inheritDoc}
   * 
   * This field is always null unless an instance is provided via {@link #setAccountOwner(ICalendarAccount)} previously.
   */
  @Override
  public ICalendarAccount getAccountOwner() {
    return this.accountOwner;
  }
  /**
   * @param accountOwner the accountOwner to set
   */
  void setAccountOwner(ICalendarAccount accountOwner) {
    this.accountOwner = accountOwner;
  }
  /**
   * {@inheritDoc}
   * 
   * Returns the "ownerExternalId" attribute.
   */
  @Override
  public String getAccountOwnerAttribute() {
    return this.ownerExternalId;
  }
  
  @Override
  public String getLocation() {
    // TODO will need column for location
    return null;
  }
  @Override
  public String getContactInformation() {
    // TODO will need column for resource contact information
    return null;
  }
  /*
   * (non-Javadoc)
   * @see org.jasig.schedassist.model.AbstractCalendarAccount#getAttributes()
   */
  @Override
  public Map<String, List<String>> getAttributes() {
    return Multimaps.asMap(attributes);
  }
  /**
   * {@inheritDoc}
   * 
   * Returns the "mail" attribute.
   */
  @Override
  public String getCalendarLoginId() {
    return getEmailAddress();
  }
 
}
