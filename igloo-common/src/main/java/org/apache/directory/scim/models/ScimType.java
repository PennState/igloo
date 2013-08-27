package org.apache.directory.scim.models;

public interface ScimType {
  
  /**
   * ScimTypes are generally implemented as Java enums but when we're generating
   * hashcodes, we need access to the name of the enum element rather than the
   * ordinal.
   * 
   * @return a String containing the enum value's name
   */
  String name();

}
