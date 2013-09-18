/**
 * 
 */
package org.apache.directory.scim.search;

import static org.junit.Assert.*;

import org.apache.directory.scim.search.lexerparser.AttributeOperator;
import org.apache.directory.scim.search.lexerparser.CompoundExpression;
import org.apache.directory.scim.search.lexerparser.LogicalOperator;
import org.apache.directory.scim.search.lexerparser.SimpleExpression;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author stevemoyer
 *
 */
public class CompoundExpressionTest {

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
  public void test() {
    fail("Not yet implemented");
  }
  
  @Test
  public void testToString() {
    SimpleExpression idExpression = new SimpleExpression();
    idExpression.setOperator(AttributeOperator.EQ);
    idExpression.setLeft("id");
    idExpression.setRight("CDC6DF38-16CB-499D-8A4B-370AFB36C592");
    
    SimpleExpression userExpression = new SimpleExpression();
    userExpression.setOperator(AttributeOperator.SW);
    userExpression.setLeft("userName");
    userExpression.setRight("smoyer");
    
    CompoundExpression orExpression = new CompoundExpression();
    orExpression.setOperator(LogicalOperator.OR);
    orExpression.setLeft(idExpression);
    orExpression.setRight(userExpression);
    
    SimpleExpression lastExpression = new SimpleExpression();
    lastExpression.setOperator(AttributeOperator.EQ);
    lastExpression.setLeft("name.lastName");
    lastExpression.setRight("Moyer");
    
    CompoundExpression andExpression = new CompoundExpression();
    andExpression.setOperator(LogicalOperator.AND);
    andExpression.setLeft(lastExpression);
    andExpression.setRight(orExpression);
    
    System.out.println(andExpression.toString(""));
  }

}
