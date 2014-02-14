/**
 * 
 */
package org.apache.directory.scim.models.enums;

import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.directory.scim.models.ScimType;
import org.codehaus.jackson.annotate.JsonCreator;

/**
 * @author stevemoyer
 *
 */
public enum ScimAddressType implements ScimType {

  @XmlEnumValue("home") HOME("home"),
  @XmlEnumValue("other") OTHER("other"),
  @XmlEnumValue("work") WORK("work");
  
  private static final ReverseMap<ScimAddressType> REVERSE_MAP;
  
  static {
	  REVERSE_MAP = new ReverseMap<ScimAddressType>(ScimAddressType.class);
  }
  
  private String scimValue_;
  
  private ScimAddressType(String scimValue) {
	  scimValue_ = scimValue;
  }
  
  @JsonCreator
  public static ScimAddressType scimValueOf(String scimValue) {
	  return REVERSE_MAP.valueOf(scimValue);
  }
  
  public String toString() {
	  return scimValue_;
  }
  
}
