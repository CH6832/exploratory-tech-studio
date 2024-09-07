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
