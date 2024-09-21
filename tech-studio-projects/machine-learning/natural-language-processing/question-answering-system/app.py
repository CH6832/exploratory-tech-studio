from flask import Flask, request, jsonify
from transformers import BertTokenizer, BertForQuestionAnswering
import torch
import ctypes
import platform
import os

app = Flask(__name__)

# Load pre-trained model and tokenizer
tokenizer = BertTokenizer.from_pretrained('bert-large-uncased')
model = BertForQuestionAnswering.from_pretrained('bert-large-uncased')

# Load the appropriate C++ shared library based on the operating system
if platform.system() == 'Windows':
    lib = ctypes.CDLL('./preprocessor.dll')
elif platform.system() == 'Darwin':
    lib = ctypes.CDLL('./preprocessor.dylib')
else:
    raise RuntimeError('Unsupported operating system')

# Define the argument and return types of the C++ function
lib.preprocess_text.restype = ctypes.c_char_p

@app.route('/answer', methods=['POST'])
def answer_question():
    data = request.json
    question = data['question']
    context = data['context']

    # Preprocess text using C++
    processed_question = lib.preprocess_text(question.encode('utf-8')).decode('utf-8')
    processed_context = lib.preprocess_text(context.encode('utf-8')).decode('utf-8')

    # Encode the question and context
    inputs = tokenizer.encode_plus(processed_question, processed_context, add_special_tokens=True, return_tensors='pt')

    # Get model predictions
    with torch.no_grad():
        outputs = model(**inputs)

    # Get the start and end scores
    start_scores = outputs.start_logits
    end_scores = outputs.end_logits

    # Convert the tokens to answer
    answer_start = torch.argmax(start_scores)
    answer_end = torch.argmax(end_scores) + 1
    answer_tokens = tokenizer.convert_ids_to_tokens(inputs['input_ids'][0][answer_start:answer_end])

    answer = tokenizer.convert_tokens_to_string(answer_tokens)
    
    return jsonify({'answer': answer})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
