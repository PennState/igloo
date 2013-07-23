package org.apache.directory.scim.memory;

import java.util.HashMap;
import java.util.Map;

import org.apache.directory.scim.ProviderService;
import org.apache.directory.scim.common.Group;
import org.apache.directory.scim.common.User;

public class MemoryProvider implements ProviderService {
  
  private Map<String, Group> groups = new HashMap<String, Group>();
  
  private Map<String, User> users = new HashMap<String, User>();

  public Group createGroup(Group arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public User createUser(User arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public void deleteGroup(Group arg0) {
    // TODO Auto-generated method stub
    
  }

  public void deleteUser(User arg0) {
    // TODO Auto-generated method stub
    
  }

  public Group getGroup(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public User getUser(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public Group mergeGroup(Group arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public User mergeUser(User arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public Group replaceGroup(Group arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public User replaceUser(User arg0) {
    // TODO Auto-generated method stub
    return null;
  }

}
