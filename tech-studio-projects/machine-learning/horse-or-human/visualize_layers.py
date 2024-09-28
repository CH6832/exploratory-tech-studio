# visualize_layers.py

import matplotlib.pyplot as plt
import numpy as np
import random
from tensorflow.keras.preprocessing.image import img_to_array, load_img
import tensorflow as tf

def visualize_layers(model, train_horse_dir, train_human_dir):
    successive_outputs = [layer.output for layer in model.layers[1:]]
    visualization_model = tf.keras.models.Model(inputs=model.input, outputs=successive_outputs)

    img_path = random.choice(
        [os.path.join(train_horse_dir, f) for f in os.listdir(train_horse_dir)] +
        [os.path.join(train_human_dir, f) for f in os.listdir(train_human_dir)]
    )

    img = load_img(img_path, target_size=(100, 100))
    x = img_to_array(img) / 255.0
    x = np.expand_dims(x, axis=0)

    successive_feature_maps = visualization_model.predict(x)

    layer_names = [layer.name for layer in model.layers[1:]]
    for layer_name, feature_map in zip(layer_names, successive_feature_maps):
        if len(feature_map.shape) == 4:
            n_features = min(feature_map.shape[-1], 5)  # Limit to 5 features
            size = feature_map.shape[1]
            display_grid = np.zeros((size, size * n_features))
            for i in range(n_features):
                x = feature_map[0, :, :, i]
                x -= x.mean()
                x /= x.std()
                x *= 64
                x += 128
                x = np.clip(x, 0, 255).astype('uint8')
                display_grid[:, i * size : (i + 1) * size] = x
            scale = 20. / n_features
            plt.figure(figsize=(scale * n_features, scale))
            plt.title(layer_name)
            plt.grid(False)
            plt.imshow(display_grid, aspect='auto', cmap='viridis')
            plt.show()
