#include <LiquidCrystal.h>

const int pin = 6;
float T;
float f;

LiquidCrystal lcd(7, 8, 9, 10, 11, 12);

void setup()
{
    Serial.begin(9600);
    pinMode(pin, INPUT);

    lcd.clear();
    lcd.begin(16, 2);
}

void loop()
{
    T = pulseIn(pin, HIGH) + pulseIn(pin, LOW);

    if (T == 0)
    {
        Serial.println("Timeout.");
    }
    else
    {
        f = 1 / (T * 1e-6);

        Serial.print(f);
        Serial.println(" Hz");

        lcd.setCursor(0, 0);
        lcd.print("f/Hz: ");
        lcd.print(f);
        lcd.print("    ");

        lcd.setCursor(0, 1);
        lcd.print("f/kHz: ");
        lcd.print(f / 1000.0);
        lcd.print("    ");

        delay(1500);
    }
}
