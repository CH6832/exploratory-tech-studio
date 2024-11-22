# Test Plan

This document outlines the testing strategy for the IFRS Calculator.

---

## Objectives

1. Validate correctness of calculations.
2. Ensure usability of the CLI.
3. Test performance under varying input sizes.

---

## Test Cases

| Test Case ID | Module                    | Description                       | Expected Result              |
|--------------|---------------------------|-----------------------------------|------------------------------|
| TC01         | Fair Value Module         | Validate bond fair value          | Accurate present value       |
| TC02         | Revenue Allocation Module | Split revenue based on inputs     | Correct allocation output    |
| TC03         | Lease Liability Module    | Compute lease liabilities         | Accurate lease values        |

---

## Tools

- **Unit Testing Framework**: NUnit
- **Mocking Library**: Moq

---

## Automation

Automated tests will be triggered on every commit using a CI/CD pipeline.
