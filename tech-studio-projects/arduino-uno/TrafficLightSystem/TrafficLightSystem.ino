#include <LiquidCrystal.h>
LiquidCrystal lcd (7, 8, 9, 10, 11, 12); // usable lcd-pins

int rotAuto = 2;
int gelbAuto = 3;
int gruenAuto = 4;

int rotFuss = 13;
int gruenFuss = 6;

int ledFuss = 13;
int tasteFuss = A5;
int buzzer = A4;

void setup()
{
  lcd.clear();
  lcd.begin(16, 2);

  lcd.setCursor(0,0);
  lcd.print("Ampelschaltung");

  pinMode(rotAuto, OUTPUT);
  pinMode(gelbAuto, OUTPUT);
  pinMode(gruenAuto, OUTPUT);

  pinMode(rotFuss, OUTPUT);
  pinMode(gruenFuss, OUTPUT);
 
  pinMode(tasteFuss, INPUT_PULLUP);
  pinMode(ledFuss, OUTPUT);
  pinMode(buzzer, OUTPUT);
}

void loop()
{
  digitalWrite(rotAuto,LOW);
  digitalWrite(gelbAuto,LOW);
  digitalWrite(gruenAuto,HIGH);

  digitalWrite(gruenFuss, LOW);
  digitalWrite(rotFuss, HIGH);

  lcd.setCursor(0,1);
  lcd.print("Autofahrer-Phase");

  if(digitalRead(tasteFuss) == LOW)
  {
    digitalWrite(ledFuss, HIGH);
    delay(2000);
    digitalWrite(ledFuss, LOW);
    delay(2000);
    digitalWrite(ledFuss, HIGH);
    delay(2000);
    digitalWrite(ledFuss, LOW);
    delay(2000);
    digitalWrite(ledFuss, HIGH);
    delay(2000);
    digitalWrite(ledFuss, LOW);
    delay(2000);
    digitalWrite(ledFuss, HIGH);
    delay(2000);
    digitalWrite(ledFuss, LOW);
    delay(2000);
    digitalWrite(ledFuss, HIGH);
    delay(2000);
    digitalWrite(ledFuss, LOW);
    delay(2000);    
    tone(buzzer, 5000);

    lcd.setCursor(0,1);
    lcd.print("Fussgaengerphase");

    digitalWrite(rotAuto,HIGH);
    digitalWrite(gruenAuto,LOW);
    delay(2000);
    digitalWrite(rotAuto,LOW);
    digitalWrite(gruenAuto,HIGH);
    delay(10000);

    digitalWrite(gruenFuss,LOW);
    digitalWrite(gelbAuto,HIGH);
    digitalWrite(rotFuss,HIGH);
    delay(2000);
  }
}
