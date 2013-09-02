package org.apache.directory.scim;

import java.util.HashMap;
import java.util.Map;

import org.apache.directory.scim.models.ScimExtension;
import org.apache.directory.scim.models.ScimResource;

public class ScimExtensionRegistry {
  
  private static final ScimExtensionRegistry INSTANCE = new ScimExtensionRegistry();
  
  private Map<Class<? extends ScimResource>, Map<String, Class<? extends ScimExtension>>> registry;
  
  private ScimExtensionRegistry() {
    registry = new HashMap<Class<? extends ScimResource>, Map<String, Class<? extends ScimExtension>>>();
  }
  
  public Class<? extends ScimExtension> getExtensionClass(Class<? extends ScimResource> resourceClass, String urn) {
    Class<? extends ScimExtension> extensionClass = null;
    if(registry.containsKey(resourceClass)) {
      Map<String, Class<? extends ScimExtension>> resourceMap = registry.get(resourceClass);
      if(resourceMap.containsKey(urn)) {
        extensionClass = resourceMap.get(urn);
      }
    }
    return extensionClass;
  }
  
  public static ScimExtensionRegistry getInstance() {
    return INSTANCE;
  }
  
  public void registerExtension(ScimExtension scimExtension) {
    Class<? extends ScimResource> resourceClass = scimExtension.getBaseResource();
    String urn = scimExtension.getUrn().toString();
    Class<? extends ScimExtension> extensionClass = scimExtension.getClass();
    
    System.out.println("Registering extension for URN: " + urn);
    System.out.println("    (associated resource class: " + resourceClass.getSimpleName() + ")");
    System.out.println("    (associated extension class: " + extensionClass.getSimpleName() + ")");
    
    Map<String, Class<? extends ScimExtension>> resourceMap = registry.get(resourceClass);
    if(resourceMap == null) {
      resourceMap = new HashMap<String, Class<? extends ScimExtension>>();
      registry.put(resourceClass, resourceMap);
    }
    
    if(!resourceMap.containsKey(urn)) {
      resourceMap.put(urn, extensionClass);
    }
  }

}
