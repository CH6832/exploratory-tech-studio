import pika

def callback(ch, method, properties, body):
    print(f"Received message: {body}")

def consume():
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()
    channel.queue_declare(queue='movie_recommendations')
    channel.basic_consume(queue='movie_recommendations', on_message_callback=callback, auto_ack=True)
    print('Waiting for messages. To exit press CTRL+C')
    channel.start_consuming()



# consumer.py

from kafka import KafkaConsumer
import json

class NotificationConsumer:
    def __init__(self, topic='notifications'):
        self.consumer = KafkaConsumer(
            topic,
            bootstrap_servers='localhost:9092',
            auto_offset_reset='earliest',
            group_id='notification_group',
            value_deserializer=lambda x: json.loads(x.decode('utf-8'))
        )

    def process_notifications(self):
        for message in self.consumer:
            notification = message.value
            self.send_notification(notification)

    def send_notification(self, notification):
        # Simulate sending an email or push notification
        user_id = notification['user_id']
        recommendations = notification['recommendations']
        print(f"Sending notification to user {user_id}: {recommendations}")

if __name__ == "__main__":
    consumer = NotificationConsumer()
    consumer.process_notifications()

