package org.apache.directory.scim.search;

public interface Operator {

  boolean isAttributeOperator(String value);
  boolean isGroupingOperator(String value);
  boolean isLogicalOperator(String value);
  
}
