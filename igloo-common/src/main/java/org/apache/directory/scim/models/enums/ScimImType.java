package org.apache.directory.scim.models.enums;

import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.directory.scim.models.ScimType;

public enum ScimImType implements ScimType {

  @XmlEnumValue("aim") AIM,
  @XmlEnumValue("gtalk") GTALK,
  @XmlEnumValue("icq") ICQ,
  @XmlEnumValue("msn") MSN,
  @XmlEnumValue("qq") QQ,
  @XmlEnumValue("skype") SKYPE,
  @XmlEnumValue("xmpp") XMPP,
  @XmlEnumValue("yahoo") YAHOO
  
}
