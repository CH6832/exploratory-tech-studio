vbnet


### `class_diagram.md`

```markdown
# Class Diagram

The class diagram represents the structure and relationships of the key classes in the project.

+-----------------------------+
FinancialCalculations
+ calculate() : double
+------------+----------------+

markdown

         |
         v

+-----------------------------+
FairValueCalculation
- coupon : double
- faceValue : double
- discountRate : double
- periods : int
+ calculate() : double
+-----------------------------+

+-----------------------------+
AllocateRevenueCalculation
- sellingPrices : Map
- totalPrice : double
+ calculate() : Map<String, Double>
+-----------------------------+

+-----------------------------+
LeaseLiabilityCalculation
- leasePayments : double[]
- discountRate : double
+ calculate() : double
+-----------------------------+

+-----------------------------+
PresentValueObligationCalculation
- benefitPayments : double[]
- discountRate : double
+ calculate() : double
+-----------------------------+

+-----------------------------+
DeferredTaxCalculation
- carryingAmount : double
- taxBase : double
- taxRate : double
+ calculate() : double
+-----------------------------+

+-----------------------------+
GoodwillCalculation
- purchasePrice : double
- fairValueAssets : double
- fairValueLiabilities : double
+ calculate() : double
+-----------------------------+

+-----------------------------+
RecoverableAmountCalculation
- fairValue : double
- costToSell : double
- valueInUse : double
+ calculate() : double
+-----------------------------+