from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel
import pandas as pd

class ContentBasedRecommender:
    def __init__(self, data):
        self.data = data
        self.tfidf = TfidfVectorizer(stop_words='english')
        self.tfidf_matrix = self.tfidf.fit_transform(data['description'])
        self.cosine_sim = linear_kernel(self.tfidf_matrix, self.tfidf_matrix)

    def recommend(self, title, num_recommendations=5):
        idx = self.data.index[self.data['title'] == title].tolist()[0]
        sim_scores = list(enumerate(self.cosine_sim[idx]))
        sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
        movie_indices = [i[0] for i in sim_scores[1:num_recommendations+1]]
        return self.data['title'].iloc[movie_indices].tolist()
