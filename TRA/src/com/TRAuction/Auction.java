/*
 * Read the applicatin description file "TR Auction Application Description.pdf"
 */
package com.TRAuction;


import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;


public class Auction extends baseAuction {
    private int v_auctionQuantityOffered;
    private float v_auctionClearingPrice;

    private String v_auctionBidFileName;
    private ArrayList<String> v_saAuctionBidList;
    private HashMap<String, Bid> v_auctionBidHashMap, v_awardedBidHashMap;
    private LinkedHashMap<String, Bid> v_sortedAuctionBidHashMap;

    Comparator<Entry<String, Bid>> sortByPriceAscending = new Comparator<Entry<String,Bid>>() { 
        @Override 
        public int compare(Entry<String, Bid> e1, Entry<String, Bid> e2) { 
            float v1 = e1.getValue().getBid_Lamination_Price(); 
            float v2 = e2.getValue().getBid_Lamination_Price();

            int iReturnValue;

            iReturnValue = v1 > v2 ? 1 : (v1 < v2 ? -1 : 0);
            if (DEBUG) {
                System.out.println("sortByPriceAscending:" + "):\tv1:\t" + v1 + " \tv2:\t " + v2 + " \tiValue:\t " + iReturnValue );  
            }
            return iReturnValue; 
        } 
        };   

    Comparator<Entry<String, Bid>> sortByParticipantName = new Comparator<Entry<String,Bid>>() { 
        @Override 
        public int compare(Entry<String, Bid> e1, Entry<String, Bid> e2) { 
            String v1 = (e1.getValue()).getAuctionParticipant_Name(); 
            String v2 = (e2.getValue()).getAuctionParticipant_Name();

            int iReturnValue;

            iReturnValue = v1.compareTo(v2);
            if (DEBUG) {
                System.out.println("sortByParticipantName" + "\tv1:\t" + v1 + " \tv2:\t " + v2 + " \tiValue:\t " + iReturnValue );  
            }
            return iReturnValue; 
        } 
        };  

    Auction(String p_saAuctionBidList){
        v_auctionBidFileName = p_saAuctionBidList;
        v_auctionQuantityOffered = AUCTION_QTY_OFFERED;

    }
    public void run() {
        setAuctionBids();
        sortAuctionBids();
        awardAuctionBids();
        publishAwardedAuctionBids();
  
    }

    private void setAuctionBids() {
        String nameofCurrMethod = new Object() {} 
        .getClass() 
        .getEnclosingMethod() 
        .getName(); 

        v_saAuctionBidList = readFile(v_auctionBidFileName);  
     
       
        if (DEBUG) {
            for (int i = 0; i < v_saAuctionBidList.size(); i++) {
                System.out.println("Method(" + nameofCurrMethod + "):\t" + v_saAuctionBidList.get(i));
            } 
        }

        String[] saBid;
        v_auctionBidHashMap = new HashMap<String, Bid>();
        for (String sKey : v_saAuctionBidList) {
            saBid = null;
            saBid = sKey.split(COMMA_DELIMITER); // use comma as separator         

            v_auctionBidHashMap.put(sKey, new Bid(sKey, saBid));  
         }
         
    }

