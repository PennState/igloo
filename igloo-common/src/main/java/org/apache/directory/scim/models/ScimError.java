package org.apache.directory.scim.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Error")
@XmlType(name = "Error", propOrder = {
    "schemas",
    "Errors"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class ScimError {
  
  private static final List<String> schemas = Arrays.asList("urn:scim:schemas:core:2.0:Error");
  
  private List<ErrorTuple> errors = new ArrayList<ErrorTuple>();
  
  /**
   * 
   * @author stevemoyer
   *
   */
  @XmlType(propOrder = {
    "description",
    "code"
  })
  @XmlAccessorType(XmlAccessType.FIELD)
  private final class ErrorTuple {
    
    private String code;
    private String description;
    
    public ErrorTuple(String code, String description) {
      this.code = code;
      this.description = description;
    }

    /**
     * @return the code
     */
    public String getCode() {
      return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
      this.code = code;
    }

    /**
     * @return the description
     */
    public String getDescription() {
      return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
      this.description = description;
    }
    
  }

  public ScimError() {
  }
  
  public ScimError(String code, String description) {
    addError(code, description);
  }
  
  public void addError(String code, String description) {
    errors.add(new ErrorTuple(code, description));
  }

}
