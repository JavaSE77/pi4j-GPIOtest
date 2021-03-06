package edu.cs.ship.rs0699.gpiojavatest;
/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  Pi4J
 * PROJECT       :  Pi4J :: Java Examples
 * FILENAME      :  ListenGpioExample.java
 *
 * This file is part of the Pi4J project. More information about
 * this project can be found here:  https://www.pi4j.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2012 - 2019 Pi4J
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * This example code demonstrates how to setup a listener
 * for GPIO pin state changes on the Raspberry Pi.
 *
 * @author Robert Savage
 */
public class Main {

    public static void main(String args[]) throws InterruptedException {
        System.out.println("<--Pi4J--> GPIO Listen Example ... started.");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        
        SensorHandler sensorA = new SensorHandler(gpio, RaspiPin.GPIO_25, "Sensor 1");
        sensorA.setupPin();   
        
        SensorHandler sensorB = new SensorHandler(gpio, RaspiPin.GPIO_24, "Sensor 2");
        sensorB.setupPin();     
        
        SensorHandler sensorC = new SensorHandler(gpio, RaspiPin.GPIO_23, "Sensor 3");
        sensorC.setupPin();
        

//        // provision gpio pin #25 as an input pin with its internal pull down resistor enabled
//        final GpioPinDigitalInput sensorA = gpio.provisionDigitalInputPin(RaspiPin.GPIO_25, PinPullResistance.PULL_UP);
//
//        // set shutdown state for this input pin
//        sensorA.setShutdownOptions(true);
//
//        // create and register gpio pin listener
//        sensorA.addListener(new GpioPinListenerDigital() {
//            @Override
//            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//                // display pin state on console
//                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState() + " time: " + System.nanoTime());
//            }
//
//        });
//
//        System.out.println(" ... complete the GPIO #25 circuit and see the listener feedback here in the console.");
//
        // keep program running until user aborts (CTRL-C)
        while(true) {
            Thread.sleep(500);
        }

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller
    }
}
