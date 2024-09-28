# data_preprocessing.py

import os
import zipfile
import wget

def download_and_extract_data():
    urls = {
        'horse_or_human': 'https://storage.googleapis.com/learning-datasets/horse-or-human.zip',
        'validation_horse_or_human': 'https://storage.googleapis.com/learning-datasets/validation-horse-or-human.zip'
    }
    
    # Download datasets
    for name, url in urls.items():
        zip_path = f'/tmp/{name}.zip'
        extract_to = f'/tmp/{name}'
        wget.download(url, zip_path)
        with zipfile.ZipFile(zip_path, 'r') as zip_ref:
            zip_ref.extractall(extract_to)
    
    # Set directory paths
    paths = {
        'train_horse_dir': os.path.join('/tmp/horse-or-human/horses'),
        'train_human_dir': os.path.join('/tmp/horse-or-human/humans'),
        'validation_horse_dir': os.path.join('/tmp/validation-horse-or-human/horses'),
        'validation_human_dir': os.path.join('/tmp/validation-horse-or-human/humans')
    }
    
    return paths

def print_sample_file_names(dir_path, num_samples=10):
    return os.listdir(dir_path)[:num_samples]
