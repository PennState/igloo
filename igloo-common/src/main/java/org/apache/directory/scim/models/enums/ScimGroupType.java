package org.apache.directory.scim.models.enums;

import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.directory.scim.models.ScimType;

public enum ScimGroupType implements ScimType {

  @XmlEnumValue("direct") DIRECT,
  @XmlEnumValue("indirect") INDIRECT
  
}
