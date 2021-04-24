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
 * Purpose : The purpose of this class is to provide the method to perform
 *           analysis of the string contents of a text file. The analysis 
 *           consists of the number of words that are of specific length,
 *           Also the average number of characters associated with the content
 *           stream.
 *           
 * Amendment History 
 * ================= 
 * 
 * Date       Author        Reason 
 * ====       ======        ======
 * 16/04/2021 T.M.McCormack Initial Release.
 * 
 *****************************************************************************/
package com.eu.synalink;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author mccormam
 *
 */
public class WordCount
{
    private static final String WORD_TEST = "E:\\Development\\Synlogic\\wordtest.txt";
    
    
    /**
     * Read file content into string.
     * 
     * @param filePath - file [ath and name.
     * @return file content as string
     */
    public static String readFileContent(String filePath) 
    {
        String content = "";
 
        // Read all the contents from the named file.

        // Check that the file exists and is a file.
        boolean bFileFound = new File(filePath).isFile();
        
        // If the file exists, proceed to read the contents.
        if (bFileFound)
        {
            // Open the file using buffered reader.
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
            {
                String strCurrentLine;
    
                // While the file is being read, line is not null, continue to
                // concatenate the line.
                while ((strCurrentLine = br.readLine()) != null) 
                {
                    content = content.concat(strCurrentLine);
                }
       
            } 
            // Exception for file not found.
            catch (FileNotFoundException fne) 
            {
                fne.printStackTrace();   
            }
            // Exception for IO.
            catch (IOException e) 
            {
                e.printStackTrace();   
            }
        }
        // Otherwise, file is not found or is not a readable file. Report.
        else
        {
            System.out.println("File " +filePath+ " Not Found.");
        }
 
        // Return file contents.
        return content;
    }
    
    
    /**
     * Main executable.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        WordCount wordStat = new WordCount();
        
        wordStat.wordCount(WORD_TEST);
    }
    
    /**
     * Method to determine the length of words in the file string.
     * 
     * @param wordFile - file string content
     * @return true if processing successful, false otherwise.
     */
    public boolean wordCount(String wordFile)
    {
        boolean bReturn = false;
        
        Integer[] iWordCount = new Integer[30];
        
        // Initialise the word length array to 0.
        Arrays.fill(iWordCount, 0);
        
        // Obtain the contents of the named file.
        String strFileContents = readFileContent(wordFile);
    
        // If the file contents is not blank and not null. Process
        // the file line contents. Set the process flag to return to true.
        if ((strFileContents != null) && (!strFileContents.equals("")))
        {
            bReturn = true;
            
            // Create the tokens of the individual words in the file
            StringTokenizer wordTokenizer = new StringTokenizer(strFileContents);

            // Replace white space and special characters with null, so only consider full
            // alpha numeric characters.
            String replaceContent = strFileContents.replaceAll("\\s+", "").replaceAll("&", "");
            
            // Determine the number of words that are tokenised. Also the length of the 
            // number of characters in the file contents string. Calculate the average
            // length characters in a word.
            int nWord = wordTokenizer.countTokens();
            int nLength = replaceContent.length();
            double aveLength = ((double)nLength/nWord);
            
            // Iterate for the words in the token. Remove special characters
            // from the word token. Determine the word length and index for
            // word length
            while (wordTokenizer.hasMoreTokens())
            {
                String strWord = wordTokenizer.nextToken();
    
                String strArray = removeSpecial(strWord);
                
                int iIndex = strArray.length();
                
                iWordCount[iIndex] = (iWordCount[iIndex] + 1);
            }
            
            // Display the number of words (Tokens) in the string contents and the 
            // average number of character in a word.
            System.out.println("Word Count : " +nWord );
            System.out.println("Average Word Count : " +aveLength );
           
            // Display the length of words
            printWordCount(iWordCount);
        }
        
        System.out.println("\n\n");
        
        return bReturn;
    }

    /**
     * Method to remove special characters in the string for processing.
     * 
     * @param strToken  - Tokenised list of words.
     * @return processed string
     */
    public String removeSpecial( String strToken)
    {
        String strReturn = null;
         
        // Check if the token is null or blank. If so, return null.
        if ((strToken == null) || (strToken.equals("")))
        {
            return null;
        }
        // Otherwise process the string to remove special characters.
        else
        {
            // Set pattern for non alpha-numeric. Determine if the
            // String has non alpha-numeric or special characters.
            Pattern pattern = Pattern.compile("[^\\w\\s]");
            Matcher matcher = pattern.matcher(strToken);
            boolean hasSpecialChars = matcher.find();
            
            // Determine if special characters are present. If so,
            // process string to remove them.
            if (hasSpecialChars)
            {
                 strReturn = strToken.replaceAll("[^a-zA-Z0-9//&]+","");
            }
            // Otherwise special characters are no present. Return string
            // intact.
            else
            {
                strReturn = new String(strToken);
            }
        }
        
        return strReturn;
    }
    
    /**
     * Method to print out the length of words and how many.
     * 
     * @param iArrWordCount - array of word lengths.
     */
    public void printWordCount(Integer [] iArrWordCount)
    {
        int iFreqMax = 0;
        
        // Determine the integer array to retain the count of word lengths.
        Integer [] iWordFreq = new Integer[iArrWordCount.length];
                        
        // Initialise the word length array to zero.
        Arrays.fill(iWordFreq, 0);
        
        // Increment over the word length array.
        for (int i = 1; i < iArrWordCount.length; i++)
        {
            // ELiminate the element where the length of the word is zero.
            if (iArrWordCount[i] != 0)
            {
                // Check if the current word length is greater than the maximum
                // word length encountered in the iteration. IF so, set the new
                // maximum word length
                if (iArrWordCount[i] >= iFreqMax)
                {
                    iFreqMax = iArrWordCount[i];
                }
                    
                // Display the word length for this iteration and the index.
                System.out.println("Number of words of length " +i+ " is " +iArrWordCount[i] );
            }
        }
        
        // Initialise the word frequency statement string.
        String strWordFreq = "";
        
        // Iterate over the word length array.
        for (int i = 1; i < iArrWordCount.length; i++)
        {
            // Add the index(es) where the maximum length(s) occurs in the 
            // word frequency string.
            if (iArrWordCount[i] == iFreqMax)
            {
                strWordFreq = strWordFreq.concat(new String(" " +i+","));
            }
        }

        // Display the most frequently occurring word length and the index of the occurrence.
        System.out.println("The most frequently occurring word length is " +iFreqMax+ 
                           " for word lengths of " +strWordFreq.substring(0,(strWordFreq.length()-1)));
    }

}
