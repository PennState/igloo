package org.apache.directory.scim.memory;

import java.util.ArrayList;
import java.util.List;

import org.apache.directory.scim.ProviderService;
import org.apache.directory.scim.models.ScimGroup;
import org.apache.directory.scim.models.ScimResponse;
import org.apache.directory.scim.models.ScimUser;
import org.apache.directory.scim.search.Query;

public class MemoryProvider implements ProviderService {

  public ScimGroup createGroup(ScimGroup arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public ScimUser createUser(ScimUser arg0) {
    System.out.println("Creating user: " + arg0.getUserName());
    // TODO Auto-generated method stub
    return null;
  }

  public void deleteGroup(ScimGroup arg0) {
    // TODO Auto-generated method stub
    
  }

  public void deleteUser(ScimUser arg0) {
    // TODO Auto-generated method stub
    
  }

  public ScimResponse findGroups(Query arg0) {
    ScimResponse response = new ScimResponse();
    List<ScimGroup> groups = new ArrayList<ScimGroup>();
    response.setResources(groups);
    // TODO Auto-generated method stub
    return response;
  }

  public ScimResponse findUsers(Query arg0) {
    ScimResponse response = new ScimResponse();
    List<ScimUser> users = new ArrayList<ScimUser>();
    response.setTotalResults(users.size());
    response.setItemsPerPage(arg0.getCount());
    response.setResources(users);
    // TODO Auto-generated method stub
    return response;
  }

  public ScimGroup getGroup(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public ScimUser getUser(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public ScimGroup mergeGroup(ScimGroup arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public ScimUser mergeUser(ScimUser arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public ScimGroup replaceGroup(ScimGroup arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public ScimUser replaceUser(ScimUser arg0) {
    // TODO Auto-generated method stub
    return null;
  }


}
