/**
* FinancialAsset is the parent class for all asset types in the portfolio
* It holds the shared attributes that every financial asset has like a name,
* a current market value, and a risk rating. Subclasses like Stock and Bond
* extend this class and override methods like calculateReturn() and displayInfo()
* to provide their own specialized behavior. This design enables polymorphism because
* we can store mixed asset types in one ArrayList<FinancialAsset> and call overridden
* methods without knowing the actual subclass at compile time.
*/
public class FinancialAsset{
  // The actual name of an asset (e.g., "Microsoft Inc." or "US Treasury Bond")
  private String name;
  // The current market value of this asset in dollars
  private double currentValue;
  // A risk rating from 1 (low risk) to 10 (high risk) assigned by the user
  private int riskRating;

  /**
  * Constructs a new FinancialAsset with the given name, value, and risk rating.
  * Every subclass constructor calls super() to initialize these shared attributes
  * which ensures that the parent portion of the object is always set up correctly.
  */  
  public FinancialAsset(String name, double currentValue, int riskRating){
    this.name = name;
    this.currentValue = currentValue;
    this.riskRating = riskRating;
  }

  // Getters and Setters
  // These methods enforce encapsulation because the fact that all attributes are private,
  // thus meaning outside code must go through these methods to read or modify asset data.
  
  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public double getCurrentValue(){
    return currentValue;
  }

  public void setCurrentValue(double currentValue){
    this.currentValue = currentValue;
  }

  public int getRiskRating(){
    return riskRating;
  }

  public void setRiskRating(int riskRating){
    this.riskRating = riskRating;
  }

  /**
  * Calculates the expected annual return for this asset.
  * This base implementation returns 0.0 because a generic FinancialAsset
  * has no specific return formula. The Stock and Bond subclasses will
  * override this method with their own logic (Stock uses dividend income,
  * Bond uses coupon payments). This method exists in the parent class so that
  * polymorphic calls will work. All we do is call calculateReturn() on any
  * FinancialAsset reference and Java will dispatch to the correct child
  * version at runtime.
  */
  public double calculateReturn(){
    return 0.0;
  }

  /**
  * Displays the basic information shared by all financial assets.
  * Subclasses will override this to add their own specific attributes to the output.
  * Like calculateReturn(), this method exists in the parent class to support
  * polymorphic dispatch through an ArrayList<FinancialAsset>.
  */
  public void displayInfo(){
    System.out.println("Asset Name:    " + name);
    System.out.printf("Current Value: $%.2f%n", currentValue);
    System.out.println("Risk Rating:   " + riskRating + "/10");
  }
}
