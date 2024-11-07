import pytest
import json
from app import app

@pytest.fixture
def client():
    app.testing = True
    with app.test_client() as client:
        yield client

def test_add_review(client):
    client.post('/products/', json={
        'name': 'Test Product',
        'price': 99.99,
        'description': 'This is a test product.',
        'stock': 10
    })
    response = client.post('/products/1/reviews/', json={
        'rating': 5,
        'comment': 'Excellent product!'
    })
    assert response.status_code == 201

def test_get_reviews(client):
    response = client.get('/products/1/reviews/')
    assert response.status_code == 200
