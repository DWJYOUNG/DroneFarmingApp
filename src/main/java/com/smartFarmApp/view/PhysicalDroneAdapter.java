package com.smartFarmApp.view;
import java.io.IOException;

import com.smartFarmApp.drone.*;

public class PhysicalDroneAdapter implements SimulatedDrone{

    

    @Override
    public void scanFarm() throws InterruptedException, IOException {
        TelloDrone tello = new TelloDrone();
        tello.activateSDK();
        tello.hoverInPlace(3);
		tello.takeoff();
		tello.increaseAltitude(Constants.FARMDEPTH/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
        tello.turnCW(90);
        tello.flyForward((Constants.FARMHEIGHT-Constants.DRONESIZE)/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
        tello.turnCCW(90);
        tello.flyForward((Constants.FARMWIDTH-Constants.DRONESIZE)/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
        tello.turnCCW(90);
        tello.flyForward((Constants.FARMHEIGHT-Constants.DRONESIZE)/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
        tello.turnCCW(90);
        
        for(int i = 0; i < 2; i++) {
			tello.flyForward(Constants.DRONESIZE/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
			tello.turnCCW(90);
			tello.flyForward((Constants.FARMHEIGHT-Constants.DRONESIZE)/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
			tello.turnCW(90); 
			tello.flyForward(Constants.DRONESIZE/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
			tello.turnCW(90);
			tello.flyForward((Constants.FARMHEIGHT-Constants.DRONESIZE)/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
			tello.turnCCW(90); 
		}
		
		tello.flyForward(Constants.DRONESIZE/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
		tello.turnCCW(180);
		
		tello.hoverInPlace(2);
		
        tello.land();
        tello.end();
    }

    @Override
    public void visitItem(int dispX, int dispY, int dispZ) throws InterruptedException, IOException {

		TelloDrone tello = new TelloDrone();
		tello.activateSDK();
		tello.hoverInPlace(3);
		tello.takeoff();
		tello.increaseAltitude(dispZ/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
    	
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

		tello.flyForward(dispX/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
		tello.turnCW(xRot*yRot*90);
		tello.flyForward(dispY/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);

		tello.turnCCW(360);
		tello.turnCCW(xRot*yRot*90);
		tello.hoverInPlace(2);

		tello.flyForward(dispX/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);
		tello.turnCW(xRot*yRot*90);
		tello.flyForward(dispY/Constants.PIXELS_TO_ONE_MODEL_FOOT*Constants.CENTIMETERS_PER_MODEL_FOOT);

		tello.turnCCW(xRot*yRot*90);

		// undoing the rotation done early
		if (xRot == -1) {
			tello.turnCW(180);
		}

		tello.hoverInPlace(2);

		tello.land();
		tello.end();
    }
}
