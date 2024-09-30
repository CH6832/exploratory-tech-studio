import pytest
import pandas as pd
from services.data_ingestion.ingest_data import ingest_data

def test_ingest_data():
    ingest_data()
    df = pd.read_csv('./data/processed/processed_titanic.csv')
    assert not df.isnull().values.any(), "Processed data contains null values"
