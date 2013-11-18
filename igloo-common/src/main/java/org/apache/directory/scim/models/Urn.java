/**
 * 
 */
package org.apache.directory.scim.models;

import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import org.apache.directory.scim.exceptions.IllegalUrnException;

/**
 * @author stevemoyer
 *
 */
@XmlType(name = "urn")
@XmlAccessorType(XmlAccessType.FIELD)

/**
 * http://www.ietf.org/rfc/rfc2141.txt
 * http://stackoverflow.com/questions/5492885/is-there-a-java-library-that-validates-urns
 * 
 * @author Steve Moyer <smoyer@psu.edu>
 */
public class Urn {
  
  private static final String URN_RFC2141_REGEX = "^urn:[a-z0-9][a-z0-9-]{0,31}:([a-z0-9()+,\\-.:=@;$_!*']|%[0-9a-f]{2})+$";
  private static final Pattern URN_RFC2141_PATTERN = Pattern.compile(URN_RFC2141_REGEX, Pattern.CASE_INSENSITIVE);
  
  @XmlValue
  private String urn;
  
  public Urn(String urnString) throws IllegalUrnException {
    System.out.println("URN String: " + urnString);
    if(!isUrn(urnString)) {
      throw new IllegalUrnException("URN does not match RFC2141 specification");
    }
    this.urn = urnString;
  }
  
  public static boolean isUrn(String urnString) {
    return URN_RFC2141_PATTERN.matcher(urnString).matches();
  }
  
  public String toString() {
    return urn;
  }

}
