------------------------------------------------------------------------------------------------------------------------------------------------

Statistica tests - Chi  Squared Test

Chi-Squared Test in R
Here's an example of how you can perform a chi-squared test of independence in R:
# Create a contingency table
gender <- c("Male", "Male", "Female", "Female")
preference <- c("A", "B", "A", "B")
data <- data.frame(gender, preference)

# Create the table
table <- table(data$gender, data$preference)

# Perform the chi-squared test
chi_squared_test <- chisq.test(table)

# Print the results
chi_squared_test

Here's an example of how you can perform a chi-squared test of independence in Python:
import pandas as pd
from scipy.stats import chi2_contingency

# Create a DataFrame
data = pd.DataFrame({
    'gender': ['Male', 'Male', 'Female', 'Female'],
    'preference': ['A', 'B', 'A', 'B']
})

# Create a contingency table
contingency_table = pd.crosstab(data['gender'], data['preference'])

# Perform the chi-squared test
chi2_stat, p_val, dof, ex = chi2_contingency(contingency_table)

# Print the results
print(f"Chi-Squared Statistic: {chi2_stat}")
print(f"Degrees of Freedom: {dof}")
print(f"P-Value: {p_val}")

Exercises

Exercise 1 - Comparing Proportions of Hits
In a previous exercise, we determined whether or not each poll predicted the correct winner for their state in the 2016 U.S. presidential election. Each poll was also assigned a grade by the poll aggregator. Now we're going to determine if polls rated A- made better predictions than polls rated C-.
In this exercise, filter the errors data for just polls with grades A- and C-. Calculate the proportion of times each grade of poll predicted the correct winner.
Filter errors for grades A- and C-.
Group the data by grade and hit.
Summarize the number of hits for each grade.
Generate a two-by-two table containing the number of hits and misses for each grade. Try using the spread function to generate this table.
Calculate the proportion of times each grade was correct.

# The 'errors' data have already been loaded. Examine them using the `head` function.
head(errors)

# Generate an object called 'totals' that contains the numbers of good and bad predictions for polls rated A- and C-
totals <- table(errors$poll_rating, errors$prediction)

# Print the proportion of hits for grade A- polls to the console
total_A_minus <- sum(totals['A-', ])
good_A_minus <- totals['A-', 'good']
proportion_hits_A_minus <- good_A_minus / total_A_minus
proportion_hits_A_minus

# Print the proportion of hits for grade C- polls to the console
total_C_minus <- sum(totals['C-', ])
good_C_minus <- totals['C-', 'good']
proportion_hits_C_minus <- good_C_minus / total_C_minus
proportion_hits_C_minus

Exercise 2 - Chi-squared Test
We found that the A- polls predicted the correct winner about 80% of the time in their states and C- polls predicted the correct winner about 86% of the time.
Use a chi-squared test to determine if these proportions are different.
Use the chisq.test function to perform the chi-squared test. Save the results to an object called chisq_test.
Print the p-value of the test to the console.

# The 'totals' data have already been loaded. Examine them using the `head` function.
head(totals)

# Create a contingency table
contingency_table <- matrix(c(80, 20, 86, 14), nrow = 2, byrow = TRUE)
colnames(contingency_table) <- c("Correct", "Incorrect")
rownames(contingency_table) <- c("A- Polls", "C- Polls")

# Perform a chi-squared test on the hit data.
# Save the results as an object called 'chisq_test'.
chisq_test <- chisq.test(contingency_table)

# Print the p-value of the chi-squared test to the console
chisq_test$p.value

Exercise 3 - Odds Ratio Calculation
It doesn't look like the grade A- polls performed significantly differently than the grade C- polls in their states.
Calculate the odds ratio to determine the magnitude of the difference in performance between these two grades of polls.
Calculate the odds that a grade C- poll predicts the correct winner. Save this result to a variable called odds_C.
Calculate the odds that a grade A- poll predicts the correct winner. Save this result to a variable called odds_A.
Calculate the odds ratio that tells us how many times larger the odds of a grade A- poll is at predicting the winner than a grade C- pol

# The 'totals' data have already been loaded. Examine them using the `head` function.
head(totals)
library(dplyr)

# Create the 'totals' data frame
totals <- data.frame(
  grade = c("A-", "C-"),
  correct = c(85, 75),
  total = c(100, 100)
)

# It doesn't look like the grade A- polls performed significantly differently than the grade C- polls in their states.
# Calculate the odds ratio to determine the magnitude of the difference in performance between these two grades of polls.
# Calculate the odds that a grade C- poll predicts the correct winner. Save this result to a variable called odds_C.
# Calculate the odds that a grade A- poll predicts the correct winner. Save this result to a variable called odds_A.
# Calculate the odds ratio that tells us how many times larger the odds of a grade A- poll is at predicting the winner than a grade C- pol

# The 'totals' data have already been loaded. Examine them using the `head` function.
head(totals)

# Generate a variable called `odds_C` that contains the odds of getting the prediction right for grade C- polls
correct_C <- totals[totals$grade == "C-", "correct"]
total_C <- totals[totals$grade == "C-", "total"]
odds_C <- correct_C / (total_C - correct_C)

# Generate a variable called `odds_A` that contains the odds of getting the prediction right for grade A- polls
correct_A <- totals[totals$grade == "A-", "correct"]
total_A <- totals[totals$grade == "A-", "total"]
odds_A <- correct_A / (total_A - correct_A)

# Calculate the odds ratio to determine how many times larger the odds ratio is for grade A- polls than grade C- polls
odds_ratio <- odds_A / odds_C
odds_C
odds_A
odds_ratio
