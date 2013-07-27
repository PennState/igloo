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

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.directory.scim.common.Address;
import org.apache.directory.scim.common.Meta;
import org.apache.directory.scim.common.MultiValuedAttribute;
import org.apache.directory.scim.common.User;

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
  public List<User> getUser() {
    List<User> users = new ArrayList<User>();
    return users;
  }
  
  @GET
  @Path( "{id}" )
  public User getUser(@PathParam("id") String id)
  {
    return null; 
  }
    
  @PATCH
  public User patchUser( @PathParam("user") String userId ) {
    return null;
  }

  @POST
  public User postUser(@PathParam("user") String userId) {
    return null;
  }


}