    private void sortAuctionBids() {        
        String nameofCurrMethod = new Object() {} 
        .getClass() 
        .getEnclosingMethod() 
        .getName(); 

        if (DEBUG) {
            System.out.println("Start Method(" + nameofCurrMethod + "):\t"); 
        }

 
         
        Set<Entry<String, Bid>> v_auctionBidSet = v_auctionBidHashMap.entrySet();
        if (DEBUG) {
            System.out.println("Method(" + nameofCurrMethod + "):\t" + "HashMap before sorting, random order ");
         
            for(Entry<String, Bid> entry : v_auctionBidSet){  
                entry.getValue().print();
            } 
        }

       
  
        // Sort method needs a List, so let's first convert Set to List in Java 
        List<Entry<String, Bid>> listOfBidEntries 
            = new ArrayList<Entry<String, Bid>>(v_auctionBidSet); 
        
        // sorting HashMap by values using comparator 
        if (DEBUG) {
            System.out.println("Method(" + nameofCurrMethod + "):\t" + "Sort ... ");
        }
        Collections.sort(listOfBidEntries, sortByPriceAscending); 
        Collections.reverse(listOfBidEntries);

        v_sortedAuctionBidHashMap = new LinkedHashMap<String, Bid>(listOfBidEntries.size()); 
            
        // copying entries from List to Map 
        for(Entry<String, Bid> entry : listOfBidEntries){ 
            v_sortedAuctionBidHashMap.put(entry.getKey(), entry.getValue()); 
        } 
        
        if (DEBUG) {
            System.out.println("Method(" + nameofCurrMethod + "):\t" + "HashMap after sorting entries by price value "); 
        }
        Set<Entry<String, Bid>> entrySetSortedByValue = v_sortedAuctionBidHashMap.entrySet(); 
        
        if (DEBUG) {
            for(Entry<String, Bid> mapping : entrySetSortedByValue){ 
             mapping.getValue().print();
        } 
        }
        if (DEBUG) {
            System.out.println("End Method(" + nameofCurrMethod + "):\t"); 
        }
    }

