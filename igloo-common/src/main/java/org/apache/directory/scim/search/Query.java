package org.apache.directory.scim.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "Query" )
@XmlAccessorType( XmlAccessType.FIELD )
public class Query {

  private String attributes;
  private Long count;
  private String filter;
  private String sortBy;
  private SortOrder sortOrder;
  private String startIndex;

  public String getAttributes() {
    return attributes;
  }

  public void setAttributes(String attributes) {
    this.attributes = attributes;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }

  public String getSortBy() {
    return sortBy;
  }

  public void setSortBy(String sortBy) {
    this.sortBy = sortBy;
  }

  public SortOrder getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(SortOrder sortOrder) {
    this.sortOrder = sortOrder;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n\nQuery:");
    sb.append("\n  Attributes: ");
    sb.append(attributes);
    sb.append("\n  Count: ");
    sb.append(count);
    sb.append("\n  Filter: ");
    sb.append(filter);
    sb.append("\n  SortBy: ");
    sb.append(sortBy);
    sb.append("\n  SortOrder: ");
    sb.append(sortOrder);
    sb.append("\n  StartIndex: ");
    sb.append(startIndex);
    sb.append("\n");
    return sb.toString();
  }

}
