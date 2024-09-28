from fastapi import APIRouter, HTTPException
from pydantic import BaseModel, EmailStr
from typing import List, Optional
from pymongo import MongoClient
from bson import ObjectId

# MongoDB client setup
client = MongoClient("mongodb://mongodb:27017/")
db = client["movie_recommendation_db"]
users_collection = db["users"]

# Initialize FastAPI router
router = APIRouter()

# User model
class User(BaseModel):
    id: Optional[str] = None
    name: str
    email: EmailStr

# Helper function to convert MongoDB ObjectId to string
def user_helper(user) -> dict:
    return {
        "id": str(user["_id"]),
        "name": user["name"],
        "email": user["email"],
    }

# Create a new user
@router.post("/users/", response_model=User)
async def create_user(user: User):
    user_dict = user.dict(exclude_unset=True)
    result = users_collection.insert_one(user_dict)
    user_dict["id"] = str(result.inserted_id)
    return user_dict

# Get a user by ID
@router.get("/users/{user_id}", response_model=User)
async def get_user(user_id: str):
    user = users_collection.find_one({"_id": ObjectId(user_id)})
    if user:
        return user_helper(user)
    raise HTTPException(status_code=404, detail="User not found")

# Update a user by ID
@router.put("/users/{user_id}", response_model=User)
async def update_user(user_id: str, user: User):
    user_data = user.dict(exclude_unset=True)
    result = users_collection.update_one({"_id": ObjectId(user_id)}, {"$set": user_data})
    
    if result.modified_count == 0:
        raise HTTPException(status_code=404, detail="User not found or no change made")
    
    updated_user = users_collection.find_one({"_id": ObjectId(user_id)})
    return user_helper(updated_user)

# Delete a user by ID
@router.delete("/users/{user_id}", response_model=dict)
async def delete_user(user_id: str):
    result = users_collection.delete_one({"_id": ObjectId(user_id)})
    
    if result.deleted_count == 0:
        raise HTTPException(status_code=404, detail="User not found")
    
    return {"message": "User deleted successfully"}

# Get all users
@router.get("/users/", response_model=List[User])
async def get_all_users():
    users = users_collection.find()
    return [user_helper(user) for user in users]
