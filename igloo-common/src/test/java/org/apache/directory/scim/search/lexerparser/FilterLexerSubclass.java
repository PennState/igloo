/**
 * 
 */
package org.apache.directory.scim.search.lexerparser;

import java.util.List;

/**
 * @author stevemoyer
 *
 */
public class FilterLexerSubclass extends FilterLexer {

  /**
   * @param filter
   */
  public FilterLexerSubclass(String filter) {
    super(filter);
  }
  
  @Override
  public List<String> getTokens() {
    return super.getTokens();
  }
  
  @Override
  public void setTokens(List<String> tokens) {
    super.setTokens(tokens);
  }

}
