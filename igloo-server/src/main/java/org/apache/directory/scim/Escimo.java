package org.apache.directory.scim;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath( "scim/v2.0" )
public class Escimo extends Application {
  
  @Override
  public Set<Class<?>> getClasses()
  {
    Set<Class<?>> classes = new HashSet<Class<?>>();
    classes.add(SchemaResource.class);
    classes.add(UserResource.class);
    return classes;
  }

}
