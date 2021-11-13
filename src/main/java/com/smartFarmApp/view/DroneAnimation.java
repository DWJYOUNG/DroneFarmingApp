package com.smartFarmApp.view;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * the point of the concrete class is to make the animations with respect to distances and not aboslute position
 * since the physical drone uses distances for its flight commands
 * @author ikyuen
 *
 */
public class DroneAnimation implements DroneControl {

	private ImageView drone;
	private SequentialTransition sequence;
	private int magicTimeMs = 1000;

	public DroneAnimation(ImageView droneImage) {
		drone = droneImage;
	}

	private void setup () {
		sequence = new SequentialTransition();
	}

	private void moveUp(int dist) {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(drone);
		translate.setDuration(Duration.millis(magicTimeMs));
		translate.setByY(-dist);
		sequence.getChildren().add(translate);
	}
	private void moveDown(int dist) {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(drone);
		translate.setDuration(Duration.millis(magicTimeMs));
		translate.setByY(dist);
		sequence.getChildren().add(translate);
	}
	private void moveLeft(int dist) {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(drone);
		translate.setDuration(Duration.millis(magicTimeMs));
		translate.setByX(-dist);
		sequence.getChildren().add(translate);
	}
	private void moveRight(int dist) {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(drone);
		translate.setDuration(Duration.millis(magicTimeMs));
		translate.setByX(dist);
		sequence.getChildren().add(translate);
	}

	private void rotateCCW (int angle) {
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(drone);
		rotate.setDuration(Duration.millis(magicTimeMs/4));
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.setByAngle(angle);
		sequence.getChildren().add(rotate);

	}

	private void rotateCW (int angle) {
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
		//DroneAnimation droneAnim = new DroneAnimation(image);

		this.setup();

		this.rotateCCW(90);
		this.moveDown(600);
		this.rotateCW(90);
		this.moveRight(800);
		this.rotateCW(90);
		this.moveUp(600);
		this.rotateCW(90);


		for(int i = 0; i < 4; i++) {
			this.moveLeft(100);
			this.rotateCW(90);
			this.moveDown(500);
			this.rotateCCW(90);
			this.moveLeft(100);
			this.rotateCCW(90);
			this.moveUp(500);
			this.rotateCW(90);
		}

		this.rotateCW(180);

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
			this.rotateCW(180);
		}
		if (dispY < 0) {
			yRot = -1;
		}

		this.moveRight(dispX);
		this.rotateCCW(xRot*yRot*90);
		this.moveDown(dispY);

		this.rotateCW(360);
		this.rotateCW(xRot*yRot*90);

		this.moveLeft(dispX);
		this.rotateCCW(xRot*yRot*90);
		this.moveUp(dispY);

		this.rotateCW(xRot*yRot*90);

		// undoing the rotation done early
		if (xRot == -1) {
			this.rotateCCW(180);
		}

		this.playMovement();
		this.endMovement();
	}
}