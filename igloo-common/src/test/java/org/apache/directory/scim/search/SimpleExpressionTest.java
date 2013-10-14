/**
 * 
 */
package org.apache.directory.scim.search;

import static org.junit.Assert.*;

import org.apache.directory.scim.search.lexerparser.AttributeOperator;
import org.apache.directory.scim.search.lexerparser.SimpleExpression;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author stevemoyer
 *
 */
public class SimpleExpressionTest {

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testToString() {
    SimpleExpression expression = new SimpleExpression();
    expression.setOperator(AttributeOperator.EQ);
    expression.setLeft("userName");
    expression.setRight("swm16");
    System.out.println(expression.toString("|   |   "));
  }

}
