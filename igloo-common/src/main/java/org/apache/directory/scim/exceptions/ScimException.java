package org.apache.directory.scim.exceptions;

import javax.ws.rs.core.Response.Status;

import org.apache.directory.scim.models.ScimError;

public class ScimException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private ScimError error;
  private Status status;
  
  public ScimException(ScimError error, Status status) {
    this.error = error;
    this.status = status;
  }
  
  public ScimError getError() {
    return error;
  }
  
  public Status getStatus() {
    return status;
  }

}
