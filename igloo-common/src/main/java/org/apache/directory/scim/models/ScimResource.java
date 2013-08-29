/**
 * 
 */
package org.apache.directory.scim.models;

import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author stevemoyer
 *
 */
public abstract class ScimResource {
  
  @XmlAnyElement
  private Map<String, ScimExtension> extensions;
  
  @XmlElement(name = "externalId")
  private String externalId;
  
  @XmlElement(name = "id")
  private UUID id;
  
  @XmlElement(name = "meta")
  private ScimMeta meta;

  /**
   * @return the extensions
   */
  public Map<String, ScimExtension> getExtensions() {
    return extensions;
  }

  /**
   * @param extensions the extensions to set
   */
  public void setExtensions(Map<String, ScimExtension> extensions) {
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

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    System.out.println("ScimResource hashcode: " + result);
    result = prime * result + ((extensions == null) ? 0 : extensions.hashCode());
    System.out.println("ScimResource hashcode: " + result);
    result = prime * result + ((externalId == null) ? 0 : externalId.hashCode());
    System.out.println("ScimResource hashcode: " + result);
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    System.out.println("ScimResource hashcode: " + result);
    result = prime * result + ((meta == null) ? 0 : meta.hashCode());
    System.out.println("ScimResource hashcode: " + result);
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ScimResource other = (ScimResource) obj;
    if (extensions == null) {
      if (other.extensions != null)
        return false;
    } else if (!extensions.equals(other.extensions))
      return false;
    if (externalId == null) {
      if (other.externalId != null)
        return false;
    } else if (!externalId.equals(other.externalId))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (meta == null) {
      if (other.meta != null)
        return false;
    } else if (!meta.equals(other.meta))
      return false;
    return true;
  }

}
