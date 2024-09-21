
-----------------------------------------------------------------------------------------------------------------------------------------------

Monte Carlo

16.8.4 Predicting the electoral college
Up to now we have focused on the popular vote. But in the United States, elections are not decided by the popular vote but rather by what is known as the electoral college. Each state gets a number of electoral votes that depends, in a somewhat complex way, on the population size of the state. Here are the top 5 states ranked by electoral votes in 2016.
results_us_election_2016 |> top_n(5, electoral_votes)
#>          state electoral_votes clinton trump others
#> 1   California              55    61.7  31.6    6.7
#> 2        Texas              38    43.2  52.2    4.5
#> 3      Florida              29    47.8  49.0    3.2
#> 4     New York              29    59.0  36.5    4.5
#> 5     Illinois              20    55.8  38.8    5.4
#> 6 Pennsylvania              20    47.9  48.6    3.6
With some minor exceptions we don’t discuss, the electoral votes are won all or nothing. For example, if you win California by just 1 vote, you still get all 55 of its electoral votes. This means that by winning a few big states by a large margin, but losing many small states by small margins, you can win the popular vote and yet lose the electoral college. This happened in 1876, 1888, 2000, and 2016. The idea behind this is to avoid a few large states having the power to dominate the presidential election. Nonetheless, many people in the US consider the electoral college unfair and would like to see it abolished.
We are now ready to predict the electoral college result for 2016. We start by aggregating results from a poll taken during the last week before the election. We use the grepl, which finds strings in character vectors, to remove polls that are not for entire states.
results <- polls_us_election_2016 |>
  filter(state!="U.S." & 
           !grepl("CD", state) & 
           enddate >="2016-10-31" & 
           (grade %in% c("A+","A","A-","B+") | is.na(grade))) |>
  mutate(spread = rawpoll_clinton/100 - rawpoll_trump/100) |>
  group_by(state) |>
  summarize(avg = mean(spread), sd = sd(spread), n = n()) |>
  mutate(state = as.character(state))
Here are the five closest races according to the polls:
results |> arrange(abs(avg))
#> # A tibble: 47 × 4
#>   state               avg     sd     n
#>   <chr>             <dbl>  <dbl> <int>
#> 1 Florida         0.00356 0.0163     7
#> 2 North Carolina -0.0073  0.0306     9
#> 3 Ohio           -0.0104  0.0252     6
#> 4 Nevada          0.0169  0.0441     7
#> 5 Iowa           -0.0197  0.0437     3
#> # ℹ 42 more rows
We now introduce the command left_join that will let us easily add the number of electoral votes for each state from the dataset us_electoral_votes_2016. We will describe this function in detail in the Wrangling chapter. Here, we simply say that the function combines the two datasets so that the information from the second argument is added to the information in the first:
results <- left_join(results, results_us_election_2016, by = "state")
Notice that some states have no polls because the winner is pretty much known:
results_us_election_2016 |> filter(!state %in% results$state) |> 
  pull(state)
#> [1] "Rhode Island"         "Alaska"               "Wyoming"             
#> [4] "District of Columbia"
No polls were conducted in DC, Rhode Island, Alaska, and Wyoming because Democrats are sure to win in the first two and Republicans in the last two.
Because we can’t estimate the standard deviation for states with just one poll, we will estimate it as the median of the standard deviations estimated for states with more than one poll:
results <- results |>
  mutate(sd = ifelse(is.na(sd), median(results$sd, na.rm = TRUE), sd))
To make probabilistic arguments, we will use a Monte Carlo simulation. For each state, we apply the Bayesian approach to generate an election day d
. We could construct the priors for each state based on recent history. However, to keep it simple, we assign a prior to each state that assumes we know nothing about what will happen. Since from election year to election year the results from a specific state don’t change that much, we will assign a standard deviation of 2% or τ=0.02
. For now, we will assume, incorrectly, that the poll results from each state are independent. The code for the Bayesian calculation under these assumptions looks like this:
mu <- 0
tau <- 0.02
results |> mutate(sigma = sd/sqrt(n), 
                   B = sigma^2 / (sigma^2 + tau^2),
                   posterior_mean = B * mu + (1 - B) * avg,
                   posterior_se = sqrt(1/ (1/sigma^2 + 1/tau^2)))
