import boto3
import json
import time

# Initialize the SQS client
sqs = boto3.client('sqs', region_name='your-region')

# The URL of your SQS Queue
QUEUE_URL = 'https://sqs.your-region.amazonaws.com/your-account-id/your-queue-name'

def process_city_data_from_sqs():
    while True:
        # Poll the queue for messages
        response = sqs.receive_message(
            QueueUrl=QUEUE_URL,
            MaxNumberOfMessages=1,  # process one message at a time
            WaitTimeSeconds=10  # long polling for 10 seconds
        )

        # Check if there are any messages
        messages = response.get('Messages', [])
        if messages:
            for message in messages:
                # Process the message
                message_body = json.loads(message['Body'])
                city_name = message_body['city']
                print(f'Processing data for city: {city_name}')
                # Here you would fetch and process the city data
                # Simulate with a sleep function
                time.sleep(2)
                print(f'Data processed for city: {city_name}')
                
                # Delete the message from the queue once processed
                sqs.delete_message(
                    QueueUrl=QUEUE_URL,
                    ReceiptHandle=message['ReceiptHandle']
                )
        else:
            print('No messages in queue. Waiting...')

if __name__ == "__main__":
    process_city_data_from_sqs()
