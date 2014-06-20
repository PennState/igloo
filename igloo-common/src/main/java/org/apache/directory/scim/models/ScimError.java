package org.apache.directory.scim.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Error")
@XmlType(name = "Error")
@XmlAccessorType(XmlAccessType.NONE)
public class ScimError {
  
  @XmlElement(name = "schemas")
  private final List<String> schemas = Arrays.asList("urn:scim:schemas:core:2.0:Error");
  
  @XmlElement(name = "Errors")
  private List<ErrorTuple> errors = new ArrayList<ErrorTuple>();
  
  /**
   * 
   * @author stevemoyer
   *
   */
  @XmlType(name = "Errors", propOrder = {
    "description",
    "code"
  })
  @XmlAccessorType(XmlAccessType.NONE)
  public static final class ErrorTuple {
    
    @XmlElement(name="code")
    private String code;
    @XmlElement(name="description")
    private String description;
    
    public ErrorTuple() {
      // Needed by Object mapper
    }
    
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
  
  public void addError(ScimError error) {
    //This is to allow chaining without risking an NPE
    if (error == null)
    {
      return;
    }
    
    this.errors.addAll(error.errors);
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    for (ErrorTuple et : errors)
    {
      sb.append("Error: ");
      sb.append(et.getDescription());
      sb.append("\n");
    }
    
    return sb.toString();
  }
}
