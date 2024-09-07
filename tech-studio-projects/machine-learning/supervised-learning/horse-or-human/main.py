# main.py

import data_preprocessing
import model_definition
import data_generators
import model_training
import predictions
import visualize_layers

def main():
    # Data preprocessing
    paths = data_preprocessing.download_and_extract_data()
    print("Sample horse images:", data_preprocessing.print_sample_file_names(paths['train_horse_dir']))
    print("Sample human images:", data_preprocessing.print_sample_file_names(paths['train_human_dir']))
    
    # Model definition
    model = model_definition.create_model()
    
    # Data generators
    train_generator, validation_generator = data_generators.create_data_generators(
        '/tmp/horse-or-human/', 
        '/tmp/validation-horse-or-human'
    )
    
    # Train model
    history = model_training.train_model(model, train_generator, validation_generator)
    
    # Make predictions
    results = predictions.make_predictions(model)
    for fn, result in results:
        print(f"{fn} is a {result}")
    
    # Visualize layers
    visualize_layers.visualize_layers(
        model,
        paths['train_horse_dir'],
        paths['train_human_dir']
    )

if __name__ == "__main__":
    main()
