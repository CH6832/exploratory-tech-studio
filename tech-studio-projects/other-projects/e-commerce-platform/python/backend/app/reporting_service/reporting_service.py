from flask import Flask, jsonify
from kafka import KafkaProducer
import json

app = Flask(__name__)

# Initialize Kafka Producer
producer = KafkaProducer(bootstrap_servers='localhost:9092',
                         value_serializer=lambda v: json.dumps(v).encode('utf-8'))

@app.route('/reports/sales/', methods=['GET'])
def sales_report():
    # Mock report
    report_data = {'total_sales': 10000, 'number_of_orders': 250}
    
    # Publish report data to Kafka
    producer.send('sales_reports', report_data)
    
    return jsonify(report_data), 200

if __name__ == '__main__':
    app.run(debug=True, port=5013)
