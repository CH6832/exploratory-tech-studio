#include <Arduino.h>
#include <TensorFlowLite.h>
#include <MicroFeatures.h>
#include <model_settings.h>
#include "KWS_model.cc" // Include your converted model file

// Define the TensorFlow Lite model
const unsigned char* model_data = g_model;
const int model_data_len = g_model_len;

// Create TensorFlow Lite objects
tflite::MicroInterpreter* interpreter;
tflite::MicroErrorReporter micro_error_reporter;
tflite::MicroInterpreter::Model* model;
tflite::MicroInterpreter::Model* model;

// Create a tensor arena for TensorFlow Lite
constexpr int kTensorArenaSize = 10 * 1024;
uint8_t tensor_arena[kTensorArenaSize];

// Create an instance of the model
void setup() {
  Serial.begin(115200);
  delay(1000);
  Serial.println("Starting up...");

  // Set up TensorFlow Lite model
  model = tflite::GetModel(model_data);
  static tflite::MicroInterpreter interpreter(model, tensor_arena, kTensorArenaSize, &micro_error_reporter);
  
  // Allocate tensors
  interpreter.AllocateTensors();

  // Initialize audio
  audio.begin();
  Serial.println("Setup complete.");
}

void loop() {
  // Collect audio data
  int16_t audio_data[AudioInput::kMaxBufferSize];
  audio.read(audio_data, AudioInput::kMaxBufferSize);
  
  // Preprocess audio data
  GenerateMicroFeatures(audio_data, tensor_arena);

  // Run inference
  interpreter.Invoke();
  
  // Get the classification result
  const float* output = interpreter.output(0)->data.f;
  int max_index = 0;
  float max_value = output[0];
  
  for (int i = 1; i < kCategoryCount; ++i) {
    if (output[i] > max_value) {
      max_value = output[i];
      max_index = i;
    }
  }

  // Post-process result and control LEDs
  switch(max_index) {
    case 0: // silence
      Serial.println("Silence detected.");
      // Turn off LEDs
      digitalWrite(LED_BUILTIN, LOW);
      break;
    case 1: // unknown
      Serial.println("Unknown command.");
      // Set LED color to blue
      digitalWrite(LED_BUILTIN, HIGH);
      break;
    case 2: // keyword1
      Serial.println("Keyword 1 detected.");
      // Set LED color to green
      digitalWrite(LED_BUILTIN, HIGH);
      break;
    case 3: // keyword2
      Serial.println("Keyword 2 detected.");
      // Set LED color to red
      digitalWrite(LED_BUILTIN, HIGH);
      break;
    // Add more cases for additional keywords
    default:
      Serial.println("No command detected.");
      // Turn off LEDs
      digitalWrite(LED_BUILTIN, LOW);
      break;
  }

  delay(100); // Small delay to avoid flooding serial output
}