#> # A tibble: 47 × 12
#>   state          avg       sd     n electoral_votes clinton trump others
#>   <chr>        <dbl>    <dbl> <int>           <int>   <dbl> <dbl>  <dbl>
#> 1 Alabama    -0.149  0.0253       3               9    34.4  62.1    3.6
#> 2 Arizona    -0.0326 0.0270       9              11    45.1  48.7    6.2
#> 3 Arkansas   -0.151  0.000990     2               6    33.7  60.6    5.8
#> 4 California  0.260  0.0387       5              55    61.7  31.6    6.7
#> 5 Colorado    0.0452 0.0295       7               9    48.2  43.3    8.6
#> # ℹ 42 more rows
#> # ℹ 4 more variables: sigma <dbl>, B <dbl>, posterior_mean <dbl>,
#> #   posterior_se <dbl>
The estimates based on posterior do move the estimates towards 0, although the states with many polls are influenced less. This is expected as the more poll data we collect, the more we trust those results:

Now we repeat this 10,000 times and generate an outcome from the posterior. In each iteration, we keep track of the total number of electoral votes for Clinton. Remember that Trump gets 270 minus the votes for Clinton. Also note that the reason we add 7 in the code is to account for Rhode Island and D.C.:
B <- 10000
mu <- 0
tau <- 0.02
clinton_EV <- replicate(B, {
  results |> mutate(sigma = sd/sqrt(n), 
                   B = sigma^2 / (sigma^2 + tau^2),
                   posterior_mean = B * mu + (1 - B) * avg,
                   posterior_se = sqrt(1 / (1/sigma^2 + 1/tau^2)),
                   result = rnorm(length(posterior_mean), 
                                  posterior_mean, posterior_se),
                   clinton = ifelse(result > 0, electoral_votes, 0)) |> 
    summarize(clinton = sum(clinton)) |> 
    pull(clinton) + 7
})

mean(clinton_EV > 269)
#> [1] 0.998
This model gives Clinton over 99% chance of winning. A similar prediction was made by the Princeton Election Consortium. We now know it was quite off. What happened?
The model above ignores the general bias and assumes the results from different states are independent. After the election, we realized that the general bias in 2016 was not that big: it was between 1 and 2%. But because the election was close in several big states and these states had a large number of polls, pollsters that ignored the general bias greatly underestimated the standard error. Using the notation we introduce, they assumed the standard error was √σ2/N
which with large N is quite smaller than the more accurate estimate √σ2/N+σ2b. FiveThirtyEight, which models the general bias in a rather sophisticated way, reported a closer result. We can simulate the results now with a bias term. For the state level, the general bias can be larger so we set it at σb=0.03
:
tau <- 0.02
bias_sd <- 0.03
clinton_EV_2 <- replicate(1000, {
  results |> mutate(sigma = sqrt(sd^2/n  + bias_sd^2),  
                   B = sigma^2 / (sigma^2 + tau^2),
                   posterior_mean = B*mu + (1-B)*avg,
                   posterior_se = sqrt( 1/ (1/sigma^2 + 1/tau^2)),
                   result = rnorm(length(posterior_mean), 
                                  posterior_mean, posterior_se),
                   clinton = ifelse(result>0, electoral_votes, 0)) |> 
    summarize(clinton = sum(clinton) + 7) |> 
    pull(clinton)
})
mean(clinton_EV_2 > 269)
#> [1] 0.848
This gives us a much more sensible estimate. Looking at the outcomes of the simulation, we see how the bias term adds variability to the final results.

FiveThirtyEight includes many other features we do not include here. One is that they model variability with distributions that have high probabilities for extreme events compared to the normal. One way we could do this is by changing the distribution used in the simulation from a normal distribution to a t-distribution. FiveThirtyEight predicted a probability of 71%.

Key points


Code: Top 5 states ranked by electoral votes
The results_us_election_2016 object is defined in the dslabs package:
library(tidyverse)
library(dslabs)
data("polls_us_election_2016")
head(results_us_election_2016)

