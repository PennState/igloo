package org.apache.directory.scim.models;

public abstract class MultiValuedAttribute {

  private String display;
  private String operation;
  private boolean primary = false;

  /**
   * @return the display
   */
  public String getDisplay() {
    return display;
  }

  /**
   * @param display
   *          the display to set
   */
  public void setDisplay(String display) {
    this.display = display;
  }

  /**
   * @return the operation
   */
  public String getOperation() {
    return operation;
  }

  /**
   * @param operation
   *          the operation to set
   */
  public void setOperation(String operation) {
    this.operation = operation;
  }

  /**
   * @return the primary
   */
  public boolean isPrimary() {
    return primary;
  }

  /**
   * @param primary
   *          the primary to set
   */
  public void setPrimary(boolean primary) {
    this.primary = primary;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    System.out.println("MultiValuedAttribute hashcode: " + result);
    result = prime * result + ((display == null) ? 0 : display.hashCode());
    System.out.println("MultiValuedAttribute hashcode: " + result);
    result = prime * result + ((operation == null) ? 0 : operation.hashCode());
    System.out.println("MultiValuedAttribute hashcode: " + result);
    result = prime * result + (primary ? 1231 : 1237);
    System.out.println("MultiValuedAttribute hashcode: " + result);
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
    MultiValuedAttribute other = (MultiValuedAttribute) obj;
    if (display == null) {
      if (other.display != null)
        return false;
    } else if (!display.equals(other.display))
      return false;
    if (operation == null) {
      if (other.operation != null)
        return false;
    } else if (!operation.equals(other.operation))
      return false;
    if (primary != other.primary)
      return false;
    return true;
  }

}
