/*
 * Read the applicatin description file "TR Auction Application Description.pdf"
 */
package com.TRAuction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class baseAuction {
    //protected String AUCTION_FILE_DIRECTORY = configurationParameters.AUCTION_FILE_DIRECTORY;
    protected String TAB_DELIMITER = configurationParameters.TAB_DELIMITER;
    protected String COMMA_DELIMITER = configurationParameters.COMMA_DELIMITER;
   
    protected boolean DEBUG = configurationParameters.DEBUG;   
    protected boolean INFO = configurationParameters.INFO; 

    protected int AUCTION_QTY_OFFERED = configurationParameters.AUCTION_QTY_OFFERED;
 

public String getCurrentDateTime() {     
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now();  
   return(dtf.format(now));  
} 

public LocalDateTime getLocalDateTime(String p_sDateTime ){
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
       String date = p_sDateTime;
       return LocalDateTime.parse(date, formatter);
}

/*
 public BufferedWriter openFile(String pFileName) {
    BufferedWriter bw = null;
    try {
   File file = new File(pFileName);

    if (!file.exists()) {
       file.createNewFile();
    }

    } catch (IOException ioe) {
     ioe.printStackTrace();
  }
  finally
  { 
     try{
        if(bw!=null)
       bw.close();
     }catch(Exception ex){
         System.out.println("Error in closing the BufferedWriter"+ex);
      }
  }
  return bw; 
}

public void closeFile(BufferedWriter pBufferWriter) {
    try {

        pBufferWriter.close();

    } catch (IOException ioe) {
     ioe.printStackTrace();
  }
  finally
  { 
     try{
        if(pBufferWriter!=null)
        pBufferWriter.close();
     }catch(Exception ex){
         System.out.println("Error in closing the BufferedWriter"+ex);
      }
  }
}
*/
    ArrayList<String> readFile (String pfileName) {
        String nameofCurrMethod = new Object() {} 
                                .getClass() 
                                .getEnclosingMethod() 
                                .getName(); 

        String sfileName =  pfileName;
        if (DEBUG) {
            System.out.println("\nMethod(" + nameofCurrMethod + "):\t" + sfileName);
          }
        ArrayList<String> auctionBids = new ArrayList<String>();
        String data;
        try {
                File myObj = new File(sfileName);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                if (DEBUG) {
                    System.out.println("Method(" + nameofCurrMethod + "):\t" + data);
                }
                auctionBids.add(data);
                }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          if (DEBUG) {
            //System.out.println("\n Method(" + nameofCurrMethod + "):\t" + auctionBids);
            }   
            return auctionBids;
    }   
/*
    public void writeToFile(BufferedWriter pBufferWriter, String psContent) {
      try {

          pBufferWriter.write(psContent);
          System.out.println("File written Successfully");

      } catch (IOException ioe) {
       ioe.printStackTrace();
    }
    finally
    { 
       try{
          if(pBufferWriter!=null)
          pBufferWriter.close();
       }catch(Exception ex){
           System.out.println("Error in closing the BufferedWriter"+ex);
        }
    }
}
*/

  public FileWriter openFile(String ps_fileName) {
    FileWriter myWriter = null;
    try {
      File myObj = new File(ps_fileName); 
      
      if (myObj.delete()) { 
        if (DEBUG) {
        System.out.println("Deleted the file: " + myObj.getName());
      }
      } else {
        if (DEBUG) {
        System.out.println("Failed to delete the file.");
      }
      }  
          
     myWriter = new FileWriter(ps_fileName);
   
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return myWriter;
  }

  public void writeToFile(FileWriter p_fileWriter, String p_string) {

    try {
         p_fileWriter.write(p_string);
      //System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void closeFile(FileWriter p_fileWriter) {
    try {
      p_fileWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
