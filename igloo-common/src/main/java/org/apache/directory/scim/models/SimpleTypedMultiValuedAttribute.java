/**
 * 
 */
package org.apache.directory.scim.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author stevemoyer
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
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

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    //System.out.println("SimpleTypedMultiValuedAttribute hashcode: " + result);
    result = prime * result + ((value_ == null) ? 0 : value_.hashCode());
    //System.out.println("SimpleTypedMultiValuedAttribute hashcode: " + result);
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    SimpleTypedMultiValuedAttribute other = (SimpleTypedMultiValuedAttribute) obj;
    if (value_ == null) {
      if (other.value_ != null)
        return false;
    } else if (!value_.equals(other.value_))
      return false;
    return true;
  }

}
