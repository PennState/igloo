package org.apache.directory.scim.search;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.converters.ConversionFailedException;
import junitparams.converters.ConvertParam;
import junitparams.converters.ParamConverter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class FilterTest {
  
  private static final String NO_FILTER = "";
  
  private static final String JOHN_FILTER_MIXED_CASE_1 = "userName Eq \"john\"";
  private static final String JOHN_FILTER_MIXED_CASE_2 = "Username eq \"john\"";
  
  private static final String EXAMPLE_1 = "userName eq \"bjensen\"";
  private static final String EXAMPLE_2 = "name.familyName co \"O'Malley\"";
  private static final String EXAMPLE_3 = "userName sw \"J\"";
  private static final String EXAMPLE_4 = "title pr";
  private static final String EXAMPLE_5 = "meta.lastModified gt \"2011-05-13T04:42:34Z\"";
  private static final String EXAMPLE_6 = "meta.lastModified ge \"2011-05-13T04:42:34Z\"";
  private static final String EXAMPLE_7 = "meta.lastModified lt \"2011-05-13T04:42:34Z\"";
  private static final String EXAMPLE_8 = "meta.lastModified le \"2011-05-13T04:42:34Z\"";
  private static final String EXAMPLE_9 = "title pr and userType eq \"Employee\"";
  private static final String EXAMPLE_10 = "title pr or userType eq \"Intern\"";
  private static final String EXAMPLE_11 = "userType eq \"Employee\" and (emails co \"example.com\" or emails co \"example.org\")";
  
  private static final String[] EXAMPLE_1_INFIX_TOKENS = {"userName", "eq", "\"bjensen\""};
  private static final String[] EXAMPLE_2_INFIX_TOKENS = {"name.familyName", "co", "\"O'Malley\""};
  private static final String[] EXAMPLE_3_INFIX_TOKENS = {"userName", "sw", "\"J\""};
  private static final String[] EXAMPLE_4_INFIX_TOKENS = {"title", "pr"};
  private static final String[] EXAMPLE_5_INFIX_TOKENS = {"meta.lastModified", "gt", "\"2011-05-13T04:42:34Z\""};
  private static final String[] EXAMPLE_6_INFIX_TOKENS = {"meta.lastModified", "ge", "\"2011-05-13T04:42:34Z\""};
  private static final String[] EXAMPLE_7_INFIX_TOKENS = {"meta.lastModified", "lt", "\"2011-05-13T04:42:34Z\""};
  private static final String[] EXAMPLE_8_INFIX_TOKENS = {"meta.lastModified", "le", "\"2011-05-13T04:42:34Z\""};
  private static final String[] EXAMPLE_9_INFIX_TOKENS = {"title", "pr", "and", "userType", "eq", "\"Employee\""};
  private static final String[] EXAMPLE_10_INFIX_TOKENS = {"title", "pr", "or", "userType", "eq", "\"Intern\""};
  private static final String[] EXAMPLE_11_INFIX_TOKENS = {"userType", "eq", "\"Employee\"", "and", "(", "emails", "co", "\"example.com\"", "or", "emails", "co", "\"example.org\"", ")"};
  
  private static final String[] EXAMPLE_1_PREFIX_TOKENS = {"EQ", "userName", "\"bjensen\""};
  private static final String[] EXAMPLE_2_PREFIX_TOKENS = {"CO", "name.familyName", "\"O'Malley\""};
  private static final String[] EXAMPLE_3_PREFIX_TOKENS = {"SW", "userName", "\"J\""};
  private static final String[] EXAMPLE_4_PREFIX_TOKENS = {"PR", "title"};
  private static final String[] EXAMPLE_5_PREFIX_TOKENS = {"GT", "meta.lastModified", "\"2011-05-13T04:42:34Z\""};
  private static final String[] EXAMPLE_6_PREFIX_TOKENS = {"GE", "meta.lastModified", "\"2011-05-13T04:42:34Z\""};
  private static final String[] EXAMPLE_7_PREFIX_TOKENS = {"LT", "meta.lastModified", "\"2011-05-13T04:42:34Z\""};
  private static final String[] EXAMPLE_8_PREFIX_TOKENS = {"LE", "meta.lastModified", "\"2011-05-13T04:42:34Z\""};
  private static final String[] EXAMPLE_9_PREFIX_TOKENS = {"PR", "title", "and", "EQ", "userType", "\"Employee\""};
  private static final String[] EXAMPLE_10_PREFIX_TOKENS = {"PR", "title", "or", "EQ", "userType", "\"Intern\""};
  private static final String[] EXAMPLE_11_PREFIX_TOKENS = {"EQ", "userType", "\"Employee\"", "and", "(", "CO", "emails", "\"example.com\"", "or", "CO", "emails", "\"example.org\"", ")"};
  
  private static final String[] EXAMPLES = {
    EXAMPLE_1,
    EXAMPLE_2,
    EXAMPLE_3,
    EXAMPLE_4,
    EXAMPLE_5,
    EXAMPLE_6,
    EXAMPLE_7,
    EXAMPLE_8,
    EXAMPLE_9,
    EXAMPLE_10,
    EXAMPLE_11
  };
  
  private static final String[][] INFIX_TOKENS = {
    EXAMPLE_1_INFIX_TOKENS,
    EXAMPLE_2_INFIX_TOKENS,
    EXAMPLE_3_INFIX_TOKENS,
    EXAMPLE_4_INFIX_TOKENS,
    EXAMPLE_5_INFIX_TOKENS,
    EXAMPLE_6_INFIX_TOKENS,
    EXAMPLE_7_INFIX_TOKENS,
    EXAMPLE_8_INFIX_TOKENS,
    EXAMPLE_9_INFIX_TOKENS,
    EXAMPLE_10_INFIX_TOKENS,
    EXAMPLE_11_INFIX_TOKENS
  };
  
  private static final String[][] PREFIX_TOKENS = {
    EXAMPLE_1_PREFIX_TOKENS,
    EXAMPLE_2_PREFIX_TOKENS,
    EXAMPLE_3_PREFIX_TOKENS,
    EXAMPLE_4_PREFIX_TOKENS,
    EXAMPLE_5_PREFIX_TOKENS,
    EXAMPLE_6_PREFIX_TOKENS,
    EXAMPLE_7_PREFIX_TOKENS,
    EXAMPLE_8_PREFIX_TOKENS,
    EXAMPLE_9_PREFIX_TOKENS,
    EXAMPLE_10_PREFIX_TOKENS,
    EXAMPLE_11_PREFIX_TOKENS
  };
  
  private FilterSubclass filterSubclass;

  @Before
  public void setUp() throws Exception {
    filterSubclass = new FilterSubclass();
  }

  @After
  public void tearDown() throws Exception {
  }
  
  @SuppressWarnings("unused")
  private String[] getExamples() {
    return EXAMPLES;
  }
  
  @SuppressWarnings("unused")
  private Object[] getTokens() {
    Object[][] objects = new Object[INFIX_TOKENS.length][2];
    
    for(String[] tokens: INFIX_TOKENS) {
      int index = Arrays.asList(INFIX_TOKENS).indexOf(tokens);

      String tokenOut = "";
      for(String token: tokens) {
        tokenOut += token;
        tokenOut += " ";
      }
      tokenOut = tokenOut.trim();
      
      objects[index][0] = tokenOut;
      objects[index][1] = index;
    }
    return objects;
  }

  @Test
  @Parameters(method = "getTokens")
  public void testConvertFromInfixToPrefix(String infixTokenString, int index) {
    System.out.println(infixTokenString);
    String[] infixTokens = infixTokenString.split(" +");
    String[] prefixTokens = filterSubclass.convertFromInfixToPrefix(infixTokens);
    assertTrue("Update the test class so that INFIX_TOKENS and PREFIX_TOKENS have the same number of elements", index > -1 && index < INFIX_TOKENS.length);
    assertArrayEquals(PREFIX_TOKENS[index], prefixTokens);
  }
  
  @Test
  @Parameters(method = "getExamples")
  public void testFilterTokenizer(String filter) {
    String[] tokens = filterSubclass.tokenizeFilter(filter);
    int index = Arrays.asList(EXAMPLES).indexOf(filter);
    assertTrue("Update the test class so that EXAMPLES and TOKENS have the same number of elements", index > -1 && index < INFIX_TOKENS.length);
    assertArrayEquals(INFIX_TOKENS[index], tokens);
  }

}
