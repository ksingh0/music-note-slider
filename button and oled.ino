#include <Arduino.h>
#include <U8x8lib.h>
#define ledPin 4 // Big red LED on digital I/O 4
#define buttonPin 6 // Push button on digital I/O 6
int buttonState = 0;
int holder = 0;
auto display = U8X8_SSD1306_128X64_NONAME_HW_I2C(U8X8_PIN_NONE);
void setup() {
 Serial.begin(9600);
 display.begin();
 display.setFlipMode(0);
 display.clearDisplay();
 pinMode(ledPin, OUTPUT); // Sets the D4 pin (LED) to output
 pinMode(buttonPin, INPUT); // Sets the D6 pin (Button) to input
}

void loop() {
buttonState = digitalRead(buttonPin);
if (buttonState == HIGH && holder == 0) {
  digitalWrite(ledPin, HIGH);
  Serial.write("Button !\n");
  holder = 1;
}
else if (buttonState == HIGH)
{
  digitalWrite(ledPin, HIGH);
}
else if (buttonState == LOW) {
  digitalWrite(ledPin, LOW);
  holder = 0;
}
 display.setFont(u8x8_font_profont29_2x3_r);
 display.setCursor(0, 0);
 if (!Serial.available()) {
 return;
 }
 display.clear();
 display.print(Serial.readString());
}
