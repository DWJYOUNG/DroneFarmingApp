package com.smartFarmApp.view;
 import main.java.surelyhuman.jdrone.control.physical.*;

public class PhysicalDroneAdapter implements SimulatedDrone{

    TelloDrone tello = new TelloDrone();

    @Override
    public void scanFarm() {
        tello.takeoff;
        tello.turnCW(90);
        // use small values first to make testing much easier -- 30 and 5 have no actual significance, are for testing only
        tello.flyForward(30);
        //tello.flyForward(FARMWIDTH/PIXELS_TO_ONE_MODEL_FOOT*CENTIMETERS_PER_MODEL_FOOT);
        hoverInPlace(5);
        tello.turnCCW(90);
        tello.flyForward(30);
        //tello.flyForward(FARMHEIGHT/PIXELS_TO_ONE_MODEL_FOOT*CENTIMETERS_PER_MODEL_FOOT);
        tello.land();
    }

    @Override
    public void visitItem(int dispX, int dispY) {
    }
}
