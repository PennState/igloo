package org.apache.directory.scim.models.enums;

import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.directory.scim.models.ScimType;

public enum ScimPhotoType implements ScimType {

  @XmlEnumValue("photo") PHOTO,
  @XmlEnumValue("thumbnail") THUMBNAIL
  
}
