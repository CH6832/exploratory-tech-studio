
/*
Ampelschaltung_Vorbereitung
*/

#include <LiquidCrystal.h>
LiquidCrystal lcd (7, 8, 9, 10, 11, 12); // usable lcd-pins

// Auto-Ampel
int rotAuto = 2;
int gelbAuto = 3;
int gruenAuto = 4;

//Fusssgaenger-Ampel
int rotFuss = 13;
int gruenFuss = 6;

// Ausloesung der Fussgaengerampel
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
  //Auto-Ampel auf Gruen schalten
  digitalWrite(rotAuto,LOW);
  digitalWrite(gelbAuto,LOW);
  digitalWrite(gruenAuto,HIGH);

  //Fussgaengerampel auf Rot schalten
  digitalWrite(gruenFuss, LOW);
  digitalWrite(rotFuss, HIGH);

  lcd.setCursor(0,1);
  lcd.print("Autofahrer-Phase");

  if(digitalRead(tasteFuss) == LOW)
  {
    //Signalabgeber Fussgaengerschaltung aktiviert

    //...Hier soll Code eingefuegt werdenso,...
    //dass zunächst die Anzeige-LED (ledFuss) fünfmal blinkt...
    //und dann der Buzzer den Vorgang mit einem Ton meldet...
    //...  
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

    //Auto-Ampel zuerst auf Rot, dann erst (!)Fussgänger-Ampel auf Gruen schalten
    lcd.setCursor(0,1);
    lcd.print("Fussgaengerphase");

    //Weiteren Code einfuegen...
    //Erst Auto-Ampel auf Rot, dann 2 Sekunden warten...
    //dann erst Fussgaengerampel auf Gruen...
    //10 Sekunden Zeit fuer die Fussgaenger...
    digitalWrite(rotAuto,HIGH);
    digitalWrite(gruenAuto,LOW);
    delay(2000);
    digitalWrite(rotAuto,LOW);
    digitalWrite(gruenAuto,HIGH);
    delay(10000);

    //Phasenwechsel auf Autophase ankuendigen
    digitalWrite(gruenFuss,LOW);
    digitalWrite(gelbAuto,HIGH);
    digitalWrite(rotFuss,HIGH);
    delay(2000);
  }
}