results_us_election_2016 %>% arrange(desc(electoral_votes)) %>% top_n(5, electoral_votes)
Code: Computing the average and standard deviation for each state
results <- polls_us_election_2016 %>%
    filter(state != "U.S." &
            !grepl("CD", state) &
            enddate >= "2016-10-31" &
            (grade %in% c("A+", "A", "A-", "B+") | is.na(grade))) %>%
    mutate(spread = rawpoll_clinton/100 - rawpoll_trump/100) %>%
    group_by(state) %>%
    summarize(avg = mean(spread), sd = sd(spread), n = n()) %>%
    mutate(state = as.character(state))

# 10 closest races = battleground states
results %>% arrange(abs(avg))

# joining electoral college votes and results
results <- left_join(results, results_us_election_2016, by="state")

# states with no polls: note Rhode Island and District of Columbia = Democrat
results_us_election_2016 %>% filter(!state %in% results$state)

# assigns sd to states with just one poll as median of other sd values
results <- results %>%
    mutate(sd = ifelse(is.na(sd), median(results$sd, na.rm = TRUE), sd))
Code: Calculating the posterior mean and posterior standard error
Note there is a small error in the video code: B should be defined as sigma^2/(sigma^2 + tau^2).
mu <- 0
tau <- 0.02
results %>% mutate(sigma = sd/sqrt(n),
                   B = sigma^2/ (sigma^2 + tau^2),
                   posterior_mean = B*mu + (1-B)*avg,
                   posterior_se = sqrt( 1 / (1/sigma^2 + 1/tau^2))) %>%
    arrange(abs(posterior_mean))
Code: Monte Carlo simulation of Election Night results (no general bias)
mu <- 0
tau <- 0.02
clinton_EV <- replicate(1000, {
    results %>% mutate(sigma = sd/sqrt(n),
                       B = sigma^2/ (sigma^2 + tau^2),
                       posterior_mean = B*mu + (1-B)*avg,
                       posterior_se = sqrt( 1 / (1/sigma^2 + 1/tau^2)),
                       simulated_result = rnorm(length(posterior_mean), posterior_mean, posterior_se),
                       clinton = ifelse(simulated_result > 0, electoral_votes, 0)) %>%    # award votes if Clinton wins state
        summarize(clinton = sum(clinton)) %>%    # total votes for Clinton
        .$clinton + 7    # 7 votes for Rhode Island and DC
})
mean(clinton_EV > 269)    # over 269 votes wins election

# histogram of outcomes
data.frame(clintonEV) %>%
    ggplot(aes(clintonEV)) +
    geom_histogram(binwidth = 1) +
    geom_vline(xintercept = 269)
Code: Monte Carlo simulation including general bias
mu <- 0
tau <- 0.02
bias_sd <- 0.03
clinton_EV_2 <- replicate(1000, {
    results %>% mutate(sigma = sqrt(sd^2/(n) + bias_sd^2),    # added bias_sd term
                        B = sigma^2/ (sigma^2 + tau^2),
                        posterior_mean = B*mu + (1-B)*avg,
                        posterior_se = sqrt( 1 / (1/sigma^2 + 1/tau^2)),
                        simulated_result = rnorm(length(posterior_mean), posterior_mean, posterior_se),
                        clinton = ifelse(simulated_result > 0, electoral_votes, 0)) %>%    # award votes if Clinton wins state
        summarize(clinton = sum(clinton)) %>%    # total votes for Clinton
        .$clinton + 7    # 7 votes for Rhode Island and DC
})
mean(clinton_EV_2 > 269)    # over 269 votes wins election

Forecasting
Textbook link
This video corresponds to the textbook section on forecasting External link.
Key points


Code: Variability across one pollster
# select all national polls by one pollster
one_pollster <- polls_us_election_2016 %>%
    filter(pollster == "Ipsos" & state == "U.S.") %>%
    mutate(spread = rawpoll_clinton/100 - rawpoll_trump/100)

# the observed standard error is higher than theory predicts
se <- one_pollster %>%
    summarize(empirical = sd(spread),
            theoretical = 2*sqrt(mean(spread)*(1-mean(spread))/min(samplesize)))
se

# the distribution of the data is not normal
one_pollster %>% ggplot(aes(spread)) +
    geom_histogram(binwidth = 0.01, color = "black")
