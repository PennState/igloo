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

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.directory.scim.models.ScimMeta;
import org.apache.directory.scim.models.ScimResponse;
import org.apache.directory.scim.models.ScimUser;
import org.apache.directory.scim.search.Filter;
import org.apache.directory.scim.search.Query;
import org.apache.directory.scim.search.SortOrder;

/**
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory
 *         Project</a>
 */
@Path("Users")
@Produces( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
public class UserResource {
  
  private String propertiesLocation;
  private EscimoProviderFactory factory;
  
  @Context
  public void setServletContext(ServletContext context) {
    propertiesLocation = context.getInitParameter("propertiesLocation");
    
    Properties properties = (Properties) context.getAttribute("escimoProperties");
    if(properties == null) {
      properties = new Properties();
      try {
    		properties.load(context.getResourceAsStream(propertiesLocation));
    		System.out.println("Setting eSCIMo properties: " + properties);
    		context.setAttribute("escimoProperties", properties);
    	} catch (IOException e) {
    		System.out.println("Configuration file could not be read");
    	}
    }
    
    factory = (EscimoProviderFactory) context.getAttribute("escimoProviderFactory");
    if(factory == null) {
      String clazzName = properties.getProperty("escimo.resource.provider");
      System.out.println("Creating provider factory: " + clazzName);
      try {
        factory = new EscimoProviderFactory(clazzName);
        context.setAttribute("escimoProviderFactory", factory);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
  }
  
  @GET
  public ScimResponse getUser( @QueryParam("attributes") String attributes,
                           @QueryParam("count") Integer count,
                           @QueryParam("filter") Filter filter,
                           @QueryParam("sortBy") String sortBy,
                           @QueryParam("sortOrder") SortOrder sortOrder,
                           @QueryParam("startIndex") Long startIndex ) throws InstantiationException, IllegalAccessException
  { 
    Query query = new Query();
    query.setAttributes(attributes);
    query.setCount(count);
    query.setFilter(filter);
    query.setSortBy(sortBy);
    query.setSortOrder(sortOrder);
    ProviderService provider = factory.getProvider();
    return provider.findUsers(query);
  }
  
  @GET
  @Path( "{id}" )
  public Response getUser(@PathParam("id") String id, @Context Request request, @Context UriInfo uriInfo) throws InstantiationException, IllegalAccessException
  {
    Response response = null;
    
    // Set up cacheControl
    CacheControl cacheControl = new CacheControl();
    cacheControl.setMaxAge(86400);
    
    // Get the requested user
    ProviderService provider = factory.getProvider();
    ScimUser scimUser = provider.getUser(id);
    
    // The ResponseBuilder will be built at some point
    ResponseBuilder responseBuilder = null;
    
    if(scimUser != null) {
      // Generate the etag
      EntityTag etag = new EntityTag("" + scimUser.hashCode());
      responseBuilder = request.evaluatePreconditions(etag);
      
      if(responseBuilder != null) {
        // The user object hasn't changed so just return the cache-control and etag
        responseBuilder.cacheControl(cacheControl);
        responseBuilder.tag(etag);
      } else {
        
        ScimMeta meta = scimUser.getMeta();
        
        // Get the absolute URL for this user
        URI uri = uriInfo.getAbsolutePath();
        System.out.println("Location: " + uri.toString());
        if(uri != null) {
          
          // Copy the ETag into the meta block
          meta.setVersion(etag.getValue());
          
          // Set the location element in the meta block
          meta.setLocation(uri.toString());
          scimUser.setMeta(meta);
          
          // Add the user as the response entity
          responseBuilder = Response.ok(scimUser);
          
          // Set the cache control, location and eTag headers
          responseBuilder.cacheControl(cacheControl);
          responseBuilder.location(uri);
          responseBuilder.tag(etag);        
        }
      }
      
      response = responseBuilder.build();
    } else {
      //throw new WebApplicationException(Response.Status.NOT_FOUND);
      responseBuilder = Response.status(Status.NOT_FOUND);
    }

    return responseBuilder.build(); 
  }
    
  @PATCH
  public ScimUser patchUser( ScimUser user ) throws InstantiationException, IllegalAccessException {
    ProviderService provider = factory.getProvider();
    return provider.mergeUser(user);
  }

  @POST
  public ScimUser postUser(ScimUser user) throws InstantiationException, IllegalAccessException {
    ProviderService provider = factory.getProvider();
    return provider.createUser(user);
  }

  @POST
  @Path( ".search" )
  @Consumes( MediaType.APPLICATION_JSON )
  public ScimResponse search(Query query) throws InstantiationException, IllegalAccessException {
    ProviderService provider = factory.getProvider();
    return provider.findUsers(query);
  }

}
