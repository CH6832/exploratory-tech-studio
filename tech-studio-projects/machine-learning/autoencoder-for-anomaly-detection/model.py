#!/usr/bin/env python3
# -*- coding: utf-8 -*-

""""""

import tensorflow as tf
from tensorflow.keras import layers, Model

class AnomalyDetector(Model):
    def __init__(self, embedding_size):
        super(AnomalyDetector, self).__init__()
        self.encoder = tf.keras.Sequential([
            layers.Dense(8, activation="relu"),
            layers.Dense(embedding_size, activation="relu")
        ])
        self.decoder = tf.keras.Sequential([
            layers.Dense(8, activation="relu"),
            layers.Dense(140, activation="sigmoid")
        ])
    
    def call(self, x):
        encoded = self.encoder(x)
        decoded = self.decoder(encoded)
        return decoded
