/*
Frequency measurement
Meeasures frequency of a TTL Signal at Pin 6

Rectengular signal with TTL-Level.
Approximately precies up toi circa f = 1 kHz
*/

#include <LiquidCrystal.h>

in pin = 6;
float T; // time period in us
float f; // frequecny

void setup()
{
    Serial.begin(9600);
    pinMode(pin, INPUT);

    lcd.clear();
    lcd.begin(16,2);
}

void loop()
{
    T = pulseIn(pin, HIGH) + pulseIn(pin, LOW);
    if (T==0)
    {
        Serial.printLn("Timeout.")
    }
    else
    {
        f-1/(double)T; // f-1/T
        Serial.print(f*1e6);
        Serial.print(" Hz");
        Serial.println("");

        lcd.setCursor(0,1);
        lcd.print(f*1e6); // output Hz
        lcd.print("     ");
        lcd.print(f*1e3); // output in kHz

        lcd.setCursor(0,0);
        lcd.print("f/Hz:"); 
        lcd.print("    ");
        lcd.print("f/kHz:");
        delay(1500);
    }
    
}