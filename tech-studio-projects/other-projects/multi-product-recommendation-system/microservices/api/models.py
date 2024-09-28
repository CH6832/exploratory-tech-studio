from pymongo import MongoClient
from bson import ObjectId
from pydantic import BaseModel, EmailStr, Field
from typing import Optional, List

# MongoDB client setup
mongo_client = MongoClient("mongodb://mongodb:27017/")
db = mongo_client["movie_recommendation_db"]
users_collection = db["users"]

# Pydantic model for User
class User(BaseModel):
    id: Optional[str] = Field(default=None, alias="_id")
    name: str
    email: EmailStr

    class Config:
        # Allow the use of `_id` as `id` for Pydantic
        allow_population_by_field_name = True
        json_encoders = {
            ObjectId: str  # Convert ObjectId to string for JSON serialization
        }

# Function to create a new user
def create_user(user_data: User) -> User:
    user_dict = user_data.dict(exclude={"id"})
    result = users_collection.insert_one(user_dict)
    user_data.id = str(result.inserted_id)
    return user_data

# Function to get a user by ID
def get_user(user_id: str) -> Optional[User]:
    user_doc = users_collection.find_one({"_id": ObjectId(user_id)})
    if user_doc:
        return User(**user_doc)
    return None

# Function to update a user
def update_user(user_id: str, user_data: User) -> bool:
    update_result = users_collection.update_one(
        {"_id": ObjectId(user_id)},
        {"$set": user_data.dict(exclude={"id"})}
    )
    return update_result.modified_count > 0

# Function to delete a user
def delete_user(user_id: str) -> bool:
    delete_result = users_collection.delete_one({"_id": ObjectId(user_id)})
    return delete_result.deleted_count > 0

# Function to get all users
def get_all_users() -> List[User]:
    users = []
    for user_doc in users_collection.find():
        users.append(User(**user_doc))
    return users
