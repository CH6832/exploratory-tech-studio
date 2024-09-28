import unittest
import requests
import json

class ModelServingTestCase(unittest.TestCase):
    BASE_URL = "http://localhost:5000/predict"  # Update with your model serving endpoint

    def test_model_loading(self):
        """Test if the model loads correctly."""
        response = requests.get(f"{self.BASE_URL}/load")
        self.assertEqual(response.status_code, 200)
        self.assertIn("model_loaded", response.json())
        self.assertTrue(response.json()["model_loaded"])

    def test_model_inference(self):
        """Test sending data to the model for inference."""
        test_input = {
            "feature1": 1.0,
            "feature2": 2.0,
            "feature3": 3.0
        }  # Modify according to your model's expected input

        expected_output_keys = ["prediction", "confidence"]  # Modify according to your model's expected output

        response = requests.post(self.BASE_URL, json=test_input)
        self.assertEqual(response.status_code, 200)
        response_json = response.json()

        # Check that response contains expected keys
        for key in expected_output_keys:
            self.assertIn(key, response_json)

if __name__ == "__main__":
    unittest.main()
