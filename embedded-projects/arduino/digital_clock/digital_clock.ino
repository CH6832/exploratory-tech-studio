/* Digitaluhr mit Setzmöglichkeit der aktuellen Zeit, Ausgabe von Stunden, Minuten im hh:mm Format */

#include <TimeLib.h>                      // Zeit-Bibliothek
#include <LiquidCrystal.h>                // LCD-Bibliothek

 

int st = 0;                               // Stundenangabe
int m = 0;                                // Minutenangabe
int s = 0;                                // Sekundenangabe
int t = 13;                               // Tagesangabe
int mo = 5;                               // Monatsangabe
int j = 2019;                             // Jahresangabe


int tasterSet = 5;                        // Taster "Set"
int tasterHoch = 4;                       // Taster "Hoch"
int tasterRunter = 3;                     // Taster "Runter"


 

LiquidCrystal lcd(7,8,9,10,11,12); 


void setup() {

   pinMode(tasterHoch, INPUT);           
   pinMode(tasterHoch, INPUT);           
   pinMode(tasterSet,  INPUT);           

   
   digitalWrite(tasterHoch, HIGH);        
   digitalWrite(tasterRunter, HIGH);      // Pull up 
   digitalWrite(tasterSet, HIGH);         

 

   lcd.begin(16,2);               // LCD 16x2
   

   setzeStunde();                         // Stunden einstellen
   delay(300);                            // Kleine Wartezeit zwischen Stunden- und Minuteneinstellung
   setzeMinute();                         // Minuten einstellen

                          
   setTime(st,m,s,t,mo,j);                // Einstellung der Zeit
                                         
}

 

void loop() {

 

    st = hour();                            // Stunde aktualisieren
    m = minute();                           // Minute aktualisieren
    delay(2000);                            

    lcd.setCursor(0,0);                 
    lcd.print("Zeit:   ");               
    lcd.print(st);                       
    lcd.print(":");                      
  

    if (m<10)                            
    {                                    

        lcd.print("0");                  //Zweistelligkeit der Minutenanzeige
    }                                    

    lcd.print(m);                       
    lcd.print("    ");                   

    lcd.setCursor(0,1);                  
    lcd.print("Hier Temp/Feucht");      //Hier geben wir später Temperatur und Feuchtigkeit aus                  
    
}

 



//Setzen der aktuellen Stunde
 
void setzeStunde()                                          
{      

    while(digitalRead(tasterSet) == HIGH)                   // Solange "SET" nicht betätigt
                                                           
    {   
    
        lcd.setCursor(0,0);                                                                                         
        lcd.print("Zeit eingeben!");                      
        lcd.setCursor(0,1);                                
        lcd.print("Stunde: ");                              
        lcd.print(st);                                      
        lcd.print("       ");                               

         if(digitalRead(tasterHoch)==LOW)                    // Wenn Taster "Rauf" betätigt wird 
        {                                                   

            st = st+1;                                      // Erhöhe die Stunde um 1

        }                                                   

         if(digitalRead(tasterRunter)==LOW)                 // Wenn Taster "Runter" betätigt wird
        {                                                   

            st = st-1;                                      // Verringere die Stunde um 1

        }                                                   
 
        if(st>23)                                           
        {                                                   

            st = 0;                                         //Bei Erreichen von st = 24 Rücks. auf st = 0 

        }                                                  
                                                          

        if(st<0)                                            //Vermeidung negativer Stunden                           
        {                                                   
          
            st = 23;                                       

        }                                                  

        delay(200);                                
    }

}

//Setzen der aktuellen Minute
 void setzeMinute()                                          

{      
     while(digitalRead(tasterSet) == HIGH)                    // solange "SET" nicht betätigt

    {   
    
        lcd.setCursor(0,0);                               
        lcd.print("Zeit eingeben!");                      
        lcd.setCursor(0,1);                                 
        lcd.print("Minute: ");                             
        lcd.print(m);                                       
        lcd.print("       ");                               

        if(digitalRead(tasterHoch)==LOW)                     // Wenn Taste "Hoch" betätigt wird
        {                                                  

            m = m+1;                                        // Erhöhe die Minute um 1
        }                                                  
 
        if(digitalRead(tasterRunter)==LOW)                  // Wenn Taste "Runter" gedrückt wird
        {                                                   

            m = m-1;                                        // Verringere die Minute um 1
        }                                                  

        if(m>59)                                           
        {                                                  

            m = 0;                                          // bei Erreichen von m = 60 Rücks. auf m = 0

        }                                                  
                                                       

        if(m<0)                                             // Vermeidung negativer Minuten 
        {                                                  

            m = 59;                                        

        }                                                  
     

        delay(200);                                 
    }

} 
