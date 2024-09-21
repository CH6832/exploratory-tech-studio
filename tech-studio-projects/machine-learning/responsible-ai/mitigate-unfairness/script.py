#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""script.py


"""

# https://docs.pylint.org/
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import functools
import pandas as pd
import numpy as np
from keras import layers, models
import tensorflow as tf
from witwidget.notebook.visualization import WitConfigBuilder
from witwidget.notebook.visualization import WitWidget
import pandas as pd
import tensorflow as tf
from tensorflow.keras import layers
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler, LabelEncoder
import numpy as np
import pandas as pd
import tensorflow as tf
from tensorflow.keras import layers
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler, OneHotEncoder
import numpy as np


def make_label_column_numeric(df, label_column, label_function):
    df[label_column] = df[label_column].apply(label_function).astype(int)


def preprocess_data(df, input_features, label_column):
    # One-hot encode categorical features
    df = pd.get_dummies(df, columns=[col for col in input_features if df[col].dtype == 'object'])

    X = df.drop(columns=[label_column])
    y = df[label_column]

    # Normalize numerical features
    scaler = StandardScaler()
    X = scaler.fit_transform(X)

    return X, y


def main():
    """Driving code."""
    # Read training dataset from CSV
    csv_path = 'https://archive.ics.uci.edu/ml/machine-learning-databases/adult/adult.data'
    csv_columns = [
        "Age", "Workclass", "fnlwgt", "Education", "Education-Num", "Marital-Status",
        "Occupation", "Relationship", "Race", "Sex", "Capital-Gain", "Capital-Loss",
        "Hours-per-week", "Country", "Over-50K"
    ]

    df = pd.read_csv(csv_path, names=csv_columns, skipinitialspace=True)
    
    # Specify input columns and columns to predict
    label_column = 'Over-50K'
    make_label_column_numeric(df, label_column, lambda val: val == '>50K')

    input_features = [
        'Age', 'Workclass', 'Education', 'Marital-Status', 'Occupation',
        'Relationship', 'Race', 'Sex', 'Capital-Gain', 'Capital-Loss',
        'Hours-per-week', 'Country'
    ]

    # Preprocess data
    X, y = preprocess_data(df, input_features, label_column)

    # Split the data into training and validation sets
    X_train, X_val, y_train, y_val = train_test_split(X, y, test_size=0.2, random_state=42)

    # Create a DNN model using Keras
    model = tf.keras.Sequential([
        layers.Dense(128, activation='relu', input_shape=(X_train.shape[1],)),
        layers.Dense(64, activation='relu'),
        layers.Dense(32, activation='relu'),
        layers.Dense(1, activation='sigmoid')
    ])

    # Compile the model
    model.compile(optimizer='adam',
                  loss='binary_crossentropy',
                  metrics=['accuracy'])

    # Train the model
    model.fit(X_train, y_train, epochs=10, batch_size=32, validation_data=(X_val, y_val))

    # Optionally, evaluate the model
    loss, accuracy = model.evaluate(X_val, y_val)
    print(f"Validation Accuracy: {accuracy * 100:.2f}%")

    return None


def make_label_column_numeric(df, label_column, label_function):
    df[label_column] = df[label_column].apply(label_function).astype(int)


def preprocess_data(df, input_features, label_column):
    # One-hot encode categorical features and normalize numerical features
    df = pd.get_dummies(df, columns=[col for col in input_features if df[col].dtype == 'object'])

    X = df.drop(columns=[label_column])
    y = df[label_column]

    # Normalize numerical features
    scaler = StandardScaler()
    X = scaler.fit_transform(X)

    return X, y


def create_feature_spec(df, columns=None):
    """Create feature specifications"""
    feature_spec = {}
    if columns is None:
        columns = df.columns.values.tolist()
    for f in columns:
        if df[f].dtype is np.dtype(np.int64):
            feature_spec[f] = tf.io.FixedLenFeature(shape=(), dtype=tf.int64)
        elif df[f].dtype is np.dtype(np.float64):
            feature_spec[f] = tf.io.FixedLenFeature(shape=(), dtype=tf.float32)
        else:
            feature_spec[f] = tf.io.FixedLenFeature(shape=(), dtype=tf.string)
    return feature_spec


def create_feature_columns(columns, feature_spec):
    """Create feature columns."""
    ret = []
    for col in columns:
        if feature_spec[col].dtype is tf.int64 or feature_spec[col].dtype is tf.float32:
            ret.append(tf.feature_column.numeric_column(col))
        else:
            ret.append(tf.feature_column.indicator_column(
                tf.feature_column.categorical_column_with_vocabulary_list(col, list(df[col].unique()))))

    return ret


def tfexamples_input_fn(examples, feature_spec, label, mode=tf.estimator.ModeKeys.EVAL,
                       num_epochs=None, 
                       batch_size=64):
    """Tensorflow exmaple input."""
    def ex_generator():
        for i in range(len(examples)):
            yield examples[i].SerializeToString()
    dataset = tf.data.Dataset.from_generator(
      ex_generator, tf.dtypes.string, tf.TensorShape([]))
    if mode == tf.estimator.ModeKeys.TRAIN:
        dataset = dataset.shuffle(buffer_size=2 * batch_size + 1)
    dataset = dataset.batch(batch_size)
    dataset = dataset.map(lambda tf_example: parse_tf_example(tf_example, label, feature_spec))
    dataset = dataset.repeat(num_epochs)
    
    return dataset


def parse_tf_example(example_proto, label, feature_spec):
    """Parse tesorflow example."""
    parsed_features = tf.io.parse_example(serialized=example_proto, features=feature_spec)
    target = parsed_features.pop(label)

    return parsed_features, target


def df_to_examples(df, columns=None):
    """Convert dataframes to examples."""
    examples = []
    if columns is None:
        columns = df.columns.values.tolist()
    for index, row in df.iterrows():
        example = tf.train.Example()
        for col in columns:
            if df[col].dtype is np.dtype(np.int64):
                example.features.feature[col].int64_list.value.append(int(row[col]))
            elif df[col].dtype is np.dtype(np.float64):
                example.features.feature[col].float_list.value.append(row[col])
            elif row[col] == row[col]:
                example.features.feature[col].bytes_list.value.append(row[col].encode('utf-8'))
        examples.append(example)

    return examples


def make_label_column_numeric(df, label_column, test) -> None:
    """Make label columns numeric."""
    df[label_column] = np.where(test(df[label_column]), 1, 0)

    return None


if __name__ == "__main__":
    main()
