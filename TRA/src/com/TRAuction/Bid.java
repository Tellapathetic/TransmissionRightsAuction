/*
 * Read the applicatin description file "TR Auction Application Description.pdf"
 */
package com.TRAuction;
import java.time.LocalDateTime;

public class Bid extends baseAuction
{
private String sKey; 
private int iAuction_ID; 
private int iAuctionParticipant_ID;
private String sAuctionParticipant_Name; 
private int iBid_Lamination;
private int iBid_Lamination_Qty;
private float fBid_Lamination_Price;
private String sBid_Submitter_ID;
private LocalDateTime dtBid_Subnmission_Timestamp;
private int iAwarded_Qty;
private float fAwarded_Clearing_Price;

Bid(){};
Bid (String psKey, String[] psaBid) {
    this.sKey	=	psKey;
    this.iAuction_ID	=	Integer.parseInt(psaBid[0])	;
    this.iAuctionParticipant_ID	=	Integer.parseInt(psaBid[1])	;
    this.sAuctionParticipant_Name	=	psaBid[2]	;
    this.iBid_Lamination	=	Integer.parseInt(psaBid[3])	;
    this.iBid_Lamination_Qty	=	Integer.parseInt(psaBid[4])	;
    this.fBid_Lamination_Price	=	Float.parseFloat(psaBid[5])	;
    this.sBid_Submitter_ID	=	psaBid[6]	;
    this.dtBid_Subnmission_Timestamp	=	getLocalDateTime(psaBid[7])	;
    this.iAwarded_Qty	=	-1	;
    this.fAwarded_Clearing_Price	=	-1	;
    print();
}
Bid (String psKey,
    int piAuction_ID,
    int piAuctionParticipant_ID,
    String psAuctionParticipant_Name, 
    int piBid_Lamination,
    int piBid_Lamination_Qty,
    float pfBid_Lamination_Price,
    String psBid_Submitter_ID,
    LocalDateTime pdtBid_Subnmission_Timestamp)
{
    this.sKey	=	psKey;
    this.iAuction_ID	=	piAuction_ID	;
    this.iAuctionParticipant_ID	=	piAuctionParticipant_ID	;
    this.sAuctionParticipant_Name	=	psAuctionParticipant_Name	;
    this.iBid_Lamination	=	piBid_Lamination	;
    this.iBid_Lamination_Qty	=	piBid_Lamination_Qty	;
    this.fBid_Lamination_Price	=	pfBid_Lamination_Price	;
    this.sBid_Submitter_ID	=	psBid_Submitter_ID	;
    this.dtBid_Subnmission_Timestamp	=	pdtBid_Subnmission_Timestamp	;
    this.iAwarded_Qty	=	-1	;
    this.fAwarded_Clearing_Price	=	-1	;
    print();
}

void print() {
    String nameofCurrMethod = new Object() {} 
    .getClass() 
    .getEnclosingMethod() 
    .getName(); 
    
if (DEBUG) 
  
    System.out.println("Method(" + nameofCurrMethod + "):\t" + getAuction_ID() + TAB_DELIMITER + getAuctionParticipant_ID() + TAB_DELIMITER + getAuctionParticipant_Name() + TAB_DELIMITER + getBid_Lamination() + TAB_DELIMITER + 
    getBid_Lamination_Qty() + TAB_DELIMITER + getBid_Lamination_Price() + TAB_DELIMITER + getBid_Submitter_ID() + TAB_DELIMITER + getBid_Subnmission_Timestamp() 
    + TAB_DELIMITER + getAwarded_Qty()  + TAB_DELIMITER + getAwarded_Clearing_Price() + "\n");
}

String toStringCSV() {
    return getAuction_ID() + TAB_DELIMITER + getAuctionParticipant_ID() + TAB_DELIMITER + getAuctionParticipant_Name() + TAB_DELIMITER + getBid_Lamination() + TAB_DELIMITER + 
    getBid_Lamination_Qty() + TAB_DELIMITER + getBid_Lamination_Price() + TAB_DELIMITER + getBid_Submitter_ID() + TAB_DELIMITER + getBid_Subnmission_Timestamp() 
    + TAB_DELIMITER + getAwarded_Qty()  + TAB_DELIMITER + getAwarded_Clearing_Price() + "\n";
}
void setAuctionKey (String psKey)
{
    sKey	=	psKey	;
}
void setAuction_ID (int piAuction_ID)
{
    iAuction_ID	=	piAuction_ID	;
}
void setAuctionParticipant_ID (int piAuctionParticipant_ID)
{
    iAuctionParticipant_ID	=	piAuctionParticipant_ID	;
}
void setAuctionParticipant_Name (String psAuctionParticipant_Name)
{
    sAuctionParticipant_Name	=	psAuctionParticipant_Name	;
}
void setBid_Lamination (int piBid_Lamination)
{
    iBid_Lamination	=	piBid_Lamination	;
}
void setBid_Lamination_Qty (int piBid_Lamination_Qty)
{
    iBid_Lamination_Qty	=	piBid_Lamination_Qty	;
}
void setBid_Lamination_Price (float pfBid_Lamination_Price)
{
    fBid_Lamination_Price	=	pfBid_Lamination_Price	;
}    
void setBid_Submitter_ID (String psBid_Submitter_ID)
{
    sBid_Submitter_ID	=	psBid_Submitter_ID	;
}

void setBid_Subnmission_Timestamp (LocalDateTime pdtBid_Subnmission_Timestamp)
{
    dtBid_Subnmission_Timestamp	=	pdtBid_Subnmission_Timestamp	;
}
void setAwarded_Qty (int piAwarded_Qty)
{
    iAwarded_Qty	=	piAwarded_Qty	;
}
void setAwarded_Clearing_Price (float pfAwarded_Clearing_Price)
{
    fAwarded_Clearing_Price	=	pfAwarded_Clearing_Price	;
}    

String getAuctionKey ()
{
    return sKey	;
}
int getAuction_ID ()
{
    return iAuction_ID	;
}
int getAuctionParticipant_ID ()
{
    return iAuctionParticipant_ID	;
}
String getAuctionParticipant_Name ()
{
    return sAuctionParticipant_Name	;
}
int getBid_Lamination ()
{
    return iBid_Lamination	;
}
int getBid_Lamination_Qty ()
{
    return iBid_Lamination_Qty	;
}
float getBid_Lamination_Price ()
{
    return fBid_Lamination_Price	;
}
String getBid_Submitter_ID ()
{
    return sBid_Submitter_ID	;
}
LocalDateTime getBid_Subnmission_Timestamp ()
{
    return dtBid_Subnmission_Timestamp	;
}
int getAwarded_Qty ()
{
    return iAwarded_Qty	;
}
float getAwarded_Clearing_Price ()
{
    return fAwarded_Clearing_Price	;
}

}


