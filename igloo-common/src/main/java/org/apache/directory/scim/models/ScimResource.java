/**
 * 
 */
package org.apache.directory.scim.models;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

import org.codehaus.jackson.annotate.JsonUnwrapped;

/**
 * @author stevemoyer
 *
 */
public abstract class ScimResource {
  
  @XmlElementRef
  private List<ScimExtension> extensions;
  
  @XmlElement(name = "externalId")
  private String externalId;
  
  @XmlElement(name = "id")
  private UUID id;
  
  @XmlElement(name = "meta")
  private ScimMeta meta;

  /**
   * @return the extensions
   */
  public List<ScimExtension> getExtensions() {
    return extensions;
  }

  /**
   * @param extensions the extensions to set
   */
  public void setExtensions(List<ScimExtension> extensions) {
    this.extensions = extensions;
  }

  /**
   * @return the externalId
   */
  public String getExternalId() {
    return externalId;
  }

  /**
   * @param externalId the externalId to set
   */
  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  /**
   * @return the id
   */
  public UUID getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * @return the meta
   */
  public ScimMeta getMeta() {
    return meta;
  }

  /**
   * @param meta the meta to set
   */
  public void setMeta(ScimMeta meta) {
    this.meta = meta;
  }

}
