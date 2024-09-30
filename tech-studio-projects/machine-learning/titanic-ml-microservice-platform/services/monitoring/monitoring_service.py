import time
import requests
import logging
from datetime import datetime

# Configure logging
logging.basicConfig(filename='monitoring.log', level=logging.INFO,
                    format='%(asctime)s - %(levelname)s - %(message)s')

class ModelMonitoringService:
    def __init__(self, model_service_url):
        self.model_service_url = model_service_url
        self.health_check_interval = 60  # Check every 60 seconds

    def check_model_health(self):
        """Check if the model service is healthy."""
        try:
            response = requests.get(f"{self.model_service_url}/load")
            if response.status_code == 200 and response.json().get("model_loaded"):
                logging.info("Model service is healthy.")
            else:
                logging.warning("Model service is not healthy.")
        except requests.RequestException as e:
            logging.error(f"Error connecting to model service: {e}")

    def log_metrics(self):
        """Log the model serving metrics."""
        try:
            response = requests.get(f"{self.model_service_url}/metrics")  # Assuming a metrics endpoint exists
            if response.status_code == 200:
                metrics = response.json()
                logging.info(f"Metrics: {metrics}")
            else:
                logging.warning("Failed to retrieve metrics.")
        except requests.RequestException as e:
            logging.error(f"Error connecting to metrics endpoint: {e}")

    def run(self):
        """Start the monitoring service."""
        logging.info("Starting monitoring service...")
        while True:
            self.check_model_health()
            self.log_metrics()
            time.sleep(self.health_check_interval)

if __name__ == "__main__":
    model_service_url = "http://localhost:5000"  # Update with the actual URL of your model serving API
    monitoring_service = ModelMonitoringService(model_service_url)
    monitoring_service.run()
