package org.apache.directory.scim.models.enums;

import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.directory.scim.models.ScimType;

public enum ScimPhoneNumberType implements ScimType {
  
  @XmlEnumValue("home") HOME,
  @XmlEnumValue("fax") FAX,
  @XmlEnumValue("mobile") MOBILE,
  @XmlEnumValue("other") OTHER,
  @XmlEnumValue("pager") PAGER,
  @XmlEnumValue("work") WORK

}
