package org.apache.directory.scim.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Response")
@XmlType(name = "Response", propOrder = {
    "totalResults",
    "itemsPerPage",
    "startIndex",
    "schemas",
    "Resources"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class ScimResponse {
  
  @XmlElement(name = "itemsPerPage")
  private int itemsPerPage_;
  
  @XmlElement(name = "Resources")
  private List<? extends ScimResource> resources_;
  
  @XmlElement(name = "schemas")
  private List<String> schemas_;
  
  @XmlElement(name = "startIndex")
  private int startIndex_;
  
  @XmlElement(name = "totalResults")
  private int totalResults_;

  /**
   * @return the itemsPerPage
   */
  public int getItemsPerPage() {
    return itemsPerPage_;
  }

  /**
   * @param itemsPerPage the itemsPerPage to set
   */
  public void setItemsPerPage(int itemsPerPage) {
    this.itemsPerPage_ = itemsPerPage;
  }

  /**
   * @return the resources
   */
  public List<? extends ScimResource> getResources() {
    return resources_;
  }

  /**
   * @param resources the resources to set
   */
  public void setResources(List<? extends ScimResource> resources) {
    this.resources_ = resources;
  }

  /**
   * @return the schemas
   */
  public List<String> getSchemas() {
    return schemas_;
  }

  /**
   * @param schemas the schemas to set
   */
  public void setSchemas(List<String> schemas) {
    this.schemas_ = schemas;
  }

  /**
   * @return the startIndex
   */
  public int getStartIndex() {
    return startIndex_;
  }

  /**
   * @param startIndex the startIndex to set
   */
  public void setStartIndex(int startIndex) {
    this.startIndex_ = startIndex;
  }

  /**
   * @return the totalResults
   */
  public int getTotalResults() {
    return totalResults_;
  }

  /**
   * @param totalResults the totalResults to set
   */
  public void setTotalResults(int totalResults) {
    this.totalResults_ = totalResults;
  }

}
