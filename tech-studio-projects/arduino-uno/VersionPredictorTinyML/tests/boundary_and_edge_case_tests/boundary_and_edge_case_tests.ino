#include <SPIFFS.h>
#include <TensorFlowLite.h>
#include "tensorflow/lite/micro/all_ops_resolver.h"
#include "tensorflow/lite/micro/micro_error_reporter.h"
#include "tensorflow/lite/micro/micro_interpreter.h"
#include "tensorflow/lite/schema/schema_generated.h"
#include "tensorflow/lite/version.h"

void loop() {
  // Test with maximum input values
  input[0] = 1.0;
  input[1] = 1.0;

  if (interpreter->Invoke() != kTfLiteOk) {
    Serial.println("Invoke failed");
    while (true);  // Halt execution
  }

  Serial.print("Max Predicted Major: ");
  Serial.println(output[0] * 40.0);
  Serial.print("Max Predicted Minor: ");
  Serial.println(output[1] * 10.0);

  // Test with minimum input values
  input[0] = 0.0;
  input[1] = 0.0;

  if (interpreter->Invoke() != kTfLiteOk) {
    Serial.println("Invoke failed");
    while (true);
  }

  Serial.print("Min Predicted Major: ");
  Serial.println(output[0] * 40.0);
  Serial.print("Min Predicted Minor: ");
  Serial.println(output[1] * 10.0);

  // Halt after one iteration
  while (true);
}
