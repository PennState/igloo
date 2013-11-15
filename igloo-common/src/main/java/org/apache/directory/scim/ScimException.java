package org.apache.directory.scim;

import org.apache.directory.scim.models.ScimError;

public class ScimException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private ScimError error;
  
  public ScimException(ScimError error) {
    this.error = error;
  }
  
  public ScimError getError() {
    return error;
  }

}
