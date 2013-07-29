package org.apache.directory.scim.search;

public class Criteria {

  private String specification;

  public Criteria(String specification) {
    System.out.println("Criteria specification: " + specification);
    this.specification = specification;
    parse();
  }

  private void parse() {
  }

}
