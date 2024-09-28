import pytest
from fastapi.testclient import TestClient
from microservices.api.main import app
from pymongo import MongoClient
from bson import ObjectId

# Create a test client
client = TestClient(app)

# MongoDB client setup for testing
test_client = MongoClient("mongodb://mongodb:27017/")
test_db = test_client["movie_recommendation_db"]
test_users_collection = test_db["users"]

# Sample user data for testing
test_user = {
    "name": "John Doe",
    "email": "john.doe@example.com"
}

@pytest.fixture(autouse=True)
def setup_and_teardown():
    # Clear the test database before and after each test
    test_users_collection.delete_many({})
    yield
    test_users_collection.delete_many({})

def test_create_user():
    response = client.post("/api/users/", json=test_user)
    assert response.status_code == 200
    assert response.json()["name"] == test_user["name"]
    assert response.json()["email"] == test_user["email"]
    assert "id" in response.json()

def test_get_user():
    # Create a user first
    create_response = client.post("/api/users/", json=test_user)
    user_id = create_response.json()["id"]

    response = client.get(f"/api/users/{user_id}")
    assert response.status_code == 200
    assert response.json()["name"] == test_user["name"]
    assert response.json()["email"] == test_user["email"]
    assert response.json()["id"] == user_id

def test_update_user():
    # Create a user first
    create_response = client.post("/api/users/", json=test_user)
    user_id = create_response.json()["id"]

    updated_user_data = {"name": "Jane Doe", "email": "jane.doe@example.com"}
    response = client.put(f"/api/users/{user_id}", json=updated_user_data)
    assert response.status_code == 200
    assert response.json()["name"] == updated_user_data["name"]
    assert response.json()["email"] == updated_user_data["email"]
    assert response.json()["id"] == user_id

def test_delete_user():
    # Create a user first
    create_response = client.post("/api/users/", json=test_user)
    user_id = create_response.json()["id"]

    response = client.delete(f"/api/users/{user_id}")
    assert response.status_code == 200
    assert response.json() == {"message": "User deleted successfully"}

    # Verify the user is deleted
    get_response = client.get(f"/api/users/{user_id}")
    assert get_response.status_code == 404

def test_get_all_users():
    # Create multiple users
    client.post("/api/users/", json=test_user)
    client.post("/api/users/", json={"name": "Alice", "email": "alice@example.com"})

    response = client.get("/api/users/")
    assert response.status_code == 200
    assert len(response.json()) >= 2
