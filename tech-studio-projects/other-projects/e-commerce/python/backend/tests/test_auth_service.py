import pytest
import json
from app import app

@pytest.fixture
def client():
    app.testing = True
    with app.test_client() as client:
        yield client

def test_register(client):
    response = client.post('/auth/register/', json={
        'email': 'test@example.com',
        'password': 'password123'
    })
    assert response.status_code == 201
    assert 'user_id' in json.loads(response.data)

def test_login(client):
    client.post('/auth/register/', json={
        'email': 'test@example.com',
        'password': 'password123'
    })
    response = client.post('/auth/login/', json={
        'email': 'test@example.com',
        'password': 'password123'
    })
    assert response.status_code == 200
    assert 'token' in json.loads(response.data)

def test_get_profile(client):
    register_response = client.post('/auth/register/', json={
        'email': 'test@example.com',
        'password': 'password123'
    })
    token = json.loads(register_response.data)['token']
    response = client.get('/auth/me/', headers={'Authorization': f'Bearer {token}'})
    assert response.status_code == 200
