import pytest
import json
from app import app

@pytest.fixture
def client():
    app.testing = True
    with app.test_client() as client:
        yield client

def test_add_to_cart(client):
    response = client.post('/cart/', json={
        'product_id': 1,
        'quantity': 2
    })
    assert response.status_code == 201
    assert 'item_id' in json.loads(response.data)

def test_get_cart(client):
    client.post('/cart/', json={
        'product_id': 1,
        'quantity': 2
    })
    response = client.get('/cart/')
    assert response.status_code == 200

def test_remove_from_cart(client):
    response = client.post('/cart/', json={
        'product_id': 1,
        'quantity': 2
    })
    item_id = json.loads(response.data)['item_id']
    response = client.delete(f'/cart/{item_id}')
    assert response.status_code == 200