    private void awardAuctionBids() {
        String nameofCurrMethod = new Object() {} 
        .getClass() 
        .getEnclosingMethod() 
        .getName(); 
        if (DEBUG) {
            System.out.println("Start Method(" + nameofCurrMethod + "):\t"); 
        }

        int count;
        float v_lastBidPrice;
        float v_currentBidPrice;
        int v_currentBidQty;
        Boolean b_firstBid = true;
        ArrayList<String> va_samePriceBids=new ArrayList<>();
        int index;
        
        int v_currentAuctionQtyOffered; 
        int v_lastAuctionQtyOffered = v_auctionQuantityOffered;
        v_lastBidPrice = -2000;
        count = 0;
        v_awardedBidHashMap = new HashMap<String, Bid>();

        int v_totalSamePriceBidQty =0;
        int v_cummulativeAuctionQtyOffered =0;

        Set<String> keys = v_sortedAuctionBidHashMap.keySet();
        for (String key : keys) {
            v_currentBidQty = v_sortedAuctionBidHashMap.get(key).getBid_Lamination_Qty();
            v_currentBidPrice = v_sortedAuctionBidHashMap.get(key).getBid_Lamination_Price();        
            v_currentAuctionQtyOffered = v_lastAuctionQtyOffered - v_currentBidQty;

            if (DEBUG) {
                System.out.println("\nMethod(" + nameofCurrMethod + ")Price:\t" + v_currentBidPrice + " -- Qty: \t" + v_currentBidQty);
            } 

            if (b_firstBid) {
                if (DEBUG) {
                    System.out.println(count +"\tFirst Bid: ");
                }
                b_firstBid = false;

                v_lastBidPrice = v_currentBidPrice;
                v_auctionClearingPrice = v_currentBidPrice;

                v_totalSamePriceBidQty += v_currentBidQty;
                v_cummulativeAuctionQtyOffered += (v_currentBidQty <= v_lastAuctionQtyOffered ? v_currentBidQty : v_lastAuctionQtyOffered);

                va_samePriceBids.add(key);    
            }
            else {

                if (v_currentBidPrice == v_lastBidPrice) {
                    if (DEBUG) {
                        System.out.println(count +"Same price Bid: " + "Current: " + v_currentBidPrice + "\tLast:\t" + v_lastBidPrice);
                    }
                    v_lastBidPrice = v_currentBidPrice;
                    v_auctionClearingPrice = v_currentBidPrice;
                                              
                    v_totalSamePriceBidQty += v_currentBidQty;
                    v_cummulativeAuctionQtyOffered += (v_currentBidQty <= v_lastAuctionQtyOffered ? v_currentBidQty : v_lastAuctionQtyOffered);

                    va_samePriceBids.add(key);                    
                }
                else {
                    if (DEBUG) {
                        System.out.println(count +"\tDifferent price Bid: " + "Current: " + v_currentBidPrice + "\tLast:\t" + v_lastBidPrice);
                    }        
                    // add same price Bids to awardedBids list
                    index = 0; 		
                    String thisKey;
                    int thisQty, thisAwardedQty;
                    float thisPrice;

                    //award last series of same price bids
                    while (va_samePriceBids.size() > index) {
                        thisKey = va_samePriceBids.get(index);
                        thisQty = (v_sortedAuctionBidHashMap.get(thisKey)).getBid_Lamination_Qty();
                        thisPrice = (v_sortedAuctionBidHashMap.get(thisKey)).getBid_Lamination_Price();

                        thisAwardedQty = (thisQty*v_cummulativeAuctionQtyOffered)/v_totalSamePriceBidQty;

                        if (DEBUG) {
                            System.out.println("Method(" + nameofCurrMethod + ")Price:\t" + thisPrice + " -- Qty: \t" + thisQty);
                            System.out.println("Method(" + nameofCurrMethod + ")thisAwardedQty:\t" + thisAwardedQty + " -- v_totalSamePriceBidQty: \t" + v_totalSamePriceBidQty);
                        }
                        v_sortedAuctionBidHashMap.get(thisKey).print();

                        v_sortedAuctionBidHashMap.get(thisKey).setAwarded_Qty(thisAwardedQty); //set to remaining auction limit
                        v_awardedBidHashMap.put((v_sortedAuctionBidHashMap.get(thisKey)).getAuctionKey(), v_sortedAuctionBidHashMap.get(thisKey));
                        index++;
                        }

                        if (v_lastAuctionQtyOffered <= 0) {
                            break;
                        }
                        else {
                        //reset variables for next same price bids
                        v_totalSamePriceBidQty = 0;
                        v_cummulativeAuctionQtyOffered = 0;
                        va_samePriceBids.clear();   

                        v_lastBidPrice = v_currentBidPrice;
                        v_auctionClearingPrice = v_currentBidPrice;
                
                        v_totalSamePriceBidQty += v_currentBidQty;
                        v_cummulativeAuctionQtyOffered += (v_currentBidQty <= v_lastAuctionQtyOffered ? v_currentBidQty : v_lastAuctionQtyOffered);

                        //add the current bid to the next series of same price bids
                        va_samePriceBids.add(key);  
                        }


                    }



            }

            v_lastAuctionQtyOffered = v_currentAuctionQtyOffered;
            if (DEBUG) {
                System.out.println(count +"\tLast Auction Qty Offered: " + v_lastAuctionQtyOffered + "\t" + "Clearing Price: " + v_auctionClearingPrice); 
            }
            count++;


        }   

        // add same price Bids to awardedBids list
        
        index = 0; 		
        String thisKey;
        int thisQty, thisAwardedQty;
        float thisPrice;

        //award last series of same price bids
        while (va_samePriceBids.size() > index) {
            thisKey = va_samePriceBids.get(index);
            thisQty = (v_sortedAuctionBidHashMap.get(thisKey)).getBid_Lamination_Qty();
            thisPrice = (v_sortedAuctionBidHashMap.get(thisKey)).getBid_Lamination_Price();

            thisAwardedQty = (thisQty*v_cummulativeAuctionQtyOffered)/v_totalSamePriceBidQty;

            if (DEBUG) {
                System.out.println("Method(" + nameofCurrMethod + ")Price:\t" + thisPrice + " -- Qty: \t" + thisQty);
                System.out.println("Method(" + nameofCurrMethod + ")thisAwardedQty:\t" + thisAwardedQty + " -- v_totalSamePriceBidQty: \t" + v_totalSamePriceBidQty);
            }

            v_sortedAuctionBidHashMap.get(thisKey).print();

            v_sortedAuctionBidHashMap.get(thisKey).setAwarded_Qty(thisAwardedQty); //set to remaining auction limit
            v_awardedBidHashMap.put((v_sortedAuctionBidHashMap.get(thisKey)).getAuctionKey(), v_sortedAuctionBidHashMap.get(thisKey));
            index++;
            }

 
    
        if (DEBUG) {
            System.out.println("\nMethod(" + nameofCurrMethod + "):\t\tAuction Clearing Price\t" + v_auctionClearingPrice);
          } 
    }


