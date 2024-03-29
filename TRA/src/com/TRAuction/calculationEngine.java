/*
 * Read the applicatin description file "TR Auction Application Description.pdf"
 */
package com.TRAuction;


public class calculationEngine extends baseAuction {
     public static void main(String[] args) throws Exception {
        
       
        calculationEngine thisEngine = new calculationEngine();
        thisEngine.runAuctions();

     
    }
    
    private void runAuctions() {
     String nameofCurrMethod = new Object() {} 
        .getClass() 
        .getEnclosingMethod() 
        .getName(); 

        if (INFO) {
            System.out.println("Method(" + nameofCurrMethod + "): start time\t" + getCurrentDateTime());
          }
        
        //replace later with the list of files retrieved from the auction bid file staging directory
        String saAuctionBidFileList[] = { "testFile3.csv"};

        for(int i=0;i<saAuctionBidFileList.length;i++) {  
            runAuction(saAuctionBidFileList[i]);
        }

        if (INFO) {
            System.out.println("Method(" + nameofCurrMethod + "): end time\t" + getCurrentDateTime());
          }
    }
    
    void runAuction(String p_auctionBidFileName) {
        
        Auction v_Auction = new Auction(p_auctionBidFileName);
        v_Auction.run();

    }

    }

