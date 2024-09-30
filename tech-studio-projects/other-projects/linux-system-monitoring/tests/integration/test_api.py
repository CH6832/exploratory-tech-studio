import unittest
from api import create_app

class APITestCase(unittest.TestCase):

    def setUp(self):
        self.app = create_app()
        self.client = self.app.test_client()

    def test_remote_monitoring(self):
        # Replace with a real remote host for integration testing
        response = self.client.get('/api/monitor?host=192.168.1.10')
        self.assertEqual(response.status_code, 200)
        
if __name__ == '__main__':
    unittest.main()
