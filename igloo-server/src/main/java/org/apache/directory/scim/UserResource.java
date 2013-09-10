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
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.directory.scim.models.ScimExtension;
import org.apache.directory.scim.models.ScimMeta;
import org.apache.directory.scim.models.ScimResponse;
import org.apache.directory.scim.models.ScimUser;
import org.apache.directory.scim.search.Filter;
import org.apache.directory.scim.search.Query;
import org.apache.directory.scim.search.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory
 *         Project</a>
 */
@Path("Users")
@Produces( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
public class UserResource {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
  
  private EscimoProviderFactory factory;
  private ProviderService provider;
  
  @Context
  public void setServletContext(ServletContext context) throws InstantiationException, IllegalAccessException {
    factory = (EscimoProviderFactory) context.getAttribute("escimoProviderFactory");
    provider = factory.getProvider();
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
    return provider.findUsers(query);
  }
  
  @GET
  @Path( "{id}" )
  public Response getUser(@PathParam("id") String id, @Context Request request, @Context UriInfo uriInfo) throws InstantiationException, IllegalAccessException
  {
    // Set up cacheControl
    CacheControl cacheControl = new CacheControl();
    cacheControl.setMaxAge(86400);
    
    // Get the requested user
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
    } else {
      responseBuilder = Response.status(Status.NOT_FOUND);
    }

    return responseBuilder.build(); 
  }
    
  @PATCH
  @Path( "{id}" )
  public ScimUser patchUser( @PathParam("id") String id, ScimUser scimUserIn ) throws InstantiationException, IllegalAccessException {
    return provider.mergeUser(id, scimUserIn);
  }

  @POST
  public Response postUser(ScimUser scimUserIn, @Context Request request, @Context UriInfo uriInfo) throws InstantiationException, IllegalAccessException {
    // Set up cacheControl
    CacheControl cacheControl = new CacheControl();
    cacheControl.setMaxAge(86400);
    
    // Get the requested user
    ScimUser scimUserOut = provider.createUser(scimUserIn);
    
    // The ResponseBuilder will be built at some point
    ResponseBuilder responseBuilder = null;
    
    if(scimUserOut != null) {
      // Generate the etag
      EntityTag etag = new EntityTag("" + scimUserOut.hashCode());
      
      ScimMeta meta = scimUserOut.getMeta();
      
      // Get the absolute URL for this user
      URI uri = uriInfo.getAbsolutePath();
      LOGGER.debug("Location: " + uri.toString());
      if(uri != null) {
        
        // Copy the ETag into the meta block
        meta.setVersion(etag.getValue());
        
        // Set the location element in the meta block
        meta.setLocation(uri.toString());
        scimUserOut.setMeta(meta);
      }
       
      // Sent the created ScimUser as the entity
      responseBuilder = Response.ok(scimUserOut);
      responseBuilder.status(Status.CREATED);
    } else {
      responseBuilder = Response.status(Status.CONFLICT);
    }
    return responseBuilder.build();
  }
  
  @PUT
  @Path( "{id}" )
  public Response putUser(@PathParam("id") String id, ScimUser scimUserIn, @Context Request request, @Context UriInfo uriInfo, @Context HttpHeaders headers) throws InstantiationException, IllegalAccessException {
    // Set up cacheControl
    CacheControl cacheControl = new CacheControl();
    cacheControl.setMaxAge(86400);

    // The ResponseBuilder will be built at some point
    ResponseBuilder responseBuilder = null;

    // Make sure the user has sent an ETag via an If-Match request header
    if(headers.getRequestHeader("If-Match") == null) {
      responseBuilder = Response.status(Status.BAD_REQUEST);
      responseBuilder.entity("Resource modification requires \"If-Match\" request header");
    } else {
      
      // Get the requested user
      ProviderService provider = factory.getProvider();
      ScimUser existingScimUser = provider.getUser(id);      
      EntityTag existingEtag = new EntityTag("" + existingScimUser.hashCode());
      
      // Make sure the ETag matches
      responseBuilder = request.evaluatePreconditions(existingEtag);
      
      // If the ResponseBuilder exists, a precondition has failed
      if(responseBuilder != null) {
        responseBuilder.entity("Resource modification requires that the \"If-Match\" request header contains the current ETag");
        responseBuilder.cacheControl(cacheControl);
        responseBuilder.tag(existingEtag);       
      } else {
        ScimUser scimUserOut = provider.replaceUser(id, scimUserIn);
        if(scimUserOut != null) {
          // Generate the etag
          EntityTag etag = new EntityTag("" + scimUserOut.hashCode());
          
          ScimMeta meta = scimUserOut.getMeta();
          
          // Get the absolute URL for this user
          URI uri = uriInfo.getAbsolutePath();
          LOGGER.debug("Location: " + uri.toString());
          if(uri != null) {
            
            // Copy the ETag into the meta block
            meta.setVersion(etag.getValue());
            
            // Set the location element in the meta block
            meta.setLocation(uri.toString());
            scimUserOut.setMeta(meta);
          }
           
          // Sent the created ScimUser as the entity
          responseBuilder = Response.ok(scimUserOut);
        } else {
          responseBuilder = Response.status(Status.CONFLICT);
        }
      }
    }
    
    return responseBuilder.build();
  }

  @POST
  @Path( ".search" )
  @Consumes( MediaType.APPLICATION_JSON )
  public ScimResponse search(Query query) throws InstantiationException, IllegalAccessException {
    ProviderService provider = factory.getProvider();
    return provider.findUsers(query);
  }

}
