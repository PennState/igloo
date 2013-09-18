package org.apache.directory.scim.search.lexerparser;

public interface Operator {

  boolean isAttributeOperator();
  boolean isGroupingOperator();
  boolean isLogicalOperator();
  String name();
  
}
