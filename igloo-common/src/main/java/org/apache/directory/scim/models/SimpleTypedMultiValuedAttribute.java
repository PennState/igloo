/**
 * 
 */
package org.apache.directory.scim.models;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author stevemoyer
 *
 */
public abstract class SimpleTypedMultiValuedAttribute<T extends ScimType> extends TypedMultiValuedAttribute<T> {
  
  @XmlElement(name = "value")
  private String value_;

  /**
   * @return the value
   */
  public String getValue() {
    return value_;
  }

  /**
   * @param value the value to set
   */
  public void setValue(String value) {
    this.value_ = value;
  }

}
