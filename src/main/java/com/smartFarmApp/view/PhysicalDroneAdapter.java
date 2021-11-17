package com.smartFarmApp.view;
import java.io.IOException;

import com.smartFarmApp.drone.*;

public class PhysicalDroneAdapter implements SimulatedDrone{

    

    @Override
    public void scanFarm() throws InterruptedException, IOException {
        TelloDrone tello = new TelloDrone();
        tello.activateSDK();
        tello.hoverInPlace(5);
        tello.takeoff();
        tello.turnCW(90);
        tello.flyForward((Constants.FARMHEIGHT-100)/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
        tello.turnCCW(90);
        tello.flyForward((Constants.FARMWIDTH-100)/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
        tello.turnCCW(90);
        tello.flyForward((Constants.FARMHEIGHT-100)/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
        tello.turnCCW(90);
        
        for(int i = 0; i < 3; i++) { 
			tello.flyForward(100); 
			tello.turnCCW(90);
			tello.flyForward((Constants.FARMHEIGHT-100)/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
			tello.turnCW(90); 
			tello.flyForward(100); 
			tello.turnCW(90);
			tello.flyForward((Constants.FARMHEIGHT-100)/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
			tello.turnCCW(90); 
		}
		
		tello.flyForward(100);
		tello.turnCCW(180);
		
		tello.hoverInPlace(2);
		
        tello.land();
        tello.end();
    }

    @Override
    public void visitItem(int dispX, int dispY) throws InterruptedException, IOException {
    	
    	TelloDrone tello = new TelloDrone();
    	
    	int xRot = 1;
		int yRot = 1;

		if (dispX < 0) {
			xRot = -1;
			// for forward movement when moving to the left
			tello.turnCCW(180);
		}
		if (dispY < 0) {
			yRot = -1;
		}

		tello.flyForward(dispX);
		tello.turnCW(xRot*yRot*90);
		tello.flyForward(dispY);

		tello.turnCCW(360);
		tello.turnCCW(xRot*yRot*90);
		tello.hoverInPlace(2);

		tello.flyForward(dispX);
		tello.turnCW(xRot*yRot*90);
		tello.flyForward(dispY);

		tello.turnCCW(xRot*yRot*90);

		// undoing the rotation done early
		if (xRot == -1) {
			tello.turnCW(180);
		}
    }
}