Code: Trend across time for several pollsters
polls_us_election_2016 %>%
    filter(state == "U.S." & enddate >= "2016-07-01") %>%
    group_by(pollster) %>%
    filter(n() >= 10) %>%
    ungroup() %>%
    mutate(spread = rawpoll_clinton/100 - rawpoll_trump/100) %>%
    ggplot(aes(enddate, spread)) +
    geom_smooth(method = "loess", span = 0.1) +
    geom_point(aes(color = pollster), show.legend = FALSE, alpha = 0.6)
Code: Plotting raw percentages across time
polls_us_election_2016 %>%
    filter(state == "U.S." & enddate >= "2016-07-01") %>%
    select(enddate, pollster, rawpoll_clinton, rawpoll_trump) %>%
    rename(Clinton = rawpoll_clinton, Trump = rawpoll_trump) %>%
    gather(candidate, percentage, -enddate, -pollster) %>%
    mutate(candidate = factor(candidate, levels = c("Trump", "Clinton"))) %>%
    group_by(pollster) %>%
    filter(n() >= 10) %>%
    ungroup() %>%
    ggplot(aes(enddate, percentage, color = candidate)) +
    geom_point(show.legend = FALSE, alpha = 0.4) +
    geom_smooth(method = "loess", span = 0.15) +
    scale_y_continuous(limits = c(30, 50))
Assessment 6.1: Election Forecasting
Exercise 1 - Confidence Intervals of Polling Data
For each poll in the polling data set, use the CLT to create a 95% confidence interval for the spread. Create a new table called cis that contains columns for the lower and upper limits of the confidence intervals.
Instructions
Use pipes %>% to pass the poll object on to the mutate function, which creates new variables. 
Create a variable called X_hat that contains the estimate of the proportion of Clinton voters for each poll.
Create a variable called se that contains the standard error of the spread.
Calculate the confidence intervals using the qnorm function and your calculated se.
Use the select function to keep the following columns: state, startdate, enddate, pollster, grade, spread, lower, upper.

# Load the libraries and data
library(dplyr)
library(dslabs)
data("polls_us_election_2016")

# Create a table called `polls` that filters by  state, date, and reports the spread
polls <- polls_us_election_2016 %>% 
  filter(state != "U.S." & enddate >= "2016-10-31") %>% 
  mutate(spread = rawpoll_clinton/100 - rawpoll_trump/100)

# Create an object called `cis` that has the columns indicated in the instructions
cis <- polls %>%
  group_by(state) %>%
  summarize(avg_spread = mean(spread, na.rm = TRUE),
            sd_spread = sd(spread, na.rm = TRUE),
            n = n(),
            ci_lower = avg_spread - qnorm(0.975) * sd_spread / sqrt(n),
            ci_upper = avg_spread + qnorm(0.975) * sd_spread / sqrt(n)) %>%
  select(state, avg_spread, ci_lower, ci_upper, n)

cis


Exercise 2 - Compare to Actual Results
You can add the final result to the cis table you just created using the left_join function as shown in the sample code.
Now determine how often the 95% confidence interval includes the actual result.
Instructions
Create an object called p_hits that contains the proportion of intervals that contain the actual spread using the following two steps.
Use the mutate function to create a new variable called hit that contains a logical vector for whether the actual_spread falls between the lower and upper confidence intervals.
Summarize the proportion of values in hit that are true using the mean function inside of summarize.

library(dplyr)

# Add the actual results to the `cis` data set
add <- results_us_election_2016 %>% mutate(actual_spread = clinton/100 - trump/100) %>% select(state, actual_spread)
ci_data <- cis %>% mutate(state = as.character(state)) %>% left_join(add, by = "state")

# Create an object called `p_hits` that summarizes the proportion of confidence intervals that contain the actual value. Print this object to the console.
p_hits <- ci_data %>%
  mutate(hit = actual_spread >= lower & actual_spread <= upper) %>%
  summarize(proportion_hits = mean(hit, na.rm = TRUE))

p_hits

Exercise 3 - Stratify by Pollster and Grade
Now find the proportion of hits for each pollster. Show only pollsters with at least 5 polls and order them from best to worst. Show the number of polls conducted by each pollster and the FiveThirtyEight grade of each pollster.
Instructions
Create an object called p_hits that contains the proportion of intervals that contain the actual spread using the following steps.
Use the mutate function to create a new variable called hit that contains a logical vector for whether the actual_spread falls between the lower and upper confidence intervals.
Use the group_by function to group the data by pollster.
Use the filter function to filter for pollsters that have at least 5 polls.
Summarize the proportion of values in hit that are true as a variable called proportion_hits. Also create new variables for the number of polls by each pollster (n) using the n() function and the grade of each poll (grade) by taking the first row of the grade column.
Use the arrange function to arrange the proportion_hits in descending order.

