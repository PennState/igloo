package org.apache.directory.scim;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.wink.providers.jackson.WinkJacksonJaxbJsonProvider;

@ApplicationPath( "v1" )
public class Escimo extends Application {
  
  @Override
  public Set<Class<?>> getClasses()
  {
    Set<Class<?>> classes = new HashSet<Class<?>>();
    classes.add(Search.class);
    classes.add(UserResource.class);

    classes.add(WinkJacksonJaxbJsonProvider.class);
    return classes;
  }

}
