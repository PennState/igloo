/**
 * 
 */
package org.apache.directory.scim.search.lexerparser;


/**
 * @author stevemoyer
 *
 */
public class FilterParserSubclass extends FilterParser {

  /**
   * @param lexer
   */
  public FilterParserSubclass(FilterLexer lexer) {
    super(lexer);
    // TODO Auto-generated constructor stub
  }
  
  public void logExpression(Expression<?> expression) {
    super.logExpression(expression);
  }
  
  public void logExpression(String title, Expression<?> expression) {
    super.logExpression(title, expression);
  }

}