    void publishAwardedAuctionBids() {
        String nameofCurrMethod = new Object() {} 
        .getClass() 
        .getEnclosingMethod() 
        .getName(); 


        Bid thisAwardedBid;
        Set<String> AwardedBids = v_awardedBidHashMap.keySet();
        for (String key : AwardedBids) {
            thisAwardedBid = v_awardedBidHashMap.get(key);
            thisAwardedBid.setAwarded_Clearing_Price (v_auctionClearingPrice);
            if (DEBUG) {
                //System.out.println("\nMethod(" + nameofCurrMethod + "):\tAwarded Bids\t" + key + " -- " + thisAwardedBid);
                //System.out.println("\nMethod(" + nameofCurrMethod + ")Price:\tAwarded Bids Price\t" + (v_awardedBidHashMap.get(key)).getBid_Lamination_Price() + " -- Qty: \t" + (v_awardedBidHashMap.get(key)).getBid_Lamination_Qty());
                thisAwardedBid.print();
            } 
        }

        Set<Entry<String, Bid>> v_awardedAuctionBidSet = v_awardedBidHashMap.entrySet();
        if (DEBUG) {
            System.out.println("Method(" + nameofCurrMethod + "):\t" + "Awarded HashMap before sorting, random order ");
        
            for(Entry<String, Bid> entry : v_awardedAuctionBidSet){ 
                entry.getValue().print();
            } 
        }

        // Sort method needs a List, so let's first convert Set to List in Java 
        List<Entry<String, Bid>> listOfAwardedBidEntries 
            = new ArrayList<Entry<String, Bid>>(v_awardedAuctionBidSet); 
        
        // sorting HashMap by values using comparator 
        if (DEBUG) {
            System.out.println("Method(" + nameofCurrMethod + "):\t" + "Sort ... ");
        }
        Collections.sort(listOfAwardedBidEntries, sortByParticipantName); 
        //Collections.reverse(listOfAwardedBidEntries);

        v_sortedAuctionBidHashMap = new LinkedHashMap<String, Bid>(listOfAwardedBidEntries.size()); 
            
        // copying entries from List to Map 
        for(Entry<String, Bid> entry : listOfAwardedBidEntries){ 
            if (DEBUG) {
                System.out.println("Method(" + nameofCurrMethod + "):\t" + entry.getKey() + " ==> " + String.valueOf(entry.getValue())); 
            }
            v_sortedAuctionBidHashMap.put(entry.getKey(), entry.getValue()); 
        } 
        
        if (DEBUG) {
            System.out.println("Method(" + nameofCurrMethod + "):\t" + "HashMap after sorting entries by values "); 
        }

        Set<Entry<String, Bid>> entrySetSortedByValue = v_sortedAuctionBidHashMap.entrySet(); 

        String v_participantName, v_lastparticipantName = "";
        FileWriter thisFileWriter = null;

        for(Entry<String, Bid> mapping : entrySetSortedByValue){ 
            v_participantName =  mapping.getValue().getAuctionParticipant_Name();
            if (DEBUG) {
                System.out.println("\n\n");
            }
            if (v_participantName.compareTo(v_lastparticipantName) != 0) {
                if (thisFileWriter != null) { closeFile(thisFileWriter);}

                thisFileWriter = openFile(v_participantName + "_" + v_auctionBidFileName);

            }

            if (DEBUG) { mapping.getValue().print(); }

            writeToFile(thisFileWriter, mapping.getValue().toStringCSV());

            v_lastparticipantName = v_participantName;
        } 
        if (thisFileWriter != null) { closeFile(thisFileWriter);}     

    if (DEBUG) {
        System.out.println("End Method(" + nameofCurrMethod + "):\t"); 
    }
}




 
  
}
