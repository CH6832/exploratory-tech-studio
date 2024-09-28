# model_training.py

def train_model(model, train_generator, validation_generator):
    history = model.fit(
        train_generator,
        steps_per_epoch=8,  # Adjust based on your dataset size
        epochs=100,
        verbose=1,
        validation_data=validation_generator
    )
    return history
