from pymongo import MongoClient

client = MongoClient("mongodb://db:27017/")
db = client.movie_recommendation
