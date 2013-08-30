package org.apache.directory.scim.models.enums;

import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.directory.scim.models.ScimType;

public enum ScimEmailType implements ScimType {
  
  @XmlEnumValue("home") HOME,
  @XmlEnumValue("other") OTHER,
  @XmlEnumValue("work") WORK

}
