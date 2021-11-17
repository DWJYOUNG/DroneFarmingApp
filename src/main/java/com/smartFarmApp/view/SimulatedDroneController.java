package com.smartFarmApp.view;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import com.smartFarmApp.drone.Constants;

/**
 * the point of the concrete class is to make the animations with respect to distances and not aboslute position
 * since the physical drone uses distances for its flight commands
 * @author ikyuen
 *
 */
public class SimulatedDroneController implements SimulatedDrone {

	private ImageView drone;
	private SequentialTransition sequence;
	private int magicTimeMs = 1000;

	public SimulatedDroneController(ImageView droneImage) {
		drone = droneImage;
	}

	private void setup () {
		sequence = new SequentialTransition();
	}

	private void flyUp(int dist) {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(drone);
		translate.setDuration(Duration.millis(magicTimeMs));
		translate.setByY(-dist);
		sequence.getChildren().add(translate);
	}
	private void flyDown(int dist) {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(drone);
		translate.setDuration(Duration.millis(magicTimeMs));
		translate.setByY(dist);
		sequence.getChildren().add(translate);
	}
	private void flyLeft(int dist) {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(drone);
		translate.setDuration(Duration.millis(magicTimeMs));
		translate.setByX(-dist);
		sequence.getChildren().add(translate);
	}
	private void flyRight(int dist) {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(drone);
		translate.setDuration(Duration.millis(magicTimeMs));
		translate.setByX(dist);
		sequence.getChildren().add(translate);
	}

	private void turnCW (int angle) {
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(drone);
		rotate.setDuration(Duration.millis(magicTimeMs/4));
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.setByAngle(angle);
		sequence.getChildren().add(rotate);

	}

	private void turnCCW (int angle) {
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(drone);
		rotate.setDuration(Duration.millis(magicTimeMs/4));
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.setByAngle(-angle);
		sequence.getChildren().add(rotate);
	}

	private void hover (int time) {
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(drone);
		rotate.setDuration(Duration.millis(time));
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.setByAngle(0);
		sequence.getChildren().add(rotate);
	}

	private void playMovement() {
		sequence.play();
	}

	private void endMovement() {
		sequence.getChildren().clear();
	}


	public void scanFarm() {
		//SimulatedDroneController droneAnim = new SimulatedDroneController(image);

		this.setup();

		this.turnCW(90);
		this.flyDown(700);
		this.turnCCW(90);
		this.flyRight(500);
		this.turnCCW(90);
		this.flyUp(700);
		this.turnCCW(90);

		
		for(int i = 0; i < 2; i++) {
			this.flyLeft(100); 
			this.turnCCW(90);
			this.flyDown(700);
			this.turnCW(90); 
			this.flyLeft(100); 
			this.turnCW(90);
			this.flyUp(700);
			this.turnCCW(90); 
		}
		
		this.flyLeft(100);
		this.turnCCW(180);
		 
		this.playMovement();
		this.endMovement();
	}

	public void visitItem(int dispX, int dispY) {
		// yoinked comment from correct farm layout
		/*
		 *  Farm is 800px tall and 600px wide
		 *  Drone is 50x50
		 *  For now I am just going to subtract 50 from where I actually want to go on the (x, y)
		 *  ArcTo will be good for flying directly to a specified coordinate.
		 *
		 */
		this.setup();

		int xRot = 1;
		int yRot = 1;

		if (dispX < 0) {
			xRot = -1;
			// for forward movement when moving to the left
			this.turnCCW(180);
		}
		if (dispY < 0) {
			yRot = -1;
		}

		this.flyRight(dispX);
		this.turnCW(xRot*yRot*90);
		this.flyDown(dispY);

		this.turnCCW(360);
		this.turnCCW(xRot*yRot*90);
		this.hover(3000);

		this.flyLeft(dispX);
		this.turnCW(xRot*yRot*90);
		this.flyUp(dispY);

		this.turnCCW(xRot*yRot*90);

		// undoing the rotation done early
		if (xRot == -1) {
			this.turnCW(180);
		}

		this.playMovement();
		this.endMovement();
	}
}