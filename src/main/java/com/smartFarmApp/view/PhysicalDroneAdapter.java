package com.smartFarmApp.view;
 import main.java.surelyhuman.jdrone.control.physical.*;

public class PhysicalDroneAdapter implements SimulatedDrone{

    TelloDrone tello = new TelloDrone();

    @Override
    public void scanFarm() {
        tello.takeoff;
        tello.turnCW(90);
        tello.flyForward(FARMWIDTH/PIXELS_TO_ONE_MODEL_FOOT*CENTIMETERS_PER_MODEL_FOOT);
        tello.turnCCW(90);
        tello.flyForward(FARMHEIGHT/PIXELS_TO_ONE_MODEL_FOOT*CENTIMETERS_PER_MODEL_FOOT);
    }

    @Override
    public void visitItem(int dispX, int dispY) {
    }
}
