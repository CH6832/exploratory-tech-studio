#include <SPIFFS.h>
#include <TensorFlowLite.h>
#include "tensorflow/lite/micro/all_ops_resolver.h"
#include "tensorflow/lite/micro/micro_error_reporter.h"
#include "tensorflow/lite/micro/micro_interpreter.h"
#include "tensorflow/lite/schema/schema_generated.h"
#include "tensorflow/lite/version.h"

// TensorFlow Lite setup
const int tensor_arena_size = 2 * 1024;
uint8_t tensor_arena[tensor_arena_size];

const tflite::Model* model = nullptr;
tflite::MicroInterpreter* interpreter = nullptr;
tflite::MicroErrorReporter micro_error_reporter;
tflite::AllOpsResolver resolver;

// Monitoring variables
unsigned long inference_start_time;
unsigned long inference_end_time;
float memory_usage = 0;

void setup() {
  Serial.begin(115200);

  // Initialize SPIFFS or another file system
  if (!SPIFFS.begin(true)) {
    Serial.println("Failed to mount file system");
    return;
  }

  // Open the model file from SPIFFS
  File model_file = SPIFFS.open("/model.tflite", "r");
  if (!model_file) {
    Serial.println("Failed to open model file");
    return;
  }

  // Get the size of the model
  size_t model_size = model_file.size();

  // Allocate buffer to hold the model
  std::vector<uint8_t> model_buffer(model_size);
  model_file.read(model_buffer.data(), model_size);
  model_file.close();

  // Load the model into TensorFlow Lite
  model = tflite::GetModel(model_buffer.data());
  if (model->version() != TFLITE_SCHEMA_VERSION) {
    Serial.println("Model schema version does not match");
    return;
  }

  // Set up the interpreter
  interpreter = new tflite::MicroInterpreter(
      model, resolver, tensor_arena, tensor_arena_size, &micro_error_reporter);

  // Allocate memory for the tensors
  if (interpreter->AllocateTensors() != kTfLiteOk) {
    Serial.println("Failed to allocate tensors");
    return;
  }

  // Monitor memory usage
  memory_usage = (float)ESP.getFreeHeap() / 1024.0;  // Memory usage in KB

  // Get pointers to the input and output tensors
  float* input = interpreter->input(0)->data.f;
  float* output = interpreter->output(0)->data.f;

  // Set input values (example normalized input)
  input[0] = 0.025;  // Example normalized major version input
  input[1] = 0.0;    // Example normalized minor version input

  // Start timing inference
  inference_start_time = millis();

  // Run inference
  if (interpreter->Invoke() != kTfLiteOk) {
    Serial.println("Invoke failed");
    return;
  }

  // End timing inference
  inference_end_time = millis();

  // Output the results (denormalizing for readability)
  Serial.print("Predicted Major: ");
  Serial.println(output[0] * 40.0);  // Denormalize
  Serial.print("Predicted Minor: ");
  Serial.println(output[1] * 10.0);  // Denormalize

  // Output monitoring results
  Serial.print("Inference Time: ");
  Serial.print(inference_end_time - inference_start_time);
  Serial.println(" ms");

  Serial.print("Memory Usage: ");
  Serial.print(memory_usage);
  Serial.println(" KB");
}

void loop() {
  // Periodic updates or monitoring (if needed)
  delay(10000);  // Example delay for periodic checks

  // Monitor memory usage periodically
  memory_usage = (float)ESP.getFreeHeap() / 1024.0;  // Update memory usage in KB
  Serial.print("Current Memory Usage: ");
  Serial.print(memory_usage);
  Serial.println(" KB");
}
