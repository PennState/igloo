package org.apache.directory.scim.search;

import org.apache.directory.scim.search.lexerparser.Expression;
import org.apache.directory.scim.search.lexerparser.FilterLexer;
import org.apache.directory.scim.search.lexerparser.FilterParseException;
import org.apache.directory.scim.search.lexerparser.FilterParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Steve Moyer <smoyer@psu.edu>
 */
public class Filter {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(Filter.class);

  private Expression<?> expression;
  private String filter;
  
  protected Filter() {
  }

  public Filter(String filter) throws FilterParseException {
    setFilter(filter);
  }
  
  /**
   * Returns the expression represented by this filter string so long as it can
   * be parsed.
   * 
   * @return the expression
   * @throws FilterParseException 
   */
  public Expression<?> getExpression() throws FilterParseException {
    Expression<?> expression = null;
    if(filter != null && !filter.isEmpty()) {
      expression = parseFilter(filter);
    }
    return expression;
  }

  /**
   * @return the filter
   */
  public String getFilter() {
    return filter;
  }

  /**
   * @param filter the filter to set
   * @throws FilterParseException 
   */
  public void setFilter(String filter) throws FilterParseException {
    this.filter = filter;
    this.expression = parseFilter(filter);
  }

  protected Expression<?> parseFilter(String filter) throws FilterParseException {
    FilterLexer lexer = new FilterLexer(filter);
    FilterParser parser = new FilterParser(lexer);
    return parser.parse();
  }
  


}
