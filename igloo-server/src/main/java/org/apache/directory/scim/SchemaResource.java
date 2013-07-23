package org.apache.directory.scim;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.directory.scim.common.Schema;
import org.apache.directory.scim.common.SchemaAttribute;

@Path( "Schemas" )
@Produces( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
public class SchemaResource {
  
  private static final String[] ACCESSOR_PREFIXES = {"get", "is", "has"};
  
  private static final Class<?>[] ACCESSOR_PARAMETER_TYPES = {};
  
  private Schema groupSchema = new Schema();
  private Schema psuUserSchema = new Schema();
  private Schema userSchema = new Schema();
  
  private Schema[] schemas = { groupSchema, psuUserSchema, userSchema };
  
  private Method findAccessor(Class<?> clazz, Method mutator) {
    String mutatorName = mutator.getName();
    String baseName = mutatorName.substring(3);
    Method accessor = null;
    for(String prefix: ACCESSOR_PREFIXES) {
      try {
        accessor = clazz.getMethod(prefix + baseName, ACCESSOR_PARAMETER_TYPES);
        break;
      } catch(NoSuchMethodException e) {
        // Exceptions for flow control - yech (but I didn't write the Class class)
      }
    }
    return accessor;
  }
  
  private Schema generateSchema(Class<?> clazz) throws IllegalArgumentException {
    XmlRootElement xmlRootElement = clazz.getAnnotation(XmlRootElement.class);
    if( xmlRootElement == null ) {
      throw new IllegalArgumentException( "A schema can only be generated from a class annotated with @XmlRootElement" );
    }
    System.out.println("Processing class: " + clazz.getName());
    
    Schema schema = new Schema();
    schema.setName( xmlRootElement.name() );
    Schema.Attributes attributes = new Schema.Attributes();
    
    XmlAccessType xmlAccessType = clazz.getAnnotation(XmlAccessorType.class).value();
    
    // Process fields
    Field[] fields = clazz.getDeclaredFields();
    System.out.println("  Processing " + fields.length + " fields");
    for(Field field: fields) {
      System.out.print("    Field: " + field.getName());
      if(!Modifier.isTransient(field.getModifiers())) {
        if(!field.isAnnotationPresent(XmlTransient.class)) {
          if(xmlAccessType == XmlAccessType.FIELD ||
              (xmlAccessType == XmlAccessType.PUBLIC_MEMBER && Modifier.isPublic(field.getModifiers())) ||
              field.isAnnotationPresent(XmlElement.class))  {
            SchemaAttribute attribute = new SchemaAttribute();
            attribute.setName(field.getName());
            attributes.getAttribute().add(attribute);
            System.out.println(", Result: added");
          } else {
            System.out.println(", Result: not added (per XmlAccessorType rules)");
          }
        } else {
          System.out.println(", Result: not added (field is XmlTransient)");
        }
      } else {
        System.out.println(", Result: not added (field is transient)");
      }
    }
    
    // Process accessor/mutator pairs
    Method[] methods = clazz.getDeclaredMethods();
    System.out.println("  Method count: " + methods.length);
    for(Method mutator: methods) {
      System.out.print("    Method: " + mutator.getName());
      if(!Modifier.isTransient(mutator.getModifiers())) {
        if(!mutator.isAnnotationPresent(XmlTransient.class)) {
          String methodName = mutator.getName();
          if(methodName.startsWith("set")) {
            methodName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
            Method accessor = findAccessor(clazz, mutator);
            if(accessor != null) { 
              SchemaAttribute attribute = new SchemaAttribute();
              attribute.setName(methodName);
              attribute.setType(accessor.getReturnType().getSimpleName());
              attributes.getAttribute().add(attribute);
              System.out.println(", Result: added");
            } else {
              
            }
          } else {
            System.out.println(", Result: not added (method not a mutator)");
          }
        } else {
          System.out.println(", Result: not added (method is XmlTransient)");
        }
      } else {
        System.out.println(", Result: not added (method is transient)");
      }
    }
    
    schema.setAttributes(attributes);
    return schema;
  }
  
  @GET
  public Schema[] getSchemaList() {
    groupSchema.setName( "Group" );
    userSchema.setName( "User" );
    return schemas;
  }
  
  @GET
  @Path( "User" )
  public Schema getUserSchema() {
    return userSchema;
  }
  
//  @GET
//  @Path( "PsuUser" )
//  public Schema getPsuUserSchema() {
//    return generateSchema(PsuUser.class);
//  }
  
}
