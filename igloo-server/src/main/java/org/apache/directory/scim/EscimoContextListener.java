/**
 * 
 */
package org.apache.directory.scim;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.directory.scim.models.ScimExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author stevemoyer
 *
 */
public class EscimoContextListener implements ServletContextListener {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(EscimoContextListener.class);
  
  private static final String PROPERTY_PROVIDER = "escimo.resource.provider";

  private static final String EXCEPTION_NO_CONTEXTPARAM = "The \"propertiesLocation\" <contextParam> must be included in web.xml";
  private static final String EXCEPTION_PROPERTIES_FILE_NOT_FOUND = "The eSCIMo properties file could not be read (it must be on the classpath).";
  private static final String EXCEPTION_EXTENSION_CLASS_NOT_ALLOWED = "The SCIM extension class was prohibited by the SecurityManager";
  private static final String EXCEPTION_EXTENSION_CLASS_NOT_CREATED = "The SCIM extension class can not be created";
  private static final String EXCEPTION_FACTORY_CLASS_NOT_ALLOWED = "The factory class was prohibited by the SecurityManager";
  private static final String EXCEPTION_FACTORY_CLASS_NOT_CREATED = "The factory class can not be created";
  private static final String EXCEPTION_FACTORY_CLASS_NOT_FOUND = "The factory class could not be found";
  private static final String EXCEPTION_PROVIDER_CLASS_NOT_ALLOWED = "The provider class was prohibited by the SecurityManager";
  private static final String EXCEPTION_PROVIDER_CLASS_NOT_CREATED = "The provider class can not be created";
  private static final String EXCEPTION_PROVIDER_CLASS_NOT_FOUND = "The provider class could not be found";
  private static final String EXCEPTION_PROVIDER_NOT_SPECIFIED = "The eSCIMo properties file doesn't specify \"" + PROPERTY_PROVIDER + "\"";

  /**
   * 
   */
  public EscimoContextListener() {
    // TODO Auto-generated constructor stub
  }

  /* (non-Javadoc)
   * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
   */
  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    // TODO Auto-generated method stub

  }

  /* (non-Javadoc)
   * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
   */
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    LOGGER.info("Context Initialized Event");
    
    ServletContext context = servletContextEvent.getServletContext();
    
    // Get the properties location
    LOGGER.info("Reading the eSCIMo properties location from web.xml");
    String propertiesLocation = context.getInitParameter("propertiesLocation");
    if(propertiesLocation == null) {
      LOGGER.error(EXCEPTION_NO_CONTEXTPARAM);
      throw new RuntimeException(EXCEPTION_NO_CONTEXTPARAM);
    }
    
    // Get the eSCIMo properties
    Properties properties = new Properties();
    try {
      properties.load(context.getResourceAsStream(propertiesLocation));
      LOGGER.info("Saving the eSCIMo properties as a context attribute");
      context.setAttribute("escimoProperties", properties);
    } catch (IOException e) {
      LOGGER.error(EXCEPTION_PROPERTIES_FILE_NOT_FOUND + ": " + propertiesLocation);
      throw new RuntimeException(EXCEPTION_PROPERTIES_FILE_NOT_FOUND + ": " + propertiesLocation);
    }
    
    // Get the provider factory class name
    String clazzName = properties.getProperty("escimo.resource.provider");
    if(clazzName == null) {
      LOGGER.error(EXCEPTION_PROVIDER_NOT_SPECIFIED);
      throw new RuntimeException(EXCEPTION_PROVIDER_NOT_SPECIFIED);
    }
    LOGGER.info("eSCIMo provider factory class name: " + clazzName);
    
    // Get a provider factory and save as a context attribute
    
//      try {
    EscimoProviderFactory factory;
    try {
      factory = new EscimoProviderFactory(clazzName);
    } catch (ClassNotFoundException e) {
      String message = EXCEPTION_PROVIDER_CLASS_NOT_FOUND + ": " + clazzName;
      LOGGER.error(message);
      throw new RuntimeException(message, e);
    } catch (InstantiationException e) {
      String message = EXCEPTION_FACTORY_CLASS_NOT_CREATED + ": " + clazzName;
      LOGGER.error(message);
      throw new RuntimeException(message);
    } catch (IllegalAccessException e1) {
      String message = EXCEPTION_FACTORY_CLASS_NOT_ALLOWED + ": " + clazzName;
      LOGGER.error(message);
      throw new RuntimeException(message);
    }
    LOGGER.info("Saving the EscimoProviderFactory as a context attribute");
    context.setAttribute("escimoProviderFactory", factory);
        
    // Get an instance of the ProviderService
    ProviderService provider = null;
    try {
      provider = factory.getProvider();
    } catch (InstantiationException e) {
      String message = EXCEPTION_PROVIDER_CLASS_NOT_CREATED + ": " + clazzName;
      LOGGER.error(message);
      throw new RuntimeException(message);
    } catch (IllegalAccessException e1) {
      String message = EXCEPTION_PROVIDER_CLASS_NOT_ALLOWED + ": " + clazzName;
      LOGGER.error(message);
      throw new RuntimeException(message);
    }
    
    // Get a list of the ScimExtensions and register them
    List<Class<? extends ScimExtension>> extensionClasses = provider.getExtensionClasses();
    if(extensionClasses != null) {
      for(Class<? extends ScimExtension> extensionClass: extensionClasses) {
        try {
          ScimExtension extension = extensionClass.newInstance();
          LOGGER.info("Registered a SCIM extension: " + extension.getClass().getSimpleName());
          ScimExtensionRegistry.getInstance().registerExtension(extension);
        } catch (InstantiationException e) {
          String message = EXCEPTION_EXTENSION_CLASS_NOT_CREATED + ": " + clazzName;
          LOGGER.error(message);
          throw new RuntimeException(message);
        } catch (IllegalAccessException e1) {
          String message = EXCEPTION_EXTENSION_CLASS_NOT_ALLOWED + ": " + clazzName;
          LOGGER.error(message);
          throw new RuntimeException(message);
        }
      }
    }
  }

}
