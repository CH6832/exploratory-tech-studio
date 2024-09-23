from flask import Flask
from flask_cors import CORS

app = Flask(__name__)
CORS(app)  # Enable CORS for all routes

# Import services
from order_service.order_service import app as order_service
from auth_service.auth_service import app as auth_service
from cart_service.cart_service import app as cart_service
from review_service.review_service import app as review_service
from inventory_service.inventory_service import app as inventory_service
from user_profile_service.user_profile_service import app as user_profile_service
from shipping_service.shipping_service import app as shipping_service
from activity_logging_service.activity_logging_service import app as activity_logging_service

# Register services as blueprints
app.register_blueprint(order_service, url_prefix='/order')
app.register_blueprint(auth_service, url_prefix='/auth')
app.register_blueprint(cart_service, url_prefix='/cart')
app.register_blueprint(review_service, url_prefix='/reviews')
app.register_blueprint(inventory_service, url_prefix='/inventory')
app.register_blueprint(user_profile_service, url_prefix='/users')
app.register_blueprint(shipping_service, url_prefix='/shipping')
app.register_blueprint(activity_logging_service, url_prefix='/activity')

if __name__ == '__main__':
    app.run(debug=True, port=5000)
