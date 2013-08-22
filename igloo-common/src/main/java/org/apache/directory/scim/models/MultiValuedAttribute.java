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

}
