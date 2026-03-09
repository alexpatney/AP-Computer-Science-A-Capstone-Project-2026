/**
* Bond is a child class of FinancialAsset that represents a fixed income investment.
* It adds bond specific attributes like face value, coupon rate, and years to maturity.
* Like Stock, Bond extends FinancialAsset and overrides calculateReturn() and
* displayInfo() to provide bond specific behavior. This si the second child class in
* the inheritance hierarchy, showing that multiple subclasses can share the same
* parent while each implementing their own unique logic. When both Stock and Bond
* objects sit in the same ArrayList<FinancialAsset>, Java's polymorphic dispatch
* ensures the correct version of each overridden method is called.
*/
public class Bond extends FinancialAsset{
  // The par value of the bond (the amount paid back at maturity)
  private double faceValue;
  // The annual coupon rate as a decial (0.05 = 5% annual interest)
  private double couponRate;
  // The number of years until the bond matures and the face value is returned
  private int yearsToMaturity;

  /**
  * Constructs a new Bond with both parent and child attributes.
  * The super() call initialies the FinancialAsset portion (name, value, risk),
  * then the Bond specific attributes are set. This constructure chain ensures the
  * full object is properly initialized from parent down to child.
  */
  public Bond(String name, double currentValue, int riskRating,
              double faceValue, double couponRate, int yearsToMaturity){
    super(name, currentValue, riskRating);
    this.faceValue = faceValue;
    this.couponRate = couponRate;
    this.yearsToMaturity = yearsToMaturity;
  }

  // Getters and Setters
  // Ensures encapsulation: all attributes are private, only accessed through these methods.
  
  public double getFaceValue(){
    return faceValue;
  }

  public void setFaceValue(double faceValue){
    this.faceValue = faceValue;
  }

  public double getCouponRate(){
    return couponRate;
  }

  public void setCouponRate(double couponRate){
    this.couponRate = couponRate;
  }

  public int getYearsToMaturity(){
    return yearsToMaturity;
  }

  public void setYearsToMaturity(int yearsToMaturity){
    this.yearsToMaturity = yearsToMaturity;
  }

  /**
  * Calculates the expected annual return for this bond based on coupon payments.
  * Formula: faceValue * couponRate
  * This gives the fixed annual interest payment the bondholder receives.
  * Unlike stocks (which uses dividend yield), bonds pay a fixed coupon based
  * on face value regardless of the current market price. This override replaces
  * the parent's default return of 0.0 with the bond specific logic.
  */
  @Override
  public double calculateReturn(){
    return faceValue * couponRate;
  }

  /**
  * Displays all information about this bond, including both inherited and
  * bond specific attributes. Calls super.displayInfo() first to print the shared
  * parent attributes, then adds bond specific details. This override ensures
  * polymorphic calls through ArrayList<FinancialAsset> print complete bond info.
  */
  @Override
  public void displayInfo(){
    System.out.println("--- Bond ---");
    // Call the parent's displayInfo() to print shared attributes (name, value, risk)
    super.displayInfo();
    System.out.printf("Face Value:      $%.2f%n", faceValue);
    System.out.printf("Coupon Rate:     %.2f%%%n", couponRate * 100);
    System.out.println("Years To Maturity: " + yearsToMaturity);
    System.out.printf("Expected Return: $%.2f%n", calculateReturn());
    System.out.println();
  }
}
