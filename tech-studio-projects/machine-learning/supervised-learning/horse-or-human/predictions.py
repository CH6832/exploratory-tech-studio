# predictions.py

import numpy as np
from tensorflow.keras.utils import load_img, img_to_array
from google.colab import files

def make_predictions(model):
    uploaded = files.upload()
    results = []
    
    for fn in uploaded.keys():
        path = '/content/' + fn
        img = load_img(path, target_size=(100, 100))
        x = img_to_array(img) / 255.0
        x = np.expand_dims(x, axis=0)
        
        classes = model.predict(x)
        result = (fn, "human" if classes[0] > 0.5 else "horse")
        results.append(result)
    
    return results
