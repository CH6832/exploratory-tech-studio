#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""tweets_analyzation.py

The script is designed to analyze engagement metrics from Twitter data
stored in a JSON file and identify influential users based on their level
of engagement. It loads the JSON data, extracts relevant engagement
metrics (likes, retweets, replies) from each tweet, and aggregates them
to identify users with significant engagement levels.

How the Script Works:
---------------------
1. Loading Data: The script reads Twitter data from a JSON file.
2. Analyzing Engagement: It analyzes the engagement metrics (likes, retweets, replies) for each tweet in the dataset and organizes them into a pandas DataFrame.
3. Identifying Influential Users: The script aggregates engagement metrics on a per-user basis to calculate the total engagement for each user. Users with total engagement exceeding a specified threshold are identified as influential.
4. Threshold Setting: The threshold for identifying influential users can be adjusted based on the specified parameter. This allows for flexibility in defining what constitutes "influential" based on engagement metrics.
5. Output: The script outputs a DataFrame containing the list of influential users along with their respective engagement metrics.

By analyzing engagement metrics such as likes, retweets, and replies, the
script provides insights into the most influential users within the Twitter
dataset. Adjusting the threshold parameter allows for fine-tuning the
definition of "influential" based on specific requirements and dataset
characteristics.
"""


import json
import pandas as pd
from typing import List, Dict, Any


def main():
    """program entry point"""
    # Load data from JSON file
    with open('data/tweets.json', 'r') as file:
        data = json.load(file)
    
    # analyze engagement
    engagement_df = analyze_engagement(data['data'])
    
    # identify influential users with a lower threshold of 10
    influential_users = identify_influential_users(engagement_df, threshold=10)
    
    print("Influential Users:")
    print(influential_users)


def analyze_engagement(data: List[Dict[str, Any]]) -> pd.DataFrame:
    """Analyzes engagement metrics such as likes, retweets, and replies from Twitter data.

    Keyword arguments:
    tweet_data (pd.DataFrame) -- DataFrame containing Twitter data with engagement metrics.
    """
    tweets = []
    for entry in data:
        tweet = {
            'User': entry['user']['screen_name'],
            'Likes': entry['favorite_count'],
            'Retweets': entry['retweet_count'],
            'Replies': 0  # Assuming we don't have reply information in the provided data
        }
        tweets.append(tweet)
    
    return pd.DataFrame(tweets)


def identify_influential_users(engagement_df: pd.DataFrame, threshold: int = 100) -> pd.DataFrame:
    """Identifies influential users based on engagement metrics such as likes, retweets, and replies.

    Keyword arguments:
    engagement_df (pd.DataFrame) -- DataFrame containing engagement metrics for each tweet/user.
    threshold (int, optional) -- Minimum total engagement threshold for considering a user as influential. Defaults to 100.
    """
    influential_users = engagement_df.groupby('User').agg({
        'Likes': 'sum',
        'Retweets': 'sum',
        'Replies': 'sum'
    }).reset_index()
    
    # calculate the total engagement for each user by summing up likes, retweets, and replies
    influential_users['Total Engagement'] = influential_users['Likes'] + influential_users['Retweets'] + influential_users['Replies']

    # filter users based on the specified threshold
    influential_users = influential_users[influential_users['Total Engagement'] >= threshold]

    # sort the remaining users in descending order of total engagement
    influential_users = influential_users.sort_values(by='Total Engagement', ascending=False)

    # return the DataFrame containing influential users
    return influential_users


if __name__ == "__main__":
    main()
