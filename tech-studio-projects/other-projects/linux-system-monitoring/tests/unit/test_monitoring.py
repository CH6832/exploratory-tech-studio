import unittest
from api import create_app

class MonitoringTestCase(unittest.TestCase):

    def setUp(self):
        self.app = create_app()
        self.client = self.app.test_client()

    def test_monitor_endpoint(self):
        response = self.client.get('/api/monitor')
        self.assertEqual(response.status_code, 200)
        self.assertIn('cpu_usage', response.get_json())
        
if __name__ == '__main__':
    unittest.main()
