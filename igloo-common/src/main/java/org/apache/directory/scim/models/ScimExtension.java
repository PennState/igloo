package org.apache.directory.scim.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ScimExtension")
public interface ScimExtension {
  
  Class<? extends ScimResource> getBaseClass();
  Urn getUrn();

}
