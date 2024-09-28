def engineer_features(df):
    df['description'] = df['description'].fillna('')
    df['genres'] = df['genres'].apply(lambda x: x.split('|'))  # Assuming genres are pipe-separated
    return df
