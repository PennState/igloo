/**
 * 
 */
package org.apache.directory.scim.models.enums;

import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.directory.scim.models.ScimType;

/**
 * @author stevemoyer
 *
 */
public enum ScimAddressType implements ScimType {

  @XmlEnumValue("home") HOME,
  @XmlEnumValue("other") OTHER,
  @XmlEnumValue("work") WORK
  
}
