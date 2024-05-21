#include <LiquidCrystal.h>

String userInput;
float factor = 1.35962;
float PS;

LiquidCrystal lcd (7, 8, 9, 10, 11, 12);

void setup()
{
  Serial.begin(9600);
  lcd.clear(); 
  lcd.begin(16, 2);
}

void loop()
{
  // query of kw via serial monitor
  Serial.println("Please enter KW here: ");  
  while (Serial.available() == 0){}  
  userInput = Serial.readString();

  // convert to horsepower
  float kW = userInput.toFloat();
  PS = kW * factor;

  // display resulot via ...
  // ... serial monitor
  String strInput = String(userInput);
  Serial.println(userInput+" KW are "+PS+" PS");

  // ... lcd
  lcd.setCursor(0,0); 
  String resultValue = String(PS);
  lcd.print(resultValue);
}
