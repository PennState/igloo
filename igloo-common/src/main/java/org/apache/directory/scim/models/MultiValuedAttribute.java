package org.apache.directory.scim.models;

import java.util.List;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.directory.scim.exceptions.ScimException;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class MultiValuedAttribute {

  @XmlElement(name = "display")
  private String display;
  
  @XmlElement(name = "operation")
  private String operation;
  
  @XmlElement(name = "primary")
  private boolean primary = false;

  public static final String MULTIPLE_PRIMARIES_ERROR = "Multiple entries were flagged as primary.  Only on primary is allowed per attribute";
  
  /**
   * Returns the primary value
   * @param values
   * @return
   */
  static <T extends MultiValuedAttribute> T getPrimary(List<T> values)
  {
    T retVal = null;
    
    if (values != null)
    {
      for (T t : values)
      {
        if (t.isPrimary())
        {
          retVal = t;
          break;
        }
      }
    }
    
    return retVal;
  }

  static <T extends MultiValuedAttribute> void addValue(T newValue, List<T> values)
  {
    if (values != null)
    {
      if (newValue != null && newValue.isPrimary())
      {
        for (T t : values)
        {
          t.setPrimary(false);
        }
      }
      
      if (newValue != null)
      {
        values.add(newValue);
      }
    }
  }
  
  static <T extends MultiValuedAttribute> void validatePrimaryUniqueness(List<T> values) throws ScimException
  { 
    if (values == null)
    {
      return;
    }
    
    boolean primaryFound = false;
    
    for (T t : values)
    {
      if(t != null) {
        if (t.isPrimary() && primaryFound == false)
        {
          primaryFound = true;
        }
        else if (t.isPrimary() && primaryFound == true)
        {
          throw new ScimException(new ScimError("400", MULTIPLE_PRIMARIES_ERROR), Status.BAD_REQUEST);
        }
      }
    }
  }

  
  
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
    //System.out.println("MultiValuedAttribute hashcode: " + result);
    result = prime * result + ((display == null) ? 0 : display.hashCode());
    //System.out.println("MultiValuedAttribute hashcode: " + result);
    result = prime * result + ((operation == null) ? 0 : operation.hashCode());
    //System.out.println("MultiValuedAttribute hashcode: " + result);
    result = prime * result + (primary ? 1231 : 1237);
    //System.out.println("MultiValuedAttribute hashcode: " + result);
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
