import pika

def send_message(message: str):
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()
    channel.queue_declare(queue='movie_recommendations')
    channel.basic_publish(exchange='', routing_key='movie_recommendations', body=message)
    print(f"Sent message: {message}")
    connection.close()



# producer.py

from kafka import KafkaProducer
import json
import time

class NotificationProducer:
    def __init__(self, topic='notifications'):
        self.producer = KafkaProducer(
            bootstrap_servers='localhost:9092',
            value_serializer=lambda v: json.dumps(v).encode('utf-8')
        )
        self.topic = topic

    def send_notification(self, user_id, recommendations):
        notification = {
            "user_id": user_id,
            "message": "Check out your new movie recommendations!",
            "recommendations": recommendations
        }
        self.producer.send(self.topic, notification)
        self.producer.flush()
        print(f"Notification sent to user {user_id}: {recommendations}")

if __name__ == "__main__":
    producer = NotificationProducer()
    
    # Simulate sending notifications
    # Replace this part with actual logic that updates recommendations
    time.sleep(1)  # Simulate a delay
    producer.send_notification("user123", ["Movie A", "Movie B", "Movie C"])
    
    time.sleep(1)  # Simulate a delay
    producer.send_notification("user456", ["Movie D", "Movie E"])
