package org.apache.directory.scim.models;

public abstract class TypedMultiValuedAttribute<T extends ScimType> extends MultiValuedAttribute {

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

}
