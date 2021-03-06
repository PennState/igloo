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

import java.net.URI;
import java.util.List;

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

import org.apache.directory.scim.exceptions.ScimException;
import org.apache.directory.scim.models.ScimError;
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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
  
  private static final String ERROR_CODE_STRING_BAD_REQUEST = Integer.toString(Status.BAD_REQUEST.getStatusCode());
  private static final String ERROR_CODE_STRING_CONFLICT = Integer.toString(Status.CONFLICT.getStatusCode());
  private static final String SERVER_ERROR_CODE = "500";
  
  private static final String ERROR_DESCRIPTION_MISSING_IF_MATCH = "Resource modification requires \"If-Match\" request header";
  private static final String ERROR_DESCRIPTION_UNMATCHED_IF_MATCH = "Resource modification requires that the \"If-Match\" request header contains the current ETag";
  private static final String ERROR_INVALID_URI_MESSAGE = "Unable to extract the URI from the URI Info";
  
  private EscimoProviderFactory factory;
  private ProviderService provider;
  
  @Context
  private UriInfo uriInfo;
  
  @Context
  public void setServletContext(ServletContext context) throws InstantiationException, IllegalAccessException {
    factory = (EscimoProviderFactory) context.getAttribute("escimoProviderFactory");
    provider = factory.getProvider();
  }
  
  @GET
  public Response getUser( @QueryParam("attributes") String attributes,
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
    return search(query);
  }
  
  @GET
  @Path( "{id}" )
  public Response getUser(@PathParam("id") String id, @Context Request request) throws InstantiationException, IllegalAccessException
  {
    // The ResponseBuilder will be built at some point
    ResponseBuilder responseBuilder = null;
    
    try {
      // Set up cacheControl
      CacheControl cacheControl = new CacheControl();
      cacheControl.setMaxAge(86400);
      
      // Get the requested user
      ScimUser scimUser = provider.getUser(id);
      
      if(scimUser != null) {
        URI uri = uriInfo.getAbsolutePath();
        setMeta(scimUser);
        // Generate the etag
        EntityTag etag = new EntityTag("" + scimUser.getMeta().getVersion());
        responseBuilder = request.evaluatePreconditions(etag);
        
        if(responseBuilder != null) {
          // The user object hasn't changed so just return the cache-control and etag
          responseBuilder.cacheControl(cacheControl);
          responseBuilder.tag(etag);
        } else {          
          // Add the user as the response entity
          responseBuilder = Response.ok(scimUser);
          
          // Set the cache control, location and eTag headers
          responseBuilder.cacheControl(cacheControl);
          responseBuilder.location(uri);
          responseBuilder.tag(etag);        
        }      
      } else {
        responseBuilder = Response.status(Status.NOT_FOUND);
      }
    } catch(ScimException e) {
      responseBuilder = Response.status(e.getStatus());
      responseBuilder.entity(e.getError());
    }

    return responseBuilder.build(); 
  }
    
  @PATCH
  @Path( "{id}" )
  public Response patchUser( @PathParam("id") String id, ScimUser scimUserIn ) throws InstantiationException, IllegalAccessException {
    ResponseBuilder responseBuilder = null;
    try {
      ScimUser scimUser = provider.mergeUser(id,  scimUserIn);
      responseBuilder = Response.ok(scimUser);
    } catch(ScimException e) {
      responseBuilder = Response.status(e.getStatus());
      responseBuilder.entity(e.getError());
    }
    
    return responseBuilder.build();
  }

  @POST
  public Response postUser(ScimUser scimUserIn, @Context Request request, @Context UriInfo uriInfo) throws InstantiationException, IllegalAccessException {
    // The ResponseBuilder will be built at some point
    ResponseBuilder responseBuilder = null;

    try {
      // Set up cacheControl
      CacheControl cacheControl = new CacheControl();
      cacheControl.setMaxAge(86400);
      
      // Get the requested user
      ScimUser scimUserOut = provider.createUser(scimUserIn);
      
      // Generate the etag
      EntityTag etag = new EntityTag("" + scimUserOut.hashCode());
      
      ScimMeta meta = scimUserOut.getMeta();
      
      // Get the absolute URL for this user
      URI uri = uriInfo.getAbsolutePath();

      if(uri != null) {
        LOGGER.debug("Location: " + uri.toString());

        // Copy the ETag into the meta block
        meta.setVersion(etag.getValue());
        
        // Set the location element in the meta block
        meta.setLocation(uri.toString());
        scimUserOut.setMeta(meta);
      }
       
      // Sent the created ScimUser as the entity
      responseBuilder = Response.ok(scimUserOut);
      responseBuilder.status(Status.CREATED);
    } catch(ScimException e) {
      responseBuilder = Response.status(e.getStatus());
      responseBuilder.entity(e.getError());
    }
    
    return responseBuilder.build();
  }
  
  @PUT
  @Path( "{id}" )
  public Response putUser(@PathParam("id") String id, ScimUser scimUserIn, @Context Request request, @Context UriInfo uriInfo, @Context HttpHeaders headers) throws InstantiationException, IllegalAccessException {
    // The ResponseBuilder will be built at some point
    ResponseBuilder responseBuilder = null;

    try {
      // Set up cacheControl
      CacheControl cacheControl = new CacheControl();
      cacheControl.setMaxAge(86400);
  
      // Make sure the user has sent an ETag via an If-Match request header
      if(headers.getRequestHeader("If-Match") == null) {
        responseBuilder = Response.status(Status.BAD_REQUEST);
        responseBuilder.entity(new ScimError(ERROR_CODE_STRING_BAD_REQUEST, ERROR_DESCRIPTION_MISSING_IF_MATCH));
      } else {
        
        // Get the requested user
        ProviderService provider = factory.getProvider();
        ScimUser existingScimUser = provider.getUser(id);      
        EntityTag existingEtag = new EntityTag("" + existingScimUser.hashCode());
        
        // Make sure the ETag matches
        responseBuilder = request.evaluatePreconditions(existingEtag);
        
        // If the ResponseBuilder exists, a precondition has failed
        if(responseBuilder != null) {
          responseBuilder = Response.status(Status.BAD_REQUEST);
          responseBuilder.entity(new ScimError(ERROR_CODE_STRING_CONFLICT, ERROR_DESCRIPTION_UNMATCHED_IF_MATCH));
          responseBuilder.cacheControl(cacheControl);
          responseBuilder.tag(existingEtag);       
        } else {
          
          // Replace the user and get the new value back
          ScimUser scimUserOut = provider.replaceUser(id, scimUserIn);
          
          // Generate the etag
          EntityTag etag = new EntityTag("" + scimUserOut.hashCode());
          
          ScimMeta meta = scimUserOut.getMeta();
          
          // Get the absolute URL for this user
          URI uri = uriInfo.getAbsolutePath();
          if(uri != null) {
            LOGGER.debug("Location: " + uri.toString());

            // Copy the ETag into the meta block
            meta.setVersion(etag.getValue());
            
            // Set the location element in the meta block
            meta.setLocation(uri.toString());
            scimUserOut.setMeta(meta);
          }
           
          // Sent the created ScimUser as the entity
          responseBuilder = Response.ok(scimUserOut);
        }
      }
    } catch(ScimException e) {
      responseBuilder = Response.status(e.getStatus());
      responseBuilder.entity(e.getError());
    }
    
    return responseBuilder.build();
  }

  @POST
  @Path( ".search" )
  @Consumes( MediaType.APPLICATION_JSON )
  public Response search(Query query) throws InstantiationException, IllegalAccessException {
    ResponseBuilder responseBuilder = null;
    try {
      ScimResponse<ScimUser> scimResponse = provider.findUsers(query);
      
      List<ScimUser> scimUsers = scimResponse.getResources();
           
      if(scimUsers != null) {
        for (ScimUser scimUser : scimUsers) {
          setMeta(scimUser);
        }
      }
      
      responseBuilder = Response.ok(scimResponse);
    } catch(ScimException e) {
      responseBuilder = Response.status(e.getStatus());
      responseBuilder.entity(e.getError());
    }
    
    return responseBuilder.build();
  }

  private void setMeta(ScimUser scimUser) throws ScimException {
    ScimMeta meta = scimUser.getMeta();
        
    if(uriInfo != null) {
      meta.setVersion(Integer.toString(scimUser.hashCode()));
      meta.setLocation(uriInfo.getAbsolutePath().toString());
    }
    else {
      ScimError error = new ScimError(SERVER_ERROR_CODE, ERROR_INVALID_URI_MESSAGE);
      throw new ScimException(error, Status.INTERNAL_SERVER_ERROR);
    }     
  }
  
}
