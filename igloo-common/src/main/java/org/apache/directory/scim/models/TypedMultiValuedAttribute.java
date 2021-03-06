package org.apache.directory.scim.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class TypedMultiValuedAttribute<T extends ScimType> extends MultiValuedAttribute {

  @XmlElement(name = "type")
  private T type;

  /**
   * @return the type
   */
  public T getType() {
    return type;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(T type) {
    this.type = type;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    //System.out.println("MultiValuedAttribute hashcode: " + result);
    // ***** WARNING *****
    // The type enum's name is used here so that the resulting hashcode will
    // be consistent across JVM restarts.
    result = prime * result + ((type == null) ? 0 : type.name().hashCode());
    //System.out.println("MultiValuedAttribute hashcode: " + result);
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
    TypedMultiValuedAttribute other = (TypedMultiValuedAttribute) obj;
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type))
      return false;
    return true;
  }

}
