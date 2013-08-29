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

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    System.out.println("ScimAddress hashcode: " + result);
    result = prime * result + ((country_ == null) ? 0 : country_.hashCode());
    System.out.println("ScimAddress hashcode: " + result);
    result = prime * result + ((formatted_ == null) ? 0 : formatted_.hashCode());
    System.out.println("ScimAddress hashcode: " + result);
    result = prime * result + ((locality_ == null) ? 0 : locality_.hashCode());
    System.out.println("ScimAddress hashcode: " + result);
    result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
    System.out.println("ScimAddress hashcode: " + result);
    result = prime * result + ((region_ == null) ? 0 : region_.hashCode());
    System.out.println("ScimAddress hashcode: " + result);
    result = prime * result + ((streetAddress_ == null) ? 0 : streetAddress_.hashCode());
    System.out.println("ScimAddress hashcode: " + result);
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
    ScimAddress other = (ScimAddress) obj;
    if (country_ == null) {
      if (other.country_ != null)
        return false;
    } else if (!country_.equals(other.country_))
      return false;
    if (formatted_ == null) {
      if (other.formatted_ != null)
        return false;
    } else if (!formatted_.equals(other.formatted_))
      return false;
    if (locality_ == null) {
      if (other.locality_ != null)
        return false;
    } else if (!locality_.equals(other.locality_))
      return false;
    if (postalCode == null) {
      if (other.postalCode != null)
        return false;
    } else if (!postalCode.equals(other.postalCode))
      return false;
    if (region_ == null) {
      if (other.region_ != null)
        return false;
    } else if (!region_.equals(other.region_))
      return false;
    if (streetAddress_ == null) {
      if (other.streetAddress_ != null)
        return false;
    } else if (!streetAddress_.equals(other.streetAddress_))
      return false;
    return true;
  }

}
