package org.apache.directory.scim.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.directory.scim.ProviderService;
import org.apache.directory.scim.models.ScimExtension;
import org.apache.directory.scim.models.ScimGroup;
import org.apache.directory.scim.models.ScimResource;
import org.apache.directory.scim.models.ScimResponse;
import org.apache.directory.scim.models.ScimUser;
import org.apache.directory.scim.models.Urn;
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

  public void deleteGroup(String id) {
    // TODO Auto-generated method stub
    
  }

  public void deleteUser(String id) {
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

  public ScimUser getUser(String id) {
    ScimUser user = new ScimUser();
    UUID uuid = UUID.fromString(id);
    user.setId(uuid);
    user.setUserName("test");
    return user;
  }

  public ScimGroup mergeGroup(String id, ScimGroup arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public ScimUser mergeUser(String id, ScimUser arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public ScimGroup replaceGroup(String id, ScimGroup arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public ScimUser replaceUser(String id, ScimUser arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<Class<? extends ScimExtension>> getExtensionClasses() {
    // TODO Auto-generated method stub
    return null;
  }

}
