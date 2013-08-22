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

}
