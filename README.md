# PatneyPortfolio Manager

A terminal-based portfolio management system built in Java for Alex Patney's AP Computer Science A Capstone Project. Users can add stocks and bonds to a portfolio, view all holdings, sort by different criteria, search for specific assets, view a performance summary, and remove assets — all through a validated console interface.

## Table of Contents

- [Concept](#concept)
- [How to Compile and Run](#how-to-compile-and-run)
- [Features](#features)
- [Class Hierarchy](#class-hierarchy)
- [How Inheritance and Polymorphism Work in This Project](#how-inheritance-and-polymorphism-work-in-this-project)
- [Algorithms Used](#algorithms-used)
- [Input Validation](#input-validation)
- [Reflection on Self-Teaching](#reflection-on-self-teaching)
- [Research Log](#research-log)

## Concept

PatneyPortfolio Manager lets a user build and analyze a collection of financial assets. The core idea is that stocks and bonds are both financial assets, but each calculates returns differently. A stock earns dividends; a bond pays coupons. By modeling this with a parent class (`FinancialAsset`) and two child classes (`Stock`, `Bond`), the program can treat all assets uniformly in a single `ArrayList<FinancialAsset>` while still getting the correct specialized behavior from each type at runtime. This is inheritance and polymorphism in action.

## How to Compile and Run

Make sure you have Java (JDK 8 or higher) installed. Then from the project root:

```bash
cd src
javac FinancialAsset.java Stock.java Bond.java PortfolioManager.java
java PortfolioManager
```

The program runs entirely in the terminal. Follow the on-screen menu prompts to interact with it.

## Features

| Menu Option | What It Does |
|---|---|
| **1. Add Asset** | Add a Stock or Bond to the portfolio. Collects all required attributes with full input validation. |
| **2. View Portfolio** | Displays every asset in the portfolio with all its details, using polymorphic `displayInfo()` calls. |
| **3. Sort Portfolio** | Sorts assets using **selection sort**. User picks the criteria: current value (ascending), expected return (descending), or risk rating (ascending). |
| **4. Search Asset** | Uses **linear search** to find an asset by name (case-insensitive). |
| **5. Calculate Portfolio Summary** | Shows total value, total expected return, average risk rating, and a count of stocks vs. bonds. |
| **6. Remove Asset** | Lists all assets, lets the user pick one by number, and confirms before removing. |
| **7. Exit** | Ends the program. |

## Class Hierarchy

```
FinancialAsset (Parent)
├── Stock (Child) — extends FinancialAsset
└── Bond (Child)  — extends FinancialAsset

PortfolioManager (Runner) — manages ArrayList<FinancialAsset>
```

### FinancialAsset (Parent Class)
- **Private attributes:** `name`, `currentValue`, `riskRating`
- **Constructor:** Takes all three attributes
- **Getters/Setters:** Full set for all attributes (encapsulation)
- **`calculateReturn()`:** Returns `0.0` as a base implementation — exists so subclasses can override it and polymorphic calls through the parent type work at compile time
- **`displayInfo()`:** Prints the shared attributes — also exists so subclasses can override it

### Stock (Child Class)
- **Additional private attributes:** `tickerSymbol`, `sharesHeld`, `pricePerShare`, `dividendYield`
- **Constructor:** Calls `super(name, currentValue, riskRating)` to initialize the parent portion, then sets stock-specific attributes
- **`@Override calculateReturn()`:** Returns `sharesHeld * pricePerShare * dividendYield` (annual dividend income)
- **`@Override displayInfo()`:** Calls `super.displayInfo()` for shared attributes, then prints stock-specific details

### Bond (Child Class)
- **Additional private attributes:** `faceValue`, `couponRate`, `yearsToMaturity`
- **Constructor:** Calls `super(name, currentValue, riskRating)` to initialize the parent portion, then sets bond-specific attributes
- **`@Override calculateReturn()`:** Returns `faceValue * couponRate` (annual coupon payment)
- **`@Override displayInfo()`:** Calls `super.displayInfo()` for shared attributes, then prints bond-specific details

### PortfolioManager (Runner Class)
- **`ArrayList<FinancialAsset> portfolioAssets`** — the polymorphic collection
- **`Scanner kb`** — shared Scanner for all input
- **`main()`** — menu loop that delegates to dedicated methods
- **`getValidInt()` / `getValidDouble()`** — input validation helpers reused everywhere

## How Inheritance and Polymorphism Work in This Project

The entire architecture revolves around one key concept: a `Stock` *is a* `FinancialAsset`, and a `Bond` *is a* `FinancialAsset`. Because both child classes extend the same parent, they can both be stored in a single `ArrayList<FinancialAsset>`.

When the program loops through this list and calls `calculateReturn()`, it doesn't need to know whether each element is a Stock or a Bond. Java checks the actual object type at runtime and calls the correct overridden version automatically. A Stock computes dividend income; a Bond computes coupon payments. The calling code is identical for both — that's polymorphism.

The same thing happens with `displayInfo()`. One loop, one method call, but each asset type prints its own specialized output. The parent class defines the method signature so the code compiles, and the child classes provide the real behavior so the code runs correctly.

This pattern means adding a third asset type (like a `MutualFund`) would only require writing the new child class — no changes to the existing loop logic in `PortfolioManager`.

## Algorithms Used

### Selection Sort (`sortPortfolio()`)
The sort uses the **selection sort** algorithm. On each pass through the unsorted portion of the ArrayList, it finds the element that belongs in the current position (e.g., the smallest value) and swaps it there. This runs in O(n²) time. The user chooses the sort criteria:
- **Current Value** — ascending (lowest value first)
- **Expected Return** — descending (highest return first)
- **Risk Rating** — ascending (lowest risk first)

### Linear Search (`searchAsset()`)
The search uses **linear search** (sequential scan). It checks each asset's name one by one, comparing case-insensitively, until a match is found or the end of the list is reached. This is appropriate because the list is not sorted by name and is relatively small.

## Input Validation

Every single `Scanner` input in the program is validated. Two helper methods handle this:

- **`getValidInt(String prompt, int min, int max)`** — catches `NumberFormatException` if the user types letters or symbols, and reprompts if the number is outside the valid range
- **`getValidDouble(String prompt, double min, double max)`** — same logic for decimal inputs

String inputs (asset name, ticker symbol) are validated to ensure they are not empty. The remove confirmation accepts `y`/`yes` and treats anything else as a cancellation. The program never crashes on unexpected input.

## Reflection on Self-Teaching

The biggest concept I had to learn for this project was how polymorphic dispatch actually works under the hood. When I first started, I thought storing a `Stock` in an `ArrayList<FinancialAsset>` would "erase" the Stock-specific behavior — that calling `calculateReturn()` on a `FinancialAsset` reference would just return the parent's `0.0`. My "aha!" moment came when I realized that the declared type (`FinancialAsset`) only determines what methods you *can call* at compile time, but the actual object type (`Stock` or `Bond`) determines *which version runs* at runtime. That single distinction is what makes the entire project work: one ArrayList, one loop, but each asset behaves according to its own class.

Understanding this also helped me see why the parent class needs `calculateReturn()` and `displayInfo()` in the first place, even though its implementations are just defaults. Without those methods in `FinancialAsset`, the compiler wouldn't let me call them through a `FinancialAsset` reference — the code wouldn't even compile. The parent defines the *contract*, and the children fulfill it with real logic.

I also learned practical lessons about input validation. My first version used `kb.nextInt()` directly, which threw `InputMismatchException` the moment someone typed a letter. Switching to `kb.nextLine()` combined with `Integer.parseInt()` inside a try-catch block completely solved the problem and made the program robust against any user input.

## Research Log

1. **Oracle Java Documentation — Inheritance:** [https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html](https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html)
   - Used to understand the `extends` keyword, how `super()` works in constructors, and how `@Override` ensures the method signatures match the parent.

2. **GeeksforGeeks — Inheritance in Java:** [https://www.geeksforgeeks.org/java/inheritance-in-java/](https://www.geeksforgeeks.org/java/inheritance-in-java/)
   - Used for the visual breakdown of inheritance types (single, multilevel, hierarchical). Helped me confirm that my project uses single inheritance (Stock extends FinancialAsset, Bond extends FinancialAsset) and understand how the constructor chain flows from parent to child.
