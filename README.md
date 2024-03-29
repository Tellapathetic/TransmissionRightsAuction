# Transmission Rights Auction (TRA)
## Purpose:
This code is intended to emulate the Transimissions Rights Market program by [IESO](https://www.ieso.ca/sector-participants/market-operations/markets-and-related-programs/transmission-rights-market), but a little bit simplified. 

The code awards megawatt quantities through an auction, by setting a clearing price (lowest price needed to sell all available transmission rights) and then providing transmission rights in order of bid price.

## Buisness Rules:
The following rules are the guidelines I used to make the program.
- One or many organizations can participate in an auction.
- Each bidder can submit multiple bids with different price and quantity pairs.
- For each auction, the price and quantity pairs are unique for a bidder.
- The bids are sorted descending by price; and bid quantities are awarded from highest to lowest price till all megawatt quantities are awarded.
- The market clearing price for an auction is set to the price of the last quantity and pair awarded (i.e. the lowest price) to allocate all offered auction quantities.
- A tie break is required when two or more bidders have submitted the same bid price for an auction and there are not enough offered auction quantities to satisfy those bid submissions that are tied. The remaining auction quantities are allocated to the bidders in proportion to their bid quantities.

## Core Functions:
What the program does and the order it does it in.
1)	Read the submitted bids from a csv file.
2)	Sort the bids from highest price to lowest.
3)	Determine the market clearing price.
4)	Award successful bidders.
5)	Publish auction results in a csv file format.

## Code Files:
The Java files within the TRA folder.
- configurationParameters.java – defines constant variables used in the application.
- Bid.java – defines a bid object, methods to set/get its attributes and print the object.
- baseAuction.java – defines common variables and methods for an auction.
- Auction.java – defines an auction object and the core functions.
- calculationEngine.java – defines the main class that can run one or more auctions.

## Instructions:
Step-by-step intended for VS Code
1)	Set the bid list for an auction in the calculationEngine.runAuctions() method by specifying an input test file. There is a list of available of input files in the “/TRA/” directory. 
Example: testFile3.csv
2)	Set the auction quantity offered in the configurationParameters class. This is how many Megawatts are available for auction. 
3)	(Optional) Set the DEBUG parameter to true in the configurationParameters class to run the application in debug mode. This will display what is going on behind the scenes in the terminal.
4)	Run the calculationEngine class.
5)	View the CSV output files generated in the “/TRA/” directory using the format:  <participantName>_<auctionBidFileName>
Example: RBC_testFile3.csv

## Work in Progress!
This is one of my first more serious coding projects and it is still unfinished.

In the future I plan to:
- Scale the code to process multiple auctions.
- Improve performance by using Java Threads.
-	Add functionality to accept input and output from an XML file.
-	Add a user interface (e.g. using JavaScript, HTML, Python) to accept input and present output.
-	Add functionality to accept input and output from a database.

