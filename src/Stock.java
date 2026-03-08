/**
* Stock is a child class of FinancialAsset that represents an equity holding.
* It adds stock specific attributes like ticker symbol, shares held, price per share,
* and dividend yield. The main concept here is inheritance: Stock extends FinancialAsset,
* reusing the parent's name, value, and risk attrivutes while adding its own. Both the methods
* calculateReturn() and displayInfo() are overridden to provide stock specific behavior.
* This enables polymorphism when these objects are stored in an ArrayList<FinancialAsset>
* and processed through the parent type.
*/
public class Stock extends FinancialAsset{
  // The stock's ticker symbol (e.g., "MSFT" for Microsoft)
  private String tickerSymbol;
  // The number of shares the user owns of this stock
  private int sharesHeld;
  // The current market price of one share in dollars
  private double pricePerShare;
  // The annual dividend yield as a decimal (e.g., 0.03 = 3%)
  private double dividendYield;

  /**
  * Constructs a new Stock with both parent and child attributes.
  * The super() call passes name, currentValue, and riskRating up to the
  * FinancialAsset constructor, ensuring the parent portion is initialized first.
  * This directly shows how inheritance in Java works: the parent constructor runs
  * before any child specific initialization happens.
  */
  public Stock(String name, double currentValue, int riskRating,
               String tickerSymbol, int sharesHeld, double pricePerShare,
               double dividendYield){
    super(name, currentValue, riskRating);
    this.tickerSymbol = tickerSymbol;
    this.sharesHeld = sharesHeld;
    this.pricePerShare = pricePerShare;
    this.dividendYield = dividendYield;
  }

  // Getters and Setters
  // Ensures encapsulation: all attributes are private, only accessed through these methods.

  public String getTickerSymbol(){
    return tickerSymbol;
  }

  public void setTickerSymbol(String tickerSymbol){
    this.tickerSymbol = tickerSymbol;
  }

  public int getSharesHeld(){
    return sharesHeld;
  }

  public void setSharesHeld(int sharesHeld){
    this.sharesHeld = sharesHeld;
  }

  public double getPricePerShare(){
    return pricePerShare;
  }

  public void setPricePerShare(double pricePerShare){
    this.pricePerShare = pricePerShare;
  }

  public double getDividendYield(){
    return dividendYield;
  }

  public void setDividendYield(double dividendYield){
    this.dividendYield = dividendYield;
  }

  /**
  * Calculates the expected annual return for this stock based on dividend income.
  * Formula: sharesHeld * pricePerShare * dividendYield
  * This gives the total annual dividend payment the investor receives.
  * This method overrides the parent's version, which returns 0.0, so that when
  * Java encounters a Stock stored as a FinancialAsset reference, it dispatches to
  * this version at runtime. Thus showing polymorphism.
  */
  @Override
  public double calculateReturn(){
    return sharesHeld * pricePerShare * dividendYield;
  }

  /**
  * Displays all information about this stock, including both inherited and stock
  * specific attributes. Overrides the parent's displayInfo() so that polymorphic
  * calls through an ArrayList<FinancialAsset> print the full stock details
  * rather than just the base asset info.
  */
  @Override
  public void displayInfo(){
    System.out.println("--- Stock ---");
    // Call the parent's displayInfo() to print the shared attributes of name, value, and risk
    super.displayInfo();
    System.out.println("Ticker:        " + tickerSymbol);
    System.out.println("Shares Held:   " + sharesHeld);
    System.out.printf("Price/Share:   $%.2f%n", pricePerShare);
    System.out.printf("Dividend Yield: %.2f%%%n", dividendYield * 100);
    System.out.printf("Expected Return: $%.2f%n", calculateReturn());
    System.out.println();
  }
}
