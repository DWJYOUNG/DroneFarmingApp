package com.smartFarmApp.drone;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;

import com.smartFarmApp.drone.Constants;

public class StreamRecorder extends Thread{

	private static final int RECORD_LENGTH = 5000;
	private volatile boolean running = false;
	private int videoPort;

	public StreamRecorder(int videoPort) throws FileNotFoundException, SocketException {
		this.videoPort = videoPort;
	}

	public void run() {
		running = true;

		System.out.print("Recording" + '\n');

		while (running) {
			convertVideo(0, videoPort);
		}

	}

	public void endVideoRecord() throws IOException, InterruptedException {
		setRunning(false);
	}

	public int getVideoPort() {
		return videoPort;
	}

	@SuppressWarnings("unused")
	private void setVideoPort(int videoPort) {
		this.videoPort = videoPort;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	private void convertVideo(int n, int port) {
		FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("udp://@:" + port);
		try {
			grabber.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(Constants.QUALIFIED_PATH_COMPLETION + "/jdrone/src/VideoRecv" + n + ".mp4", 
				grabber.getImageWidth(), grabber.getImageHeight(), 0);
		try {
			recorder.start();
		} catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
			e.printStackTrace();
		}
		long t1 = System.currentTimeMillis();
		Frame frame;
		try {
			while ((frame = grabber.grabFrame()) != null) {
				recorder.record(frame);
				if ((System.currentTimeMillis() - t1) > RECORD_LENGTH) {
					grabber.close();
					recorder.close();
					convertVideo(n+1, port);
				}
			}
		} catch (Exception | org.bytedeco.javacv.FrameRecorder.Exception e) {
			e.printStackTrace();
		}
		try {
			grabber.close();
			recorder.close();
		} catch (org.bytedeco.javacv.FrameRecorder.Exception | Exception e) {
			e.printStackTrace();
		}
	}
}

