package org.apache.directory.scim.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "name", propOrder = {
    "formatted_",
    "familyName_",
    "givenName_",
    "middleName_",
    "honorificPrefix_",
    "honorificSuffix_"    
})
@XmlAccessorType(XmlAccessType.FIELD)
public class ScimName {
  
  @XmlElement(name = "formatted")
  private String formatted_;
  
  @XmlElement(name = "familyName")
  private String familyName_;
  
  @XmlElement(name = "givenName")
  private String givenName_;
  
  @XmlElement(name = "middleName")
  private String middleName_;
  
  @XmlElement(name = "honorificPrefix")
  private String honorificPrefix_;
  
  @XmlElement(name = "honorificSuffix")
  private String honorificSuffix_;
  
  /**
   * @return the formatted name
   */
  public String getFormatted() {
    return formatted_;
  }
  
  /**
   * @param formatted the formatted name to set
   */
  public void setFormatted(String formatted) {
    this.formatted_ = formatted;
  }
  
  /**
   * @return the familyName
   */
  public String getFamilyName() {
    return familyName_;
  }
  
  /**
   * @param familyName the familyName to set
   */
  public void setFamilyName(String familyName) {
    this.familyName_ = familyName;
  }
  
  /**
   * @return the givenName
   */
  public String getGivenName() {
    return givenName_;
  }
  
  /**
   * @param givenName the givenName to set
   */
  public void setGivenName(String givenName) {
    this.givenName_ = givenName;
  }
  
  /**
   * @return the middleName
   */
  public String getMiddleName() {
    return middleName_;
  }
  
  /**
   * @param middleName the middleName to set
   */
  public void setMiddleName(String middleName) {
    this.middleName_ = middleName;
  }
  
  /**
   * @return the honorificPrefix
   */
  public String getHonorificPrefix() {
    return honorificPrefix_;
  }
  
  /**
   * @param honorificPrefix the honorificPrefix to set
   */
  public void setHonorificPrefix(String honorificPrefix) {
    this.honorificPrefix_ = honorificPrefix;
  }
  
  /**
   * @return the honorificSuffix
   */
  public String getHonorificSuffix() {
    return honorificSuffix_;
  }
  
  /**
   * @param honorificSuffix the honorificSuffix to set
   */
  public void setHonorificSuffix(String honorificSuffix) {
    this.honorificSuffix_ = honorificSuffix;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((familyName_ == null) ? 0 : familyName_.hashCode());
    result = prime * result + ((formatted_ == null) ? 0 : formatted_.hashCode());
    result = prime * result + ((givenName_ == null) ? 0 : givenName_.hashCode());
    result = prime * result + ((honorificPrefix_ == null) ? 0 : honorificPrefix_.hashCode());
    result = prime * result + ((honorificSuffix_ == null) ? 0 : honorificSuffix_.hashCode());
    result = prime * result + ((middleName_ == null) ? 0 : middleName_.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ScimName other = (ScimName) obj;
    if (familyName_ == null) {
      if (other.familyName_ != null)
        return false;
    } else if (!familyName_.equals(other.familyName_))
      return false;
    if (formatted_ == null) {
      if (other.formatted_ != null)
        return false;
    } else if (!formatted_.equals(other.formatted_))
      return false;
    if (givenName_ == null) {
      if (other.givenName_ != null)
        return false;
    } else if (!givenName_.equals(other.givenName_))
      return false;
    if (honorificPrefix_ == null) {
      if (other.honorificPrefix_ != null)
        return false;
    } else if (!honorificPrefix_.equals(other.honorificPrefix_))
      return false;
    if (honorificSuffix_ == null) {
      if (other.honorificSuffix_ != null)
        return false;
    } else if (!honorificSuffix_.equals(other.honorificSuffix_))
      return false;
    if (middleName_ == null) {
      if (other.middleName_ != null)
        return false;
    } else if (!middleName_.equals(other.middleName_))
      return false;
    return true;
  }

}
