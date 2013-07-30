package org.apache.directory.scim;

public class EscimoProviderFactory {
  
  private String clazzName;
  private Class<ProviderService> providerClazz;
  
  public EscimoProviderFactory(String clazzName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    this.clazzName = clazzName;
    Class<?> clazz = Class.forName(clazzName);
    if(clazz.newInstance() instanceof ProviderService) {
      providerClazz = (Class<ProviderService>) clazz;
    }
  }
  
  public ProviderService getProvider() throws InstantiationException, IllegalAccessException {
    return providerClazz.newInstance();
  }

}
