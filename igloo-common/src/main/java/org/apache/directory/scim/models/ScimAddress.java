/**
 * 
 */
package org.apache.directory.scim.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.directory.scim.models.enums.ScimAddressType;

/**
 * @author stevemoyer
 *
 */
@XmlType(name = "address", propOrder = {
    "formatted",
    "streetAddress",
    "locality",
    "region",
    "postalCode",
    "country"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class ScimAddress extends TypedMultiValuedAttribute<ScimAddressType> {
  
  @XmlElement(name = "country")
  private String country_;
  
  @XmlElement(name = "formatted")
  private String formatted_;
  
  @XmlElement(name = "locality")
  private String locality_;
  
  @XmlElement(name = "postalCode")
  private String postalCode;
  
  @XmlElement(name = "region")
  private String region_;
  
  @XmlElement(name = "streetAddress")
  private String streetAddress_;
  
  /**
   * @return the country
   */
  public String getCountry() {
    return country_;
  }
  
  /**
   * @param country the country to set
   */
  public void setCountry(String country) {
    this.country_ = country;
  }
  
  /**
   * @return the formatted
   */
  public String getFormatted() {
    return formatted_;
  }
  
  /**
   * @param formatted the formatted to set
   */
  public void setFormatted(String formatted) {
    this.formatted_ = formatted;
  }
  
  /**
   * @return the locality
   */
  public String getLocality() {
    return locality_;
  }
  
  /**
   * @param locality the locality to set
   */
  public void setLocality(String locality) {
    this.locality_ = locality;
  }
  
  /**
   * @return the postalCode
   */
  public String getPostalCode() {
    return postalCode;
  }
  
  /**
   * @param postalCode the postalCode to set
   */
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
  
  /**
   * @return the region
   */
  public String getRegion() {
    return region_;
  }
  
  /**
   * @param region the region to set
   */
  public void setRegion(String region) {
    this.region_ = region;
  }
  
  /**
   * @return the streetAddress
   */
  public String getStreetAddress() {
    return streetAddress_;
  }
  
  /**
   * @param streetAddress the streetAddress to set
   */
  public void setStreetAddress(String streetAddress) {
    this.streetAddress_ = streetAddress;
  }

}
