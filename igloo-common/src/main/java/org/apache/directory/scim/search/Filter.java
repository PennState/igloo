package org.apache.directory.scim.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Filter {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(Filter.class);

  private Expression<?> expression;
  private String filter;
  
  protected Filter() {
  }

  public Filter(String filter) {
    setFilter(filter);
  }
  
  /**
   * @return the expression
   */
  public Expression<?> getExpression() {
    return expression;
  }

  /**
   * @param expression the expression to set
   */
  public void setExpression(Expression<?> expression) {
    this.expression = expression;
  }

  /**
   * @return the filter
   */
  public String getFilter() {
    return filter;
  }

  /**
   * @param filter the filter to set
   */
  public void setFilter(String filter) {
    this.filter = filter;
    this.expression = parseFilter(filter);
  }
  
  protected String[] convertFromInfixToPrefix(String[] tokens) {
    LOGGER.info("Converting to infix form");
    logTokens(tokens);
    // Loop through tokens to find attribute operators
    for(int i = 0; i < tokens.length; i++) {
      String token = tokens[i];
      AttributeOperator operator = null;
      try {
        operator = AttributeOperator.valueOf(token.toUpperCase());
        LOGGER.info("    Token: " + token + ", AttributeOperator: true");
        LOGGER.info("    AttributeOperator is one-operand: " + operator.isOneOperand());
        if(i > 0) {
          tokens[i] = tokens[i - 1];
          tokens[i - 1] = operator.name();
        }
      } catch(IllegalArgumentException e) {
        LOGGER.info("    Token: " + token + ", AttributeOperator: false");
      }
    }
    return tokens;
  }
  
  protected boolean isAttributeName(String token) {
    return false;
  }
  
  protected boolean isValue(String token) {
    return false;
  }
  
  private void logTokens(String[] tokens) {
    LOGGER.info("Tokens: ");
    for(String token: tokens) {
      LOGGER.info("    " + token);
    }    
  }

  protected Expression<?> parseFilter(String filter) {
    LOGGER.info("Parsing filter string: " + filter);
    String[] tokens = tokenizeFilter(filter);
    tokens = convertFromInfixToPrefix(tokens);
    return null;
  }
  
  protected String[] tokenizeFilter(String filter) {
    LOGGER.info("Tokenizing filter string:" + filter);
    
    // Trim leading and trailing spaces
    String cleanString = filter.trim();
    LOGGER.info("Cleaned leading and trailing spaces: " + cleanString);
    
    // Make sure there are spaces around parenthesis
    cleanString = cleanString.replaceAll("\\(", " ( ");
    cleanString = cleanString.replaceAll("\\)", " ) ");
    LOGGER.info("Padded parenthesis with spaces (maybe extra): " + cleanString);
    
    // Convert multiple consecutive spaces to single spaces
    cleanString = cleanString.replaceAll(" +", " ");
    LOGGER.info("Padded parenthesis with single spaces: " + cleanString);
    
    // Convert the string to tokens
    String[] tokens = cleanString.split(" ");
    logTokens(tokens);
    return tokens;
  }

}
