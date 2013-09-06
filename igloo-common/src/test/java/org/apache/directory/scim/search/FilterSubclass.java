/**
 * 
 */
package org.apache.directory.scim.search;

/**
 * @author stevemoyer
 *
 */
public class FilterSubclass extends Filter {
  
  public FilterSubclass() {
    super();
  }

  /**
   * @param filter
   */
  public FilterSubclass(String filter) {
    super(filter);
  }
  
  @Override
  public String[] convertFromInfixToPrefix(String[] tokens) {
    return super.convertFromInfixToPrefix(tokens);
  }
  
  @Override
  public Expression<?> parseFilter(String filter) {
    return super.parseFilter(filter);
  }
  
  @Override
  public String[] tokenizeFilter(String filter) {
    return super.tokenizeFilter(filter);
  }

}
