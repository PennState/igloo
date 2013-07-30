package org.apache.directory.scim.memory;

import java.util.ArrayList;
import java.util.List;

import org.apache.directory.scim.ProviderService;
import org.apache.directory.scim.common.Group;
import org.apache.directory.scim.common.Response;
import org.apache.directory.scim.common.Response.Resources;
import org.apache.directory.scim.common.User;
import org.apache.directory.scim.search.Query;

public class MemoryProvider implements ProviderService {

  public Group createGroup(Group arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public User createUser(User arg0) {
    System.out.println("Creating user: " + arg0.getUserName());
    // TODO Auto-generated method stub
    return null;
  }

  public void deleteGroup(Group arg0) {
    // TODO Auto-generated method stub
    
  }

  public void deleteUser(User arg0) {
    // TODO Auto-generated method stub
    
  }

  public Response findGroups(Query arg0) {
    Response response = new Response();
    List<User> users = new ArrayList<User>();
    Resources resources = new Resources();
    resources.getResource().addAll(users);
    response.setResources(resources);
    // TODO Auto-generated method stub
    return response;
  }

  public Response findUsers(Query arg0) {
    Response response = new Response();
    List<User> users = new ArrayList<User>();
    response.setTotalResults(new Long(users.size()));
    response.setItemsPerPage(arg0.getCount());
    Resources resources = new Resources();
    resources.getResource().addAll(users);
    response.setResources(resources);
    // TODO Auto-generated method stub
    return response;
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
