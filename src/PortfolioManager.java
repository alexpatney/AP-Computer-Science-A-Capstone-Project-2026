import java.util.ArrayList;
import java.util.Scanner;

public class PortfolioManager{
  private static ArrayList<FinancialAsset> portfolioAssets = new ArrayList<FinancialAsset>();
  private static Scanner kb = new Scanner(System.in);

  public static void main(String[] args){
    System.out.println("==========================================");
    System.out.println("   Welcome to PatneyPortfolio Manager");
    System.out.println("==========================================");

    boolean running = true;

    while(running){
      System.out.println("---------- Main Menu ----------");
      System.out.println("1. Add Asset");
      System.out.println("2. View Portfolio");
      System.out.println("3. Sort Portfolio");
      System.out.println("4. Search Asset");
      System.out.println("5. Calculate Portfolio Summary");
      System.out.println("6. Remove Asset");
      System.out.println("7. Exit");

      int choice = getValidInt("Enter your choice", 1, 7);

      switch(choice){
        case 1:
          addAsset();
          break;
        case 2:
          viewPortfolio();
          break;
        case 3:
          sortPortfolio();
          break;
        case 4:
          searchAsset();
        case 5:
          calculateSummary();
          break;
        case 6:
          removeAsset();
          break;
        case 7:
          running = false;
          System.out.println("Thank you for using PatneyPortfolio Manager. Goodbye!");
          break;
      }
      System.out.println();
    }

    kb.close();
  }

  public static void addAsset(){}

  public static void viewPortfolio(){}

  public static void sortPortfolio(){}

  public static void searchAsset(){}

  public static void calculateSummary(){}

  public static void removeAsset(){}

  public static int getValidInt(String prompt, int min, int max){
    int value;

    while(true){
      System.out.print(prompt + " (" + min + "-" + max + "): ");
      String input = kb.nextLine().trim();

      try{
        value = Integer.parseInt(input);

        if(value >= min && value <= max){
          return value;
        } else{
          System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
      } catch(NumberFormatException e){
        System.out.println("Invalid input. Please enter a whole number.");
      }
    }
  }

  public static double getValidDouble(String prompt, double min, double max){
    double value;

    while(true){
      System.out.printf("%s (%.2f-%.2f): ", prompt, min, max);
      String input = kb.nextLine().trim();

      try{
        value = Double.parseDouble(input);

        if(value >= min && value <= max){
          return value;
        } else{
          System.out.printf("Please enter a number between %.2f and %.2f.%n", min, max);
        }
      } catch(NumberFormatException e){
        System.out.println("Invalid input. Please enter a valid number.");
      }
    }
  }
}
