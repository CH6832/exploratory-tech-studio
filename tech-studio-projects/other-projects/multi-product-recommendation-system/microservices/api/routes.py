from fastapi import APIRouter
from models import RecommendationRequest
from services.recommendation_service import get_recommendations

router = APIRouter()

@router.post("/recommendations/")
async def recommend_movies(req: RecommendationRequest):
    recommendations = await get_recommendations(req.user_id)
    return {"recommendations": recommendations}
