package org.apache.directory.scim;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

public class Search {

  @GET
  public String get() {
    return "Got to GET";
  }

  @POST
  @Consumes( "application/json" )
  public String Search() {
    System.out.println("Search at root");
    return "Got here!";
  }

}

