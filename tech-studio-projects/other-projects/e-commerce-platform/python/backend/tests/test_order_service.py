import pytest
import json
from app import app

@pytest.fixture
def client():
    app.testing = True
    with app.test_client() as client:
        yield client

def test_create_order(client):
    client.post('/cart/', json={
        'product_id': 1,
        'quantity': 2
    })
    response = client.post('/orders/')
    assert response.status_code == 201
    assert 'order_id' in json.loads(response.data)

def test_get_order(client):
    order_response = client.post('/orders/')
    order_id = json.loads(order_response.data)['order_id']
    response = client.get(f'/orders/{order_id}')
    assert response.status_code == 200

def test_cancel_order(client):
    order_response = client.post('/orders/')
    order_id = json.loads(order_response.data)['order_id']
    response = client.post(f'/orders/{order_id}/cancel')
    assert response.status_code == 200