# The `cis` data have already been loaded for you
add <- results_us_election_2016 %>% mutate(actual_spread = clinton/100 - trump/100) %>% select(state, actual_spread)
ci_data <- cis %>% mutate(state = as.character(state)) %>% left_join(add, by = "state")

# Create an object called `p_hits` that summarizes the proportion of hits for each pollster that has at least 5 polls.

Exercise 4 - Stratify by State
Repeat the previous exercise, but instead of pollster, stratify by state. Here we can't show grades.
Instructions
Create an object called p_hits that contains the proportion of intervals that contain the actual spread using the following steps.
Use the mutate function to create a new variable called hit that contains a logical vector for whether the actual_spread falls between the lower and upper confidence intervals.
Use the group_by function to group the data by state.
Use the filter function to filter for states that have more than 5 polls.
Summarize the proportion of values in hit that are true as a variable called proportion_hits. Also create new variables for the number of polls in each state using the n() function.
Use the arrange function to arrange the proportion_hits in descending order.

# The `cis` data have already been loaded for you
add <- results_us_election_2016 %>% mutate(actual_spread = clinton/100 - trump/100) %>% select(state, actual_spread)
ci_data <- cis %>% mutate(state = as.character(state)) %>% left_join(add, by = "state")

# Create an object called `p_hits` that summarizes the proportion of hits for each state that has more than 5 polls.

Exercise 5- Plotting Prediction Results
Make a barplot based on the result from the previous exercise.
Instructions
Reorder the states in order of the proportion of hits.
Using ggplot, set the aesthetic with state as the x-variable and proportion of hits as the y-variable.
Use geom_bar to indicate that we want to plot a barplot. Specifcy stat = "identity" to indicate that the height of the bar should match the value.
Use coord_flip to flip the axes so the states are displayed from top to bottom and proportions are displayed from left to right.

# The `p_hits` data have already been loaded for you. Use the `head` function to examine it.
head(p_hits)

# Make a barplot of the proportion of hits for each state
p_hits <- data.frame(
  State = c("New York", "California", "Texas", "Florida"),
  Proportion = c(0.25, 0.30, 0.20, 0.25)
)

head(p_hits)

ggplot(data = p_hits, aes(x = State, y = Proportion)) +
  geom_bar(stat = "identity", fill = "blue") +
  labs(
    title = "Proportion of Hits by State",
    x = "State",
    y = "Proportion"
  ) +
  coord_flip() +  # Flip the axes
  ylim(0, 0.4) +  # Adjust ylim if needed
  theme_minimal()

Exercise 6 - Predicting the Winner
Even if a forecaster's confidence interval is incorrect, the overall predictions will do better if they correctly called the right winner. 
Add two columns to the cis table by computing, for each poll, the difference between the predicted spread and the actual spread, and define a column hit that is true if the signs are the same.
Instructions
Use the mutate function to add two new variables to the cis object: error and hit.
For the error variable, subtract the actual spread from the spread.
For the hit variable, return "TRUE" if the poll predicted the actual winner. Use the sign function to check if their signs match - learn more with ?sign.
Save the new table as an object called errors.
Use the tail function to examine the last 6 rows of errors.

# The `cis` data have already been loaded. Examine it using the `head` function.
head(cis)

# Create an object called `errors` that calculates the difference between the predicted and actual spread and indicates if the correct winner was predicted
errors <- cis %>% mutate(error = spread - actual_spread, hit = sign(spread) == sign(actual_spread))

# Examine the last 6 rows of `errors`
tail(errors)

Exercise 7 - Plotting Prediction Results
Create an object called p_hits that contains the proportion of instances when the sign of the actual spread matches the predicted spread for states with 5 or more polls.
Make a barplot based on the result from the previous exercise that shows the proportion of times the sign of the spread matched the actual result for the data in p_hits.
Instructions
Use the group_by function to group the data by state.
Use the filter function to filter for states that have 5 or more polls.
Summarize the proportion of values in hit that are true as a variable called proportion_hits. Also create a variable called n for the number of polls in each state using the n() function.
To make the plot, follow these steps:
Reorder the states in order of the proportion of hits.
Using ggplot, set the aesthetic with state as the x-variable and proportion of hits as the y-variable.
Use geom_bar to indicate that we want to plot a barplot.
Use coord_flip to flip the axes so the states are displayed from top to bottom and proportions are displayed from left to right.

