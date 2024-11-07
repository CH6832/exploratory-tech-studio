import json

def save_to_json_file(data, filename):
    with open(filename, 'w') as file:
        json.dump(data, file, indent=4)

def load_from_json_file(filename):
    try:
        with open(filename, 'r') as file:
            return json.load(file)
    except FileNotFoundError:
        return {}
