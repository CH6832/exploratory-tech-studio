from sklearn.metrics.pairwise import cosine_similarity
import pandas as pd

class CollaborativeFiltering:
    def __init__(self, user_item_matrix):
        self.user_item_matrix = user_item_matrix

    def recommend(self, user_id, num_recommendations=5):
        user_vector = self.user_item_matrix.loc[user_id].values.reshape(1, -1)
        similarities = cosine_similarity(user_vector, self.user_item_matrix)
        similar_indices = similarities.argsort()[0][-num_recommendations:]
        return self.user_item_matrix.index[similar_indices].tolist()