# Create an object called `errors` that calculates the difference between the predicted and actual spread and indicates if the correct winner was predicted
errors <- cis %>% mutate(error = spread - actual_spread, hit = sign(spread) == sign(actual_spread))

# Create an object called `p_hits` that summarizes the proportion of hits for each state that has 5 or more polls

filtered_cis <- cis %>%
  group_by(state) %>%
  filter(n() >= 5)

p_hits <- filtered_cis %>%
  mutate(hit = spread * actual_spread > 0) %>%  # Check if sign(spread) == sign(actual_spread)
  group_by(state) %>%
  summarise(prop_hits = mean(hit, na.rm = TRUE))  # Calculate mean of hits

# Make a barplot of the proportion of hits for each state
ggplot(p_hits, aes(x = state, y = prop_hits)) +
  geom_bar(stat = "identity", fill = "skyblue") +
  labs(x = "State", y = "Proportion of Hits", title = "Proportion of Hits for States with 5 or More Polls") +
  theme(axis.text.x = element_text(angle = 45, hjust = 1))

Exercise 8 - Plotting the Errors
In the previous graph, we see that most states' polls predicted the correct winner 100% of the time. Only a few states polls' were incorrect more than 25% of the time. Wisconsin got every single poll wrong. In Pennsylvania and Michigan, more than 90% of the polls had the signs wrong. 
Make a histogram of the errors. What is the median of these errors?
Instructions
Use the hist function to generate a histogram of the errors
Use the median function to compute the median error

# The `errors` data have already been loaded. Examine them using the `head` function.
head(errors)

# Generate a histogram of the error
hist(errors$error, main="Histogram of Errors", xlab="Error", col="blue", border="black")

# Calculate the median of the errors. Print this value to the console.
median(errors$error)


Exercise 9- Plot Bias by State
We see that, at the state level, the median error was slightly in favor of Clinton. The distribution is not centered at 0, but at 0.037. This value represents the general bias we described in an earlier section. 
Create a boxplot to examine if the bias was general to all states or if it affected some states differently. Filter the data to include only pollsters with grades B+ or higher.
Instructions
Use the filter function to filter the data for polls with grades equal to A+, A, A-, or B+.
Use the reorder function to order the state data by error.
Using ggplot, set the aesthetic with state as the x-variable and error as the y-variable.
Use geom_boxplot to indicate that we want to plot a boxplot.
Use geom_point to add data points as a layer.

# The `errors` data have already been loaded. Examine them using the `head` function.
head(errors)

# Create a boxplot showing the errors by state for polls with grades B+ or higher

filtered_errors <- subset(errors, grade %in% c("A+", "A", "A-", "B+", "B", "B-"))

library(ggplot2)

ggplot(filtered_errors, aes(x = state, y = error)) +
  geom_boxplot() +
  geom_point(position = position_jitter(width = 0.2), color = "blue") +
  labs(title = "Errors by State for Polls with Grades B+ or Higher",
       x = "State",
       y = "Error") +
  theme(axis.text.x = element_text(angle = 45, hjust = 1))


Exercise 10 - Filter Error Plot
Some of these states only have a few polls. Repeat the previous exercise to plot the errors for each state, but only include states with five good polls or more.
Instructions
Use the filter function to filter the data for polls with grades equal to A+, A, A-, or B+.
Group the filtered data by state using group_by.
Use the filter function to filter the data for states with at least 5 polls. Then, use ungroup so that polls are no longer grouped by state.
Use the reorder function to order the state data by error.
Using ggplot, set the aesthetic with state as the x-variable and error as the y-variable.
Use geom_boxplot to indicate that we want to plot a boxplot.
Use geom_point to add data points as a layer.

# The `errors` data have already been loaded. Examine them using the `head` function.
head(errors)

# Create a boxplot showing the errors by state for states with at least 5 polls with grades B+ or higher

