
import java.util.*;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kolling.
 * @version 2006.03.30
 *
 * @author (of AuctionSkeleton) Lynn Marshall
 * @version 2.0
 * 
 * @author Shoana Sharma
 * @version 2017.10.06
 * 
 */
public class Auction
{
    /** The list of Lots in this auction. */
    private ArrayList<Lot> lots;

    /** 
     * The number that will be given to the next lot entered
     * into this auction.  Every lot gets a new number, even if some lots have
     * been removed.  (For example, if lot number 3 has been deleted, we don't
     * reuse it.)
     */
    private int nextLotNumber;
    /**
     * The boolean will determine it the bidding for the aution is open or closed.
     */

    private boolean isOpened;
    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
        isOpened = true;

        // you need to add something here -- see hint above.
    }

    /**
     * Add a second constructor here.  This constructor takes
     * an Auction as a parameter.  Provided the auction parameter
     * is closed, the constructor creates a new auction containing
     * the unsold lots of the closed auction.  If the auction parameter
     * is still open or null, this constructor behaves like the
     * default constructor.
     * (You need to write the entire method.)
     * @param notSoldAuction creates a new ArrayList of type Auction 
     */

    public Auction(Auction notSoldAuction)
    {
        if(notSoldAuction.isOpened == true || notSoldAuction.getNoBids() == null)
        {
            lots = new ArrayList<Lot>();
            nextLotNumber = 1;
            isOpened = true;
        }
        else //if (notSoldAuction.isOpened == false)
        {
            lots = notSoldAuction.getNoBids();
            nextLotNumber = notSoldAuction.nextLotNumber;
            isOpened = true;
        }
    }

    /**
     * Enter a new lot into the auction.  Returns false if the
     * auction is not open or if the description is null.
     * (You need to add the return type, update the documentation, 
     * and change the code.)
     *
     * @param description A description of the lot.
     */
    public boolean enterLot(String description)
    {

        if(description != null && isOpened == true )
        {
            lots.add(new Lot(nextLotNumber, description));
            nextLotNumber++;
            return true;
        }
        return false;
    }

    /**
     * Show the full list of lots in this auction.
     *
     * Print a blank line first, to make our output look nicer. 
     * If there are no lots, print a message indicating this 
     * (You need to update the code given below.)
     */
    public void showLots()
   {
        if(lots.size() == 0 || lots == null)
        {
            System.out.println(" No available lots");
        }
        else
        {
            for(Lot lot : lots) {
                System.out.println("");
                System.out.println(lot.toString());
            }
        
    }}

    /**
     * Bid for a lot.
     * Do not assume that the lots are stored in numerical order.
     * Prints a message indicating whether the bid is successful or not.
     *   
     * First print a blank line.  
     * Then print whether or not the bid is successful.
     * If the bid is successful, also print the
     * lot number, high bidder's name, and the bid value.
     * If the bid is not successful, also print the lot number 
     * and high bid (but not the high bidder's name).
     * 
     * Returns false if the auction is closed, the lot doesn't
     * exist, the bidder is null, or the bid was not positive
     * and true otherwise (even if the bid was not high enough).
     * (You need to update the return type, documentation, and code.)
     *
     * @param number The lot number being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public boolean bidFor(int lotNumber, Person bidder, long value)
    {
for (Lot lot : lots)
        if(isOpened == true && bidder != null && value > 0 && lots != null && lot.getNumber() == lotNumber)
        {
            
            {  Bid bidNew = new Bid(bidder, value);

                
                    
                    if (lot.bidFor(bidNew))
                    { 
                        System.out.println("The bid was successful.");
                        System.out.println("Lot Number is:" + lot.getNumber());
                        System.out.println("Highest Bidder is:"+ bidder.getName());
                        System.out.println("Bid Value is:" + lot.getHighestBid().getValue());

                    }
                    else
                          {
                        System.out.println("The bid was unsuccessful.");
                        System.out.println("Highest Bidder Value is:" + lot.getHighestBid().getValue());
                        System.out.println("Lot Number:" + lot.getNumber());

                  }return true;
                }
                

            
        }
        return false; 
    }

    /**
     * Return the lot with the given number. 
     * Do not assume that the lots are stored in numerical order.   
     *   
     * Returns null if the lot does not exist.
     * (You need to update the code.)
     *
     * @param lotNumber The number of the lot to return.
     *
     * @return the Lot with the given number
     */
    public Lot getLot(int lotNumber)
    {
        for(Lot lot : lots)
        {

            if( lot.getNumber() == lotNumber)
            {
                return lot;
            }
        }
        return null; 
    }

    /**
     * Closes the auction and prints information on the lots.
     * First print a blank line.  Then for each lot,
     * its number and description are printed.
     * If it did sell, the high bidder and bid value are also printed.  
     * If it didn't sell, that information is printed.
     *
     * Returns false if the auction is already closed, true otherwise.
     * (You need to update the return type, documentation, and code.)
     */
    public boolean close()
    {
        if(isOpened)
        {
            isOpened = false;
            for(Lot lot:lots)
            {
                if  (lot.getHighestBid() == null)
                {
                    System.out.println("");
                    System.out.println("The lot was unsold");
                    System.out.println("Lot number is:"+ lot.getNumber());
                    System.out.println("Lot description is:"+ lot.getDescription());

                }
                else
                {
                    System.out.println("");
                    System.out.println("Lot Number is:" + lot.getNumber());
                    System.out.println("Lot description is:"+ lot.getDescription());
                    System.out.println("Highest Bidder is:"+ lot.getHighestBid().getBidder().getName());
                    System.out.println("Highest Bid Value is:" + lot.getHighestBid().getValue());
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Returns an ArrayList containing all the items that have no bids so far.
     * (or have not sold if the auction has ended).
     * @return an ArrayList of the Lots which currently have no bids
     */
    public ArrayList<Lot> getNoBids()
    {
        ArrayList<Lot> bidNoLots = new ArrayList<Lot>();

        for (Lot lot : lots)
        {
            if (lot.getHighestBid() == null)
            {
                bidNoLots.add(lot);
            }
        }
        return bidNoLots;
    }

    /**
     * Remove the lot with the given lot number, as long as the lot has
     * no bids, and the auction is open.  
     * You must use an Iterator object to search the list and,
     * if applicable, remove the item.
     * Do not assume that the lots are stored in numerical order.
     *
     * Returns true if successful, false otherwise (auction closed,
     * lot does not exist, or lot has a bid).
     * (You need to update the return type, documentation, and code.)
     *
     * @param number The number of the lot to be removed.
     */
    public boolean removeLot(int number)
    {
        Iterator <Lot> it = lots.iterator();
        Lot lot;

        if(lots != null && isOpened == true)
        {
            while(it.hasNext())
            {
                lot = it.next();
                if (lot.getNumber() == number && lot.getHighestBid() == null )
                {
                    it.remove();
                    return true;
                }
            }
        }
        return false;  
    }
}