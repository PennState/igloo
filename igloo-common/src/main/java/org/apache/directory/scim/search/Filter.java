package org.apache.directory.scim.search;

public class Filter {

  private Expression<?> expression;
  private String filter;

  public Filter(String filter) {
    this.filter = filter;
    parseFilterString();
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
    parseFilterString();
  }

  private void parseFilterString() {
  }

}
