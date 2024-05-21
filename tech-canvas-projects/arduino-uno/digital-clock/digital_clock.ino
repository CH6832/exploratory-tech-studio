/*
Digital clock with the ability to set the current time,
outputting hours and minutes in the hh:mm format.
*/

#include <TimeLib.h>
#include <LiquidCrystal.h>

 
int st = 0;
int m = 0;
int s = 0; 
int t = 13;
int mo = 5;
int j = 2019;

int tasterSet = 5;  
int tasterHoch = 4;
int tasterRunter = 3;

LiquidCrystal lcd(7,8,9,10,11,12); 

void setup()
{
   pinMode(tasterHoch, INPUT);           
   pinMode(tasterHoch, INPUT);           
   pinMode(tasterSet,  INPUT);           

   digitalWrite(tasterHoch, HIGH);        
   digitalWrite(tasterRunter, HIGH);         
   digitalWrite(tasterSet, HIGH);         

   lcd.begin(16,2);                  

   setzeStunde();                         
   delay(300);                            
   setzeMinute();                         
                 
   setTime(st,m,s,t,mo,j);                                                  
}

void loop()
{
    st = hour();                            
    m = minute();                           
    delay(2000);                            

    lcd.setCursor(0,0);                 
    lcd.print("Zeit:   ");               
    lcd.print(st);                       
    lcd.print(":");                      
  
    if (m<10)                            
    {                                    

        lcd.print("0");                  gkeit der Minutenanzeige
    }                                    
    lcd.print(m);                       
    lcd.print("    ");                   
    lcd.setCursor(0,1);                  
    lcd.print("Hier Temp/Feucht");       wir später Temperatur und Feuchtigkeit aus                  
}

void setzeStunde()                                          
{      

    while(digitalRead(tasterSet) == HIGH)                  
    SET" nicht betätigt"                                                    
    {   
        lcd.setCursor(0,0);                                                                                         
        lcd.print("Zeit eingeben!");                      
        lcd.setCursor(0,1);                                
        lcd.print("Stunde: ");                              
        lcd.print(st);                                      
        lcd.print("       ");                               

         if(digitalRead(tasterHoch)==LOW)                     
        {                                                   

            st = st+1;                                      
        }                                                   

         if(digitalRead(tasterRunter)==LOW)                 
        {                                                   

            st = st-1;                                      
        }                                                   
 
        if(st>23)                                           
        {                                                   

            st = 0;                                         
        }                                                  
                                                          

        if(st<0)                                                                      
        {                                                   
          
            st = 23;                                       
        }                                                  
        delay(200);                                
    }

}
 void setzeMinute()                                          

{      
     while(digitalRead(tasterSet) == HIGH)                  
     SET" nicht betätigt"
    {   
    
        lcd.setCursor(0,0);                               
        lcd.print("Zeit eingeben!");                      
        lcd.setCursor(0,1);                                 
        lcd.print("Minute: ");                             
        lcd.print(m);                                       
        lcd.print("       ");                               

        if(digitalRead(tasterHoch)==LOW)                    
        {                                                  
            m = m+1;                                        
        }                                                  
 
        if(digitalRead(tasterRunter)==LOW)                 
        {                                                   
            m = m-1;                                        
        }                                                  

        if(m>59)                                           
        {                                                  
            m = 0;                                          
        }                                                  
                                                       
        if(m<0)                                             
        {                                                  
            m = 59;                                        
        }                                                  

        delay(200);                                 
    }

} 
