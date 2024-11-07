import pytest
import json
from app import app

@pytest.fixture
def client():
    app.testing = True
    with app.test_client() as client:
        yield client

def test_get_inventory(client):
    response = client.get('/inventory/')
    assert response.status_code == 200

def test_update_inventory(client):
    response = client.put('/inventory/1/', json={'stock': 100})
    assert response.status_code == 200
    assert 'message' in json.loads(response.data)
