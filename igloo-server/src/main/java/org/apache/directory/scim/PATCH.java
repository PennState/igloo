/**
 * 
 */
package org.apache.directory.scim;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.HttpMethod;

/**
 * @author stevemoyer
 *
 */
@Target( {ElementType.METHOD} )
@Retention( RetentionPolicy.RUNTIME )
@HttpMethod( "PATCH" )
@Documented
public @interface PATCH {

}
