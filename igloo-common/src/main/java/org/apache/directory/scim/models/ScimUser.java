package org.apache.directory.scim.models;

import java.util.ArrayList;
import java.util.Iterator;
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
  
  @XmlElement(name = "name")
  private ScimName name_;
  
  @XmlElement(name = "nickName")
  private String nickName_;
  
  @XmlElement(name = "password")
  private String password_;
  
  @XmlElement(name = "phoneNumbers")
  private List<ScimPhoneNumber> phoneNumbers_;
  
  @XmlElement(name = "photos")
  private List<ScimPhoto> photos_;

  @XmlElement(name = "profileUrl")
  private String profileUrl_;
  
  @XmlElement(name = "preferredLanguage")
  private String preferredLanguage_;
  
  @XmlElement(name = "roles")
  private List<ScimRole> roles_;
  
  @XmlElement(name = "timezone")
  private String timezone_;
  
  @XmlElement(name = "title")
  private String title_;
  
  @XmlElement(name = "userName")
  private String userName_;
  
  @XmlElement(name = "userType")
  private String userType_;
  
  @XmlElement(name = "x509Certificates")
  private List<ScimX509Certificate> x509Certificates_;
  
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
   * Get the primary address or the only address if there's only one
   * If there's multiple addresses but none marked primary we return
   * the first address in the list
   * @return ScimAddress or null if no addresses are set
   */
  public ScimAddress getPrimaryAddress()
  {
    ScimAddress address = null;
    if (addresses_.size() == 1)
    {
      address = addresses_.get(0);
    }
    else if (addresses_.size() > 1)
    {
      for (ScimAddress addr : addresses_)
      {
        if (addr.isPrimary())
        {
          address = addr;
          break;
        }
      }
      
      if (address == null)
      {
        address = addresses_.get(0);
      }
    }
    
    return address;
  }
  
  public void addAddress(ScimAddress address)
  {
    if (addresses_ == null)
    {
      addresses_ = new ArrayList<ScimAddress>();
    }
    
    if (address.isPrimary())
    {
      for (ScimAddress addr : addresses_)
      {
        addr.setPrimary(false);
      }
    }
    
    //Remove any duplicates with this type
    Iterator<ScimAddress> iterator = addresses_.iterator();
    
    while(iterator.hasNext())
    {
      ScimAddress sa = iterator.next();
      
      if (sa.getType() == address.getType())
      {
        iterator.remove();
      }
    }
    
    addresses_.add(address);
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
    return name_;
  }
  
  /**
   * @param name the name to set
   */
  public void setName(ScimName name) {
    this.name_ = name;
  }
  
  /**
   * @return the nickName
   */
  public String getNickName() {
    return nickName_;
  }
  
  /**
   * @param nickName the nickName to set
   */
  public void setNickName(String nickName) {
    this.nickName_ = nickName;
  }
  
  /**
   * @return the password
   */
  public String getPassword() {
    return password_;
  }
  
  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password_ = password;
  }
  
  /**
   * @return the phoneNumbers
   */
  public List<ScimPhoneNumber> getPhoneNumbers() {
    return phoneNumbers_;
  }
  
  /**
   * @param phoneNumbers the phoneNumbers to set
   */
  public void setPhoneNumbers(List<ScimPhoneNumber> phoneNumbers) {
    this.phoneNumbers_ = phoneNumbers;
  }
  
  /**
   * @return the photos
   */
  public List<ScimPhoto> getPhotos() {
    return photos_;
  }
  
  /**
   * @param photos the photos to set
   */
  public void setPhotos(List<ScimPhoto> photos) {
    this.photos_ = photos;
  }
  
  /**
   * @return the profileUrl
   */
  public String getProfileUrl() {
    return profileUrl_;
  }
  
  /**
   * @param profileUrl the profileUrl to set
   */
  public void setProfileUrl(String profileUrl) {
    this.profileUrl_ = profileUrl;
  }
  
  /**
   * @return the preferredLanguage
   */
  public String getPreferredLanguage() {
    return preferredLanguage_;
  }
  
  /**
   * @param preferredLanguage the preferredLanguage to set
   */
  public void setPreferredLanguage(String preferredLanguage) {
    this.preferredLanguage_ = preferredLanguage;
  }
  
  /**
   * @return the roles
   */
  public List<ScimRole> getRoles() {
    return roles_;
  }
  
  /**
   * @param roles the roles to set
   */
  public void setRoles(List<ScimRole> roles) {
    this.roles_ = roles;
  }
  
  /**
   * @return the timezone
   */
  public String getTimezone() {
    return timezone_;
  }
  
  /**
   * @param timezone the timezone to set
   */
  public void setTimezone(String timezone) {
    this.timezone_ = timezone;
  }
  
  /**
   * @return the title
   */
  public String getTitle() {
    return title_;
  }
  
  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title_ = title;
  }
  
  /**
   * @return the userName
   */
  public String getUserName() {
    return userName_;
  }
  
  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName_ = userName;
  }
  
  /**
   * @return the userType
   */
  public String getUserType() {
    return userType_;
  }
  
  /**
   * @param userType the userType to set
   */
  public void setUserType(String userType) {
    this.userType_ = userType;
  }
  
  /**
   * @return the x509Certificates
   */
  public List<ScimX509Certificate> getX509Certificates() {
    return x509Certificates_;
  }
  
  /**
   * @param x509Certificates the x509Certificates to set
   */
  public void setX509Certificates(List<ScimX509Certificate> x509Certificates) {
    this.x509Certificates_ = x509Certificates;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + (active_ ? 1231 : 1237);
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((addresses_ == null) ? 0 : addresses_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((displayName_ == null) ? 0 : displayName_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((emails_ == null) ? 0 : emails_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((entitlements_ == null) ? 0 : entitlements_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((groups_ == null) ? 0 : groups_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((ims_ == null) ? 0 : ims_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((locale_ == null) ? 0 : locale_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((name_ == null) ? 0 : name_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((nickName_ == null) ? 0 : nickName_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((password_ == null) ? 0 : password_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((phoneNumbers_ == null) ? 0 : phoneNumbers_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((photos_ == null) ? 0 : photos_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((preferredLanguage_ == null) ? 0 : preferredLanguage_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((profileUrl_ == null) ? 0 : profileUrl_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((roles_ == null) ? 0 : roles_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((timezone_ == null) ? 0 : timezone_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((title_ == null) ? 0 : title_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((userName_ == null) ? 0 : userName_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((userType_ == null) ? 0 : userType_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    result = prime * result + ((x509Certificates_ == null) ? 0 : x509Certificates_.hashCode());
    System.out.println("ScimUser hashcode: " + result);
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    ScimUser other = (ScimUser) obj;
    if (active_ != other.active_)
      return false;
    if (addresses_ == null) {
      if (other.addresses_ != null)
        return false;
    } else if (!addresses_.equals(other.addresses_))
      return false;
    if (displayName_ == null) {
      if (other.displayName_ != null)
        return false;
    } else if (!displayName_.equals(other.displayName_))
      return false;
    if (emails_ == null) {
      if (other.emails_ != null)
        return false;
    } else if (!emails_.equals(other.emails_))
      return false;
    if (entitlements_ == null) {
      if (other.entitlements_ != null)
        return false;
    } else if (!entitlements_.equals(other.entitlements_))
      return false;
    if (groups_ == null) {
      if (other.groups_ != null)
        return false;
    } else if (!groups_.equals(other.groups_))
      return false;
    if (ims_ == null) {
      if (other.ims_ != null)
        return false;
    } else if (!ims_.equals(other.ims_))
      return false;
    if (locale_ == null) {
      if (other.locale_ != null)
        return false;
    } else if (!locale_.equals(other.locale_))
      return false;
    if (name_ == null) {
      if (other.name_ != null)
        return false;
    } else if (!name_.equals(other.name_))
      return false;
    if (nickName_ == null) {
      if (other.nickName_ != null)
        return false;
    } else if (!nickName_.equals(other.nickName_))
      return false;
    if (password_ == null) {
      if (other.password_ != null)
        return false;
    } else if (!password_.equals(other.password_))
      return false;
    if (phoneNumbers_ == null) {
      if (other.phoneNumbers_ != null)
        return false;
    } else if (!phoneNumbers_.equals(other.phoneNumbers_))
      return false;
    if (photos_ == null) {
      if (other.photos_ != null)
        return false;
    } else if (!photos_.equals(other.photos_))
      return false;
    if (preferredLanguage_ == null) {
      if (other.preferredLanguage_ != null)
        return false;
    } else if (!preferredLanguage_.equals(other.preferredLanguage_))
      return false;
    if (profileUrl_ == null) {
      if (other.profileUrl_ != null)
        return false;
    } else if (!profileUrl_.equals(other.profileUrl_))
      return false;
    if (roles_ == null) {
      if (other.roles_ != null)
        return false;
    } else if (!roles_.equals(other.roles_))
      return false;
    if (timezone_ == null) {
      if (other.timezone_ != null)
        return false;
    } else if (!timezone_.equals(other.timezone_))
      return false;
    if (title_ == null) {
      if (other.title_ != null)
        return false;
    } else if (!title_.equals(other.title_))
      return false;
    if (userName_ == null) {
      if (other.userName_ != null)
        return false;
    } else if (!userName_.equals(other.userName_))
      return false;
    if (userType_ == null) {
      if (other.userType_ != null)
        return false;
    } else if (!userType_.equals(other.userType_))
      return false;
    if (x509Certificates_ == null) {
      if (other.x509Certificates_ != null)
        return false;
    } else if (!x509Certificates_.equals(other.x509Certificates_))
      return false;
    return true;
  }

}
