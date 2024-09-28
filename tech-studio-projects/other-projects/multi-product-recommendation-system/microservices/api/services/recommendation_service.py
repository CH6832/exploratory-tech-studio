from typing import List
from random import sample

# Mock function to get movie recommendations
async def get_recommendations(user_id: int) -> List[str]:
    # This function should interface with your ML model to get recommendations
    sample_movies = ['Movie A', 'Movie B', 'Movie C', 'Movie D', 'Movie E']
    return sample(sample_movies, k=3)
