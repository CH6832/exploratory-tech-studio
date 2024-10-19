import pandas as pd

# Load the dataset
def load_data(file_path):
    return pd.read_csv(file_path, header=None)

# Preprocess the dataset
def preprocess_data(data):
    # Assign column names
    columns = [
        "duration", "protocol_type", "service", "flag", "src_bytes", "dst_bytes",
        "land", "wrong_fragment", "urgent", "hot", "num_failed_logins",
        "logged_in", "num_compromised", "root_shell", "su_attempted",
        "num_root", "num_file_creations", "num_shells", "num_access_files",
        "num_outbound_cmds", "is_host_login", "is_guest_login", "count",
        "srv_count", "serror_rate", "srv_serror_rate", "rerror_rate",
        "srv_rerror_rate", "same_srv_rate", "diff_srv_rate", "srv_diff_host_rate",
        "dst_host_count", "dst_host_srv_count", "dst_host_same_srv_rate",
        "dst_host_diff_srv_rate", "dst_host_same_src_port_rate",
        "dst_host_srv_diff_host_rate", "label"
    ]
    data.columns = columns

    # Encode categorical variables
    data = pd.get_dummies(data, columns=["protocol_type", "service", "flag"], drop_first=True)

    # Convert labels to binary
    data['label'] = data['label'].apply(lambda x: 1 if x != 'normal.' else 0)

    return data

# Save the processed data
def save_processed_data(data, output_file):
    data.to_csv(output_file, index=False)

if __name__ == "__main__":
    raw_file_path = "data/raw/kddcup.data_10_percent.gz"
    processed_file_path = "data/processed/processed_data.csv"
    
    # Load, preprocess, and save the data
    data = load_data(raw_file_path)
    processed_data = preprocess_data(data)
    save_processed_data(processed_data, processed_file_path)
    print("Data preprocessing complete.")
