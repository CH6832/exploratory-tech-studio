/*

This Arduino code reads an analog voltage from pin A0, converts it to a voltage value, and then displays
it on an LCD screen and sends it to the serial monitor. Additionally, it controls multiple LEDs based
on the voltage level and triggers a buzzer if the voltage falls within a specific range.

What the code does:
* It initializes a LiquidCrystal object for controlling an LCD display.
* In the setup function, it sets up the serial communication, initializes the LCD display, and sets the pins 2 to 6 as output pins.
* In the loop function, it reads the analog value from pin A0, converts it to a voltage value, and displays it on the LCD screen and sends it to the serial monitor.
* It also controls LEDs connected to pins 2 to 6 based on the voltage level: each LED turns on if the voltage is higher than a certain threshold.
* Finally, it triggers a buzzer connected to pin 9 if the voltage falls within a specific range (between 3.25V and 3.35V).

*/

#include <LiquidCrystal.h>

LiquidCrystal lcd(7, 8, 9, 10, 11, 12); 
 
float analogWert; // variable for the analog value
float voltAnzeige;

const int buzzer = 9;

void setup() 
{
  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
   
  Serial.begin(9600);
  lcd.clear();       
  lcd.begin(16, 2); 
}


void loop() 
{
  analogWert = analogRead(A0);
  voltAnzeige = (analogWert/1024)*5;

  lcd.setCursor(0,0); 
  lcd.print("U an A0:");
  lcd.setCursor(0,1);
  lcd.print(voltAnzeige);
  lcd.setCursor(5,1);
  lcd.print("V");
  
  Serial.print(voltAnzeige);
  Serial.print(" V");
  Serial.println("");
  delay(500); 

   
  if (voltAnzeige > 0) {  digitalWrite( 2, HIGH);} else { digitalWrite(2, LOW); }
  if (voltAnzeige > 1) {  digitalWrite( 3, HIGH);} else { digitalWrite(3, LOW); }
  if (voltAnzeige > 2) {  digitalWrite( 4, HIGH);} else { digitalWrite(4, LOW); }
  if (voltAnzeige > 3) {  digitalWrite( 5, HIGH);} else { digitalWrite(5, LOW); }
  if (voltAnzeige > 4) {  digitalWrite( 6, HIGH);} else { digitalWrite(6, LOW); }

  if (voltAnzeige > 3.25 && voltAnzeige < 3.35)
  {
    tone(buzzer, 5000);
  }
}
