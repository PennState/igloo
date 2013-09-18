/**
 * 
 */
package org.apache.directory.scim.search.lexerparser;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author stevemoyer
 *
 */
@RunWith(JUnitParamsRunner.class)
public class FilterLexerTest extends AbstractLexerParserTest {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(FilterLexerTest.class);

  private FilterLexer lexer_;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    lexer_ = new FilterLexer(EXTRA_2);
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }
  
  @SuppressWarnings("unused")
  private String[] getExamplesAndExtras() {
    return EXAMPLES_AND_EXTRAS;
  }
  
  @Test
  @Parameters(method = "getExamplesAndExtras")
  public void testFilterLexer(String filterString) {
    LOGGER.info("Lexing filter string: " + filterString);
    FilterLexerSubclass lexer = new FilterLexerSubclass(filterString);
    int index = Arrays.asList(EXAMPLES_AND_EXTRAS).indexOf(filterString);
    assertTrue("Update the test class so that EXAMPLES and TOKENS have the same number of elements", index > -1 && index < EXAMPLE_AND_EXTRA_INFIX_TOKENS.length);
    assertArrayEquals(EXAMPLE_AND_EXTRA_INFIX_TOKENS[index], lexer.getTokens().toArray());
  }
  
  @Test
  public void testHasNext() {
    assertTrue(lexer_.hasNext());
    FilterLexer emptyLexer = new FilterLexer(NO_FILTER);
    assertFalse(emptyLexer.hasNext());
  }
  
  @Test
  public void testNext() {
    List<String> tokens = lexer_.getTokens();
    int tokenCount = tokens.size();
    for(int i = 0; i < tokenCount; i++) {
      assertTrue(lexer_.hasNext());
      String token = lexer_.next();
      assertEquals(tokens.get(i), token);
    }
    assertFalse(lexer_.hasNext());
  }
  
  @Test
  public void testRemove() {
    try {
      lexer_.remove();
      fail("This should have thrown an UnsupportedOperationException");
    } catch(UnsupportedOperationException e) {
      // This is the expected result
    } catch(Exception e) {
      fail("This should have thrown an UnsupportedOperationException");
    }
  }

}
