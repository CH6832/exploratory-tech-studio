# IFRS Calculator

The IFRS Calculator is a command-line tool designed to facilitate various financial calculations required under International Financial Reporting Standards (IFRS). The tool supports calculations such as fair value of bonds, revenue allocation, lease liabilities, defined benefit obligations, deferred taxes, goodwill computation, and recoverable amounts for impairment testing.

---

## Features

- **Fair Value Calculation**: Uses discounted cash flow methods to calculate bond fair values.
- **Revenue Allocation**: Allocates revenue based on standalone selling prices and transaction prices.
- **Lease Liability**: Computes the present value of lease payments.
- **Defined Benefit Obligation**: Calculates the present value of future benefit payments.
- **Deferred Tax**: Determines deferred tax assets or liabilities.
- **Goodwill**: Calculates goodwill based on financial data.
- **Recoverable Amount**: Calculates recoverable amounts for impairment purposes.

---

## Requirements

### Functional Requirements
1. Perform various IFRS-based calculations.
2. Provide accurate and efficient computation results.

### Non-Functional Requirements
1. Low latency and performance optimization.
2. User-friendly interface.
3. Error handling and logging capabilities.
4. Automated test coverage.

### Constraints
- **Language**: C#.
- **Build Tools**: Maven or Gradle for project management.
- **IDE Compatibility**: Microsoft Visual Studio Community Edition.

---

## Getting Started

### Constraints
- **Language**: C#.
- **Build Tools**: .NET Core SDK (for building and running the application).
- **IDE Compatibility**: Microsoft Visual Studio (or any IDE supporting C# and .NET Core).

### Installation
1. Navigate to the project directory:
   ```bash
   cd IFRS-Calculator
   ```

2. Build the project:
   ```bash
   dotnet build
   ```

3. Run the application:
   ```bash
   dotnet run
   ```

---

## Example Usage

```csharp
==================================================
Welcome to the IFRS Calculator
Effortlessly perform IFRS calculations!
==================================================


Main Menu:
1. Calculate Fair Value of Bonds
2. Allocate Revenue
3. Compute Lease Liability
4. Calculate Defined Benefit Obligation
5. Determine Deferred Tax
6. Compute Goodwill
7. Calculate Recoverable Amount
0. Exit
Enter your choice: 1

=== Fair Value Calculation ===
Enter cash flow amount: 3000

Enter discount rate (%): 20

Enter number of periods: 4

Fair Value of the bond: $7,766.20

```

**Explanation:**
- **Fair Value of Bonds (IFRS 9)**: This calculation is based on discounted cash flow (DCF) methodology, where the future cash flows (i.e., bond payments) are discounted at a given discount rate (here, 20%) over a certain number of periods (4 years). The formula used here is:

  $\text{Fair Value} = \sum_{t=1}^{n} \frac{CF_t}{(1 + r)^t}$

  Where:
  - $( CF_t )$ is the cash flow in period $( t )$
  - $( r )$ is the discount rate
  - $( n )$ is the number of periods

  This calculation is essential for the fair value reporting of financial instruments under **IFRS 9**, which requires the measurement of financial assets and liabilities at fair value through profit or loss or other comprehensive income, depending on the classification.

```csharp
Main Menu:
1. Calculate Fair Value of Bonds
2. Allocate Revenue
3. Compute Lease Liability
4. Calculate Defined Benefit Obligation
5. Determine Deferred Tax
6. Compute Goodwill
7. Calculate Recoverable Amount
0. Exit
Enter your choice: 2


=== Revenue Allocation ===
Enter total transaction price: 350

Enter number of performance obligations: 4

Enter standalone selling price for obligation 1: 23

Enter standalone selling price for obligation 2: 300

Enter standalone selling price for obligation 3: 200

Enter standalone selling price for obligation 4: 123

Revenue Allocation:
 - Obligation 1: $12.46
 - Obligation 2: $162.54
 - Obligation 3: $108.36
 - Obligation 4: $66.64

```

**Explanation:**
- **Revenue Allocation (IFRS 15)**: This feature allocates the total transaction price to different performance obligations based on their standalone selling prices. Under **IFRS 15**, revenue is recognized when control of goods or services is transferred to the customer. To determine the revenue for each performance obligation, we allocate the total transaction price in proportion to the relative standalone selling prices of each obligation.

  In this example, the total transaction price of $350 is allocated across four obligations based on their standalone selling prices. The proportionate share for each obligation is calculated, resulting in the revenue allocated to each.

```csharp
Main Menu:
1. Calculate Fair Value of Bonds
2. Allocate Revenue
3. Compute Lease Liability
4. Calculate Defined Benefit Obligation
5. Determine Deferred Tax
6. Compute Goodwill
7. Calculate Recoverable Amount
0. Exit
Enter your choice: 3


=== Lease Liability Calculation ===
Enter annual lease payment: 50000

Enter discount rate (%): 5

Enter lease term (years): 4

Lease Liability: $177,297.53
```

**Explanation:**
- **Lease Liability (IFRS 16)**: Under **IFRS 16**, lessees are required to recognize a lease liability on the balance sheet. The lease liability is calculated by discounting the future lease payments at the rate implicit in the lease or, if not readily available, the lessee’s incremental borrowing rate.

  In this case, we are given the annual lease payment, discount rate, and lease term. Using these inputs, we calculate the present value of the lease payments to determine the lease liability.

```csharp
Main Menu:
1. Calculate Fair Value of Bonds
2. Allocate Revenue
3. Compute Lease Liability
4. Calculate Defined Benefit Obligation
5. Determine Deferred Tax
6. Compute Goodwill
7. Calculate Recoverable Amount
0. Exit
Enter your choice: 4


=== Defined Benefit Obligation ===
Enter annual benefit payment: 120000

Enter discount rate (%): 7

Enter benefit duration (years): 4

Defined Benefit Obligation: $406,465.35
```

**Explanation:**
- **Defined Benefit Obligation (IAS 19)**: **IAS 19** outlines how to account for employee benefits. A defined benefit obligation refers to the present value of the future pension benefits that an employer is obligated to pay to employees. The obligation is calculated by discounting the future benefit payments (annually paid) at a discount rate (7% in this case).

  The calculation takes into account the duration of the benefit payments and applies the discount rate to arrive at the present value of these obligations.

```csharp
Main Menu:
1. Calculate Fair Value of Bonds
2. Allocate Revenue
3. Compute Lease Liability
4. Calculate Defined Benefit Obligation
5. Determine Deferred Tax
6. Compute Goodwill
7. Calculate Recoverable Amount
0. Exit
Enter your choice: 5


=== Deferred Tax Calculation ===
Enter carrying amount: 4000


 valid positive double: 4000
Enter tax base: 300

Enter tax rate (%): 15

Deferred Tax: $555.00
```

**Explanation:**
- **Deferred Tax (IAS 12)**: Deferred taxes arise due to temporary differences between the carrying amounts of assets or liabilities and their tax bases. This calculation determines the deferred tax liability (or asset) by applying the tax rate (15% here) to the difference between the carrying amount and the tax base.

  Under **IAS 12**, companies are required to recognize deferred tax liabilities and assets for these temporary differences. In this example, the difference is multiplied by the tax rate to compute the deferred tax liability.

---

## License

This project is licensed under the MIT License. See [LICENSE.md](./LICENSE) for details.
