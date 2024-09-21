#include <SPIFFS.h>
#include <TensorFlowLite.h>
#include "tensorflow/lite/micro/all_ops_resolver.h"
#include "tensorflow/lite/micro/micro_error_reporter.h"
#include "tensorflow/lite/micro/micro_interpreter.h"
#include "tensorflow/lite/schema/schema_generated.h"
#include "tensorflow/lite/version.h"

const int tensor_arena_size = 2 * 1024;
uint8_t tensor_arena[tensor_arena_size];

tflite::MicroInterpreter* interpreter = nullptr;
float* input;
float* output;

const float expected_major = 1.0; // Adjust based on expected output
const float expected_minor = 0.0; // Adjust based on expected output

void setup() {
  Serial.begin(115200);

  if (!SPIFFS.begin(true)) {
    Serial.println("Failed to mount file system");
    while (true);  // Halt execution
  }

  File model_file = SPIFFS.open("/model.tflite", "r");
  if (!model_file) {
    Serial.println("Failed to open model file");
    while (true);  // Halt execution
  }

  size_t model_size = model_file.size();
  std::vector<uint8_t> model_buffer(model_size);
  model_file.read(model_buffer.data(), model_size);
  model_file.close();

  const tflite::Model* model = tflite::GetModel(model_buffer.data());
  if (model->version() != TFLITE_SCHEMA_VERSION) {
    Serial.println("Model schema version does not match");
    while (true);  // Halt execution
  }

  tflite::MicroErrorReporter micro_error_reporter;
  tflite::AllOpsResolver resolver;
  interpreter = new tflite::MicroInterpreter(model, resolver, tensor_arena, tensor_arena_size, &micro_error_reporter);

  if (interpreter->AllocateTensors() != kTfLiteOk) {
    Serial.println("Failed to allocate tensors");
    while (true);  // Halt execution
  }

  input = interpreter->input(0)->data.f;
  output = interpreter->output(0)->data.f;

  Serial.println("Setup completed successfully");
}

void loop() {
  // Set input values for testing
  input[0] = 0.025;
  input[1] = 0.0;

  if (interpreter->Invoke() != kTfLiteOk) {
    Serial.println("Invoke failed");
    while (true);  // Halt execution
  }

  // Print output values for manual verification
  float predicted_major = output[0] * 40.0;
  float predicted_minor = output[1] * 10.0;

  Serial.print("Predicted Major: ");
  Serial.println(predicted_major);
  Serial.print("Predicted Minor: ");
  Serial.println(predicted_minor);

  // Check if the output is within acceptable range
  if (abs(predicted_major - expected_major) < 0.1 && abs(predicted_minor - expected_minor) < 0.1) {
    Serial.println("Test Passed");
  } else {
    Serial.println("Test Failed");
  }

  // Halt after one iteration
  while (true);
}
