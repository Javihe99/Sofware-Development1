////////////////////////////////////////////////////////////////////
// checkstyle:
// Checks Java source code for adherence to a set of rules.
// Copyright (C) 2020 Jiajia Ye Jiawang He
////////////////////////////////////////////////////////////////////
package Transport4Future.TokenManagement;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName ){
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test Suite(){
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void TestApp(){
        assertTrue( true );
    }
}
