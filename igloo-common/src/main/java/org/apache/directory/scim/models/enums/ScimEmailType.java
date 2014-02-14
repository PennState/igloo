package org.apache.directory.scim.models.enums;

import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.directory.scim.models.ScimType;
import org.codehaus.jackson.annotate.JsonCreator;

public enum ScimEmailType implements ScimType {
  
  @XmlEnumValue("home") HOME("home"),
  @XmlEnumValue("other") OTHER("other"),
  @XmlEnumValue("work") WORK("work");
  
  private static final ReverseMap<ScimEmailType> REVERSE_MAP;
  
  static {
	  REVERSE_MAP = new ReverseMap<ScimEmailType>(ScimEmailType.class);
  }
  
  private String scimValue_;
  
  private ScimEmailType(String scimValue) {
	  scimValue_ = scimValue;
  }
  
  @JsonCreator
  public static ScimEmailType scimValueOf(String scimValue) {
	  return REVERSE_MAP.valueOf(scimValue);
  }
  
  public String toString() {
	  return scimValue_;
  }
  
}
