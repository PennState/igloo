package org.apache.directory.scim.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "meta", propOrder = { "created", "lastModified", "location", "version", "attributes" })
public class ScimMeta {

  private static final String META_TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  
  private List<String> attributes;
  private Date created;
  private Date lastModified;
  private String location;
  private String version;

  /**
   * @return the attributes
   */
  public List<String> getAttributes() {
    return attributes;
  }

  /**
   * @param attributes
   *          the attributes to set
   */
  public void setAttributes(List<String> attributes) {
    this.attributes = attributes;
  }

  /**
   * @return the created
   */
  public String getCreated() {
    DateFormat formatter = new SimpleDateFormat(META_TIMESTAMP_PATTERN);
    return formatter.format(created);
  }

  /**
   * @param created
   *          the created to set
   */
  public void setCreated(Date created) {
    this.created = created;
  }

  /**
   * @return the lastModified
   */
  public String getLastModified() {
    DateFormat formatter = new SimpleDateFormat(META_TIMESTAMP_PATTERN);
    return formatter.format(lastModified);
  }

  /**
   * @param lastModified
   *          the lastModified to set
   */
  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  /**
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * @param location
   *          the location to set
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * @return the version
   */
  public String getVersion() {
    return version;
  }

  /**
   * @param version
   *          the version to set
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    //System.out.println("ScimMeta hashcode: " + result);
    result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
    //System.out.println("ScimMeta hashcode: " + result);
    // ***** WARNING *****
    // We need to hashcode the String representation of the created timestamp
    // to make it immune to clock adjustments to Date made by the JVM in
    // response to the locale.
    result = prime * result + ((created == null) ? 0 : getCreated().hashCode());
    //System.out.println("ScimMeta hashcode: " + result);
    // ***** WARNING *****
    // We need to hashcode the String representation of the lastModified timestamp
    // to make it immune to clock adjustments to Date made by the JVM in response
    // to the locale.
    result = prime * result + ((lastModified == null) ? 0 : getLastModified().hashCode());
    //System.out.println("ScimMeta hashcode: " + result);
    result = prime * result + ((location == null) ? 0 : location.hashCode());
    //System.out.println("ScimMeta hashcode: " + result);
    result = prime * result + ((version == null) ? 0 : version.hashCode());
    //System.out.println("ScimMeta hashcode: " + result);
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
    ScimMeta other = (ScimMeta) obj;
    if (attributes == null) {
      if (other.attributes != null)
        return false;
    } else if (!attributes.equals(other.attributes))
      return false;
    if (created == null) {
      if (other.created != null)
        return false;
    } else if (!created.equals(other.created))
      return false;
    if (lastModified == null) {
      if (other.lastModified != null)
        return false;
    } else if (!lastModified.equals(other.lastModified))
      return false;
    if (location == null) {
      if (other.location != null)
        return false;
    } else if (!location.equals(other.location))
      return false;
    if (version == null) {
      if (other.version != null)
        return false;
    } else if (!version.equals(other.version))
      return false;
    return true;
  }

}
