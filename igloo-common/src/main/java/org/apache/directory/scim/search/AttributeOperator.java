package org.apache.directory.scim.search;

public enum AttributeOperator implements Operator {
  
  EQ,
  CO,
  SW,
  PR(true),
  GT,
  GE,
  LT,
  LE;
  
  private boolean oneOperand;
  
  private AttributeOperator() {
    this(false);
  }
  
  private AttributeOperator(boolean oneOperand) {
    this.oneOperand = oneOperand;
  }

  @Override
  public boolean isAttributeOperator(String value) {
    return valueOf(value.toUpperCase()) == null;
  }

  @Override
  public boolean isGroupingOperator(String value) {
    return false;
  }

  @Override
  public boolean isLogicalOperator(String value) {
    return false;
  }
  
  public boolean isOneOperand() {
    return oneOperand;
  }
  
}
