# Component Diagram

The component diagram illustrates the high-level architecture of the IFRS Calculator application.

+-------------------+
| Financial |
| Calculator |
| Application |
+--------+----------+
|
v
+--------+----------+
| GUI |
| (Swing) |
+--------+----------+
|
v
+--------+----------+
| Calculation |
| Modules |
| - FairValue |
| - Revenue |
| - Lease |
| - PV Obligation |
| - DeferredTax |
| - Goodwill |
| - Recoverable |
+--------+----------+
|
v
+--------+----------+
| Logging |
| & Error Handling|
+--------+----------+
|
v
+--------+----------+
| External Libraries |
| (JUnit, Maven, etc.) |
+----------------------+