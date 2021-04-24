/*****************************************************************************
 * 
 * Copyright : Cortech Information Ltd (2021) 
 * 
 * File Name : WordCount.java
 *
 * Author : T. M. McCormack (Cortech Information Ltd) 
 * 
 * Date Written : 16 April 2021
 * 
 * Purpose : The purpose of this class is to provide Java Unit tests for the
 *           word count analysis. The test consists of two files provide by
 *           the client and a negative test file a non-existent file.
 *           
 * Amendment History 
 * ================= 
 * 
 * Date       Author        Reason 
 * ====       ======        ======
 * 16/04/2021 T.M.McCormack Initial Release.
 * 
 *****************************************************************************/
package test.com.eu.synalink;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.eu.synalink.WordCount;


public class SynaLinkTest 
{
    private static final String WORD_TEST_01 = "./src/test/resources/wordtest.txt";
    private static final String WORD_TEST_02 = "./src/test/resources/daily_bible.txt";
    private static final String WORD_TEST_03 = "./src/test/resources/nofile.txt";

    
    WordCount wordStat;
    
    @Before
    public void setUp() throws Exception 
    {
        wordStat = new WordCount();
    }

    @After
    public void tearDown() throws Exception 
    {
    }

    @Test
    public void testReadFile01() 
    {
        boolean bProcess = wordStat.wordCount(WORD_TEST_01);

        assertTrue("File Processed: ", bProcess);
    }
    
    @Test
    public void testReadFile02() 
    {   
        boolean bProcess = wordStat.wordCount(WORD_TEST_02);

        assertTrue("File Processed: ", bProcess);
    }
    
    @Test
    public void testReadFile03() 
    {   
        boolean bProcess = wordStat.wordCount(WORD_TEST_03);

        assertFalse("File not processed: ", bProcess);
    }
    
}
