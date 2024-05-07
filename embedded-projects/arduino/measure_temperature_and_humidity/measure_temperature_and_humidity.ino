/*
Messung von Temperatur und Luftfeuchtigkeit
*/

#include <Adafruit_Sensor.h>
#include <LiquidCrystal.h>
#include <DHT.h>

DHT dht(2, DHT11); //Das Objekt dht vom Typ DHT

int pin4 = 4; //blau
int pin5 = 5; //grün

int pin6 = 6; //rot

void setup() 
{
   dht.begin(); //Start der Kommunikation mit DHT11
   pinMode(4,OUTPUT);
   pinMode(5,OUTPUT);
   pinMode(6,OUTPUT);
}

void loop()
{
    delay(2000);                            
    int t = dht.readTemperature(); //Temperatur messen
    if(t>=21)
    {
      digitalWrite(pin6, HIGH);
      delay(2000);
      digitalWrite(pin6, LOW);
    }
    else if(t<=20 && t>=19)
    {
      digitalWrite(pin5, HIGH);
      delay(2000);
      digitalWrite(pin5, LOW);      
    }
    else if(t>=18)
    {
      digitalWrite(pin4, HIGH);
      delay(2000);
      digitalWrite(pin4, LOW);      
    }  
}

 
