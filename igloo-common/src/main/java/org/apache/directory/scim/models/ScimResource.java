/**
 * 
 */
package org.apache.directory.scim.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.directory.scim.ScimExtensionRegistry;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

/**
 * @author stevemoyer
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public abstract class ScimResource {
  
  private Map<String, ScimExtension> extensions = new HashMap<String, ScimExtension>();
  
  private String externalId;
  
  private UUID id;
  
  private ScimMeta meta;

  /**
   * @return the extensions
   */
  @XmlAnyElement
  @JsonIgnore
  public List<ScimExtension> getExtensionList() {
    return new ArrayList<ScimExtension>(extensions.values());
  }
  
  @XmlTransient
  @JsonAnyGetter
  public Map<String, ScimExtension> getExtensionsMap() {
    return extensions;
  }

  /**
   * @param extensions the extensions to set
   */
  @XmlTransient
  public void setExtensions(Map<String, ScimExtension> extensions) {
    this.extensions = extensions;
  }
  
  @XmlTransient 
  public void addExtension(String key, ScimExtension extension)
  {
    if (extensions == null)
    {
      extensions = new HashMap<String, ScimExtension>();
    }
    
    extensions.put(key, extension);
  }
  
  @JsonAnySetter
  public void setExtensions(String key, Object value) {
    System.out.println("Found a ScimExtension");
    System.out.println("Extension's URN: " + key);
    System.out.println("Extension's string representation: " + value);
    
    Class<? extends ScimResource> resourceClass = getClass();
    System.out.println("Resource class: " + resourceClass.getSimpleName());
    
    Class<? extends ScimExtension> extensionClass = ScimExtensionRegistry.getInstance().getExtensionClass(resourceClass, key);
    System.out.println("Extension class: " + extensionClass.getSimpleName());
    
    ObjectMapper mapper = new ObjectMapper();
    AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector();
    AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();
    AnnotationIntrospector pair = new AnnotationIntrospector.Pair(jacksonIntrospector, jaxbIntrospector);
    mapper.setAnnotationIntrospector(pair);
    
    ScimExtension extension = mapper.convertValue(value, extensionClass);
    if(extension != null) {
      System.out.println("    ***** Added extension to the resource *****");
      extensions.put(key, extension);
    }
  }

  /**
   * @return the externalId
   */
  @XmlElement(name = "externalId")
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
  @XmlElement(name = "id")
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
  @XmlElement(name = "meta")
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
