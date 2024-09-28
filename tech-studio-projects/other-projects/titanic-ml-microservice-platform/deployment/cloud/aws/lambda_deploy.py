import boto3
import zipfile
import os

# Configuration
FUNCTION_NAME = "TitanicModelServing"  # Name of the Lambda function
ROLE_ARN = "arn:aws:iam::YOUR_AWS_ACCOUNT_ID:role/service-role/YOUR_ROLE_NAME"  # IAM Role for Lambda
HANDLER = "app.lambda_handler"  # Entry point for the Lambda function
RUNTIME = "python3.9"  # Runtime environment
ZIP_FILE = "titanic_model_serving.zip"  # Name of the zip file to be created

# Directory containing your Lambda function code
LAMBDA_CODE_DIRECTORY = "path/to/your/lambda_code"  # Update with your path

def create_zip(zip_file, directory):
    """Create a zip file of the specified directory."""
    with zipfile.ZipFile(zip_file, 'w') as z:
        for foldername, subfolders, filenames in os.walk(directory):
            for filename in filenames:
                file_path = os.path.join(foldername, filename)
                z.write(file_path, os.path.relpath(file_path, directory))
    print(f"Created zip file: {zip_file}")

def deploy_lambda_function(zip_file):
    """Deploy the Lambda function using boto3."""
    client = boto3.client('lambda')
    
    with open(zip_file, 'rb') as f:
        zipped_code = f.read()

    try:
        response = client.create_function(
            FunctionName=FUNCTION_NAME,
            Runtime=RUNTIME,
            Role=ROLE_ARN,
            Handler=HANDLER,
            Code=dict(ZipFile=zipped_code),
            Timeout=300,  # Maximum allowable timeout (in seconds)
            MemorySize=128  # Memory allocated for the function (in MB)
        )
        print(f"Lambda function '{FUNCTION_NAME}' created successfully!")
    except client.exceptions.ResourceConflictException:
        print(f"Lambda function '{FUNCTION_NAME}' already exists. Updating...")
        response = client.update_function_code(
            FunctionName=FUNCTION_NAME,
            ZipFile=zipped_code
        )
        print(f"Lambda function '{FUNCTION_NAME}' updated successfully!")

if __name__ == "__main__":
    create_zip(ZIP_FILE, LAMBDA_CODE_DIRECTORY)
    deploy_lambda_function(ZIP_FILE)