-----------------------------------------------------------------------------------------------------------------------------------------------

Exercises

# Define `Pr_1` as the probability of the first son dying of SIDS
Pr_1 <- 1/8500

# Define `Pr_2` as the probability of the second son dying of SIDS
Pr_2 <- 1/100

# Calculate the probability of both sons dying of SIDS. Print this value to the console.
Pr_both <- Pr_1 * Pr_2
Pr_both

Exercise 4 - Calculate the Probability
Assume that the probability of a murderer finding a way to kill her two children without leaving evidence of physical harm is:
\mbox{Pr}(\mbox{two children found dead with no evidence of harm} \mid \mbox{mother is a murderer} ) = 0.50
Assume that the murder rate among mothers is 1 in 1,000,000.
\mbox{Pr}(\mbox{mother is a murderer} ) = 1/1,000,000
According to Bayes' rule, what is the probability of:
\mbox{Pr}(\mbox{mother is a murderer} \mid \mbox{two children found dead with no evidence of harm})
Instructions
Use Bayes' rule to calculate the probability that the mother is a murderer, considering the rates of murdering mothers in the population, the probability that two siblings die of SIDS, and the probability that a murderer kills children without leaving evidence of physical harm.
Print your result to the console.

# Define `Pr_1` as the probability of the first son dying of SIDS
Pr_1 <- 1/8500

# Define `Pr_2` as the probability of the second son dying of SIDS
Pr_2 <- 1/100

# Define `Pr_B` as the probability of both sons dying of SIDS
Pr_B <- Pr_1*Pr_2

# Define Pr_A as the rate of mothers that are murderers
Pr_A <- 1/1000000

# Define Pr_BA as the probability that two children die without evidence of harm, given that their mother is a murderer
Pr_BA <- 0.50

# Define Pr_AB as the probability that a mother is a murderer, given that her two children died with no evidence of physical harm. Print this value to the console.
Pr_B <- Pr_1 * Pr_2
Pr_AB <- (Pr_BA * Pr_A) / Pr_B
Pr_AB

Exercise 6 - Back to Election Polls
Florida is one of the most closely watched states in the U.S. election because it has many electoral votes and the election is generally close. Create a table with the poll spread results from Florida taken during the last days before the election using the sample code.
The CLT tells us that the average of these spreads is approximately normal. Calculate a spread average and provide an estimate of the standard error.
Instructions
Calculate the average of the spreads. Call this average avg in the final table.
Calculate an estimate of the standard error of the spreads. Call this standard error se in the final table.
Use the mean and sd functions nested within summarize to find the average and standard deviation of the grouped spread data.
Save your results in an object called results.

# Load the libraries and poll data
library(dplyr)
library(dslabs)
data(polls_us_election_2016)

# Create an object `polls` that contains the spread of predictions for each candidate in Florida during the last polling days
polls <- polls_us_election_2016 %>% 
  filter(state == "Florida" & enddate >= "2016-11-04" ) %>% 
  mutate(spread = rawpoll_clinton/100 - rawpoll_trump/100)

# Examine the `polls` object using the `head` function
head(polls)

# Create an object called `results` that has two columns containing the average spread (`avg`) and the standard error (`se`). Print the results to the console.

polls <- polls_us_election_2016 %>% 
  filter(state == "Florida" & enddate >= "2016-11-04") %>% 
  mutate(spread = rawpoll_clinton / 100 - rawpoll_trump / 100)

head(polls)

results <- polls %>%
  summarise(
    avg = mean(spread),
    se = sd(spread) / sqrt(n())
  )

results

---------------------------------------------------------------------------------------------------------------------------------------

Code: Monte Carlo simulation of confidence intervals
Note that to compute the exact 95% confidence interval, we would use qnorm(.975)*SE_hat instead of 2*SE_hat.
p <- 0.45
N <- 1000
X <- sample(c(0,1), size = N, replace = TRUE, prob = c(1-p, p))    # generate N observations
X_hat <- mean(X)    # calculate X_hat
SE_hat <- sqrt(X_hat*(1-X_hat)/N)    # calculate SE_hat, SE of the mean of N observations
c(X_hat - 2*SE_hat, X_hat + 2*SE_hat)    # build interval of 2*SE above and below mean

------------------------------------------------------------------------------------------------------------------------------------------------

