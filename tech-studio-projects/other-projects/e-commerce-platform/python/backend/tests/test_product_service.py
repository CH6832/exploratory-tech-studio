import pytest
import json
from app import app

@pytest.fixture
def client():
    app.testing = True
    with app.test_client() as client:
        yield client

def test_add_product(client):
    response = client.post('/products/', json={
        'name': 'Test Product',
        'price': 99.99,
        'description': 'This is a test product.',
        'stock': 10
    })
    assert response.status_code == 201
    assert 'id' in json.loads(response.data)

def test_get_products(client):
    response = client.get('/products/')
    assert response.status_code == 200

def test_update_product(client):
    response = client.post('/products/', json={
        'name': 'Test Product',
        'price': 99.99,
        'description': 'This is a test product.',
        'stock': 10
    })
    product_id = json.loads(response.data)['id']
    response = client.put(f'/products/{product_id}', json={'price': 89.99})
    assert response.status_code == 200

def test_delete_product(client):
    response = client.post('/products/', json={
        'name': 'Test Product',
        'price': 99.99,
        'description': 'This is a test product.',
        'stock': 10
    })
    product_id = json.loads(response.data)['id']
    response = client.delete(f'/products/{product_id}')
    assert response.status_code == 200
