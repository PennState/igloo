package org.apache.directory.scim.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "User")
@XmlType(name = "User", propOrder = {
    "userName",
    "name",
    "displayName",
    "nickName",
    "profileUrl",
    "title",
    "userType",
    "preferredLanguage",
    "locale",
    "timezone",
    "active",
    "password",
    "emails",
    "phoneNumbers",
    "ims",
    "photos",
    "addresses",
    "groups",
    "entitlements",
    "roles",
    "x509Certificates"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class ScimUser extends ScimResource {
  
  @XmlElement(name = "active")
  private boolean active_ = true;
  
  @XmlElement(name = "addresses")
  private List<ScimAddress> addresses_;
  
  @XmlElement(name = "displayName")
  private String displayName_;
  
  @XmlElement(name = "emails")
  private List<ScimEmail> emails_;
  
  @XmlElement(name = "entitlements")
  private List<ScimEntitlement> entitlements_;
  
  @XmlElement(name = "groups")
  private List<ScimGroup> groups_;
  
  @XmlElement(name = "ims")
  private List<ScimIm> ims_;
  
  @XmlElement(name = "locale")
  private String locale_;
  
  private ScimName name;
  private String nickName;
  private String password;
  private List<ScimPhoneNumber> phoneNumbers;
  private List<ScimPhoto> photos;
  private String profileUrl;
  private String preferredLanguage;
  private List<ScimRole> roles;
  private String timezone;
  private String title;
  private String userName;
  private String userType;
  private List<ScimX509Certificate> x509Certificates;
  /**
   * @return the active
   */
  public boolean isActive() {
    return active_;
  }
  /**
   * @param active the active to set
   */
  public void setActive(boolean active) {
    this.active_ = active;
  }
  /**
   * @return the addresses
   */
  public List<ScimAddress> getAddresses() {
    return addresses_;
  }
  /**
   * @param addresses the addresses to set
   */
  public void setAddresses(List<ScimAddress> addresses) {
    this.addresses_ = addresses;
  }
  /**
   * @return the displayName
   */
  public String getDisplayName() {
    return displayName_;
  }
  /**
   * @param displayName the displayName to set
   */
  public void setDisplayName(String displayName) {
    this.displayName_ = displayName;
  }
  /**
   * @return the emails
   */
  public List<ScimEmail> getEmails() {
    return emails_;
  }
  /**
   * @param emails the emails to set
   */
  public void setEmails(List<ScimEmail> emails) {
    this.emails_ = emails;
  }
  /**
   * @return the entitlements
   */
  public List<ScimEntitlement> getEntitlements() {
    return entitlements_;
  }
  /**
   * @param entitlements the entitlements to set
   */
  public void setEntitlements(List<ScimEntitlement> entitlements) {
    this.entitlements_ = entitlements;
  }
  /**
   * @return the groups
   */
  public List<ScimGroup> getGroups() {
    return groups_;
  }
  /**
   * @param groups the groups to set
   */
  public void setGroups(List<ScimGroup> groups) {
    this.groups_ = groups;
  }
  /**
   * @return the ims
   */
  public List<ScimIm> getIms() {
    return ims_;
  }
  /**
   * @param ims the ims to set
   */
  public void setIms(List<ScimIm> ims) {
    this.ims_ = ims;
  }
  /**
   * @return the locale
   */
  public String getLocale() {
    return locale_;
  }
  /**
   * @param locale the locale to set
   */
  public void setLocale(String locale) {
    this.locale_ = locale;
  }
  /**
   * @return the name
   */
  public ScimName getName() {
    return name;
  }
  /**
   * @param name the name to set
   */
  public void setName(ScimName name) {
    this.name = name;
  }
  /**
   * @return the nickName
   */
  public String getNickName() {
    return nickName;
  }
  /**
   * @param nickName the nickName to set
   */
  public void setNickName(String nickName) {
    this.nickName = nickName;
  }
  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }
  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
  /**
   * @return the phoneNumbers
   */
  public List<ScimPhoneNumber> getPhoneNumbers() {
    return phoneNumbers;
  }
  /**
   * @param phoneNumbers the phoneNumbers to set
   */
  public void setPhoneNumbers(List<ScimPhoneNumber> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }
  /**
   * @return the photos
   */
  public List<ScimPhoto> getPhotos() {
    return photos;
  }
  /**
   * @param photos the photos to set
   */
  public void setPhotos(List<ScimPhoto> photos) {
    this.photos = photos;
  }
  /**
   * @return the profileUrl
   */
  public String getProfileUrl() {
    return profileUrl;
  }
  /**
   * @param profileUrl the profileUrl to set
   */
  public void setProfileUrl(String profileUrl) {
    this.profileUrl = profileUrl;
  }
  /**
   * @return the preferredLanguage
   */
  public String getPreferredLanguage() {
    return preferredLanguage;
  }
  /**
   * @param preferredLanguage the preferredLanguage to set
   */
  public void setPreferredLanguage(String preferredLanguage) {
    this.preferredLanguage = preferredLanguage;
  }
  /**
   * @return the roles
   */
  public List<ScimRole> getRoles() {
    return roles;
  }
  /**
   * @param roles the roles to set
   */
  public void setRoles(List<ScimRole> roles) {
    this.roles = roles;
  }
  /**
   * @return the timezone
   */
  public String getTimezone() {
    return timezone;
  }
  /**
   * @param timezone the timezone to set
   */
  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }
  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }
  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }
  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }
  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }
  /**
   * @return the userType
   */
  public String getUserType() {
    return userType;
  }
  /**
   * @param userType the userType to set
   */
  public void setUserType(String userType) {
    this.userType = userType;
  }
  /**
   * @return the x509Certificates
   */
  public List<ScimX509Certificate> getX509Certificates() {
    return x509Certificates;
  }
  /**
   * @param x509Certificates the x509Certificates to set
   */
  public void setX509Certificates(List<ScimX509Certificate> x509Certificates) {
    this.x509Certificates = x509Certificates;
  }

}
