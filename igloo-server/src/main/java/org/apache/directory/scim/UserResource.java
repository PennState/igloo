/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.directory.scim;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.directory.scim.common.Address;
import org.apache.directory.scim.common.Meta;
import org.apache.directory.scim.common.MultiValuedAttribute;
import org.apache.directory.scim.common.User;

import org.apache.directory.scim.search.Filter;
import org.apache.directory.scim.search.Query;

import com.sun.xml.bind.v2.ContextFactory;

/**
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory
 *         Project</a>
 */
@Path("Users")
@Produces( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
public class UserResource {
  
  @GET
  public List<User> getUser( @QueryParam("attributes") String attributes,
                             @QueryParam("filter") String filter,
                             @QueryParam("sortBy") String sortBy,
                             @QueryParam("sortOrder") String sortOrder,
                             @QueryParam("startIndex") String startIndex,
                             @QueryParam("count") String count )
  {
    System.out.println("Get on UserResource");
    System.out.println("Filter: " + filter);
    List<User> users = new ArrayList<User>();
    User user1 = new User();
    user1.setUserName("swm16");
    users.add(user1);
    User user2 = new User();
    user2.setUserName("smoyer");
    users.add(user2);
    return users;
  }
  
  @GET
  @Path( "{id}" )
  public User getUser(@PathParam("id") String id)
  {
    User user1 = new User();
    user1.setUserName("swm16");
    return user1; 
  }
    
  @PATCH
  public User patchUser( @PathParam("user") String userId ) {
    return null;
  }

  @POST
  public User postUser(@PathParam("user") String userId) {
    return null;
  }

  @POST
  @Path( ".search" )
  @Consumes( MediaType.APPLICATION_JSON )
  public List<User> search(Query query) {
    System.out.println("Got to search");
    System.out.println("Query: " + query);
    Filter criteria = new Filter("Filter goes here");

    List<User> users = new ArrayList<User>();
    return users;
  }

}
