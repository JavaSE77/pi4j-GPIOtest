package edu.cs.ship.rs0699.gpiojavatest;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class SensorHandler {
  
  private GpioController gpio;
  private Pin pin;
  private String name;
  private Long time = System.currentTimeMillis();

  public SensorHandler(GpioController gpio, Pin pin, String name) {
    this.gpio = gpio;
    this.pin = pin;
    this.name = name;
  }
  
  /**
   * sets up the pin and registers a listener for it. 
   * This does not need it's own thread. Thank goodness
   * */
  public void setupPin() {
    // provision gpio pin as an input pin with its internal pull down resistor enabled
    final GpioPinDigitalInput sensor = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_UP);

    // set shutdown state for this input pin
    sensor.setShutdownOptions(true);

    // create and register gpio pin listener
    sensor.addListener(new GpioPinListenerDigital() {
        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
          if(event.getPin().toString().contains(pin.toString())) {
            // display pin state on console
            System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState() + " time: " 
            + System.currentTimeMillis() + " sensor name: " + name);
            
            //State is LOW (meaning beam has been broken)
            if(event.getState().toString().contains("LOW")) {
              time = System.currentTimeMillis();
            } else {
              //state is not LOW, meaning the beam has been restored
              System.out.println(name + " changed " + (System.currentTimeMillis() - time) + "ms ago");
            }
            
          }
        }

    });

    System.out.println(" ... complete the GPIO " + pin.getName() + " circuit and see the listener feedback here in the console.");

  }
  
}
