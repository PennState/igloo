package org.apache.directory.scim.models.enums;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.directory.scim.models.ScimType;
import org.codehaus.jackson.annotate.JsonCreator;

public enum ScimPhoneNumberType implements ScimType {
  
  @XmlEnumValue("home") HOME("home"),
  @XmlEnumValue("fax") FAX("fax"),
  @XmlEnumValue("mobile") MOBILE("mobile"),
  @XmlEnumValue("other") OTHER("other"),
  @XmlEnumValue("pager") PAGER("pager"),
  @XmlEnumValue("work") WORK("work");

  private static final ReverseMap<ScimPhoneNumberType> REVERSE_MAP;
  
  static {
	  REVERSE_MAP = new ReverseMap<ScimPhoneNumberType>(ScimPhoneNumberType.class);
  }
  
  private String scimValue_;
  
  private ScimPhoneNumberType(String scimValue) {
	  scimValue_ = scimValue;
  }
  
  @JsonCreator
  public static ScimPhoneNumberType scimValueOf(String scimValue) {
	  return REVERSE_MAP.valueOf(scimValue);
  }
  
  public String toString() {
	  return scimValue_;
  }

}
