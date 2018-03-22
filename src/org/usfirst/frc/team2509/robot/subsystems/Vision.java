package org.usfirst.frc.team2509.robot.subsystems;

import java.util.ArrayList;
import java.util.Iterator;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2509.robot.RobotMap;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
  	private UsbCamera cam = RobotMap.cam;
  	private CvSink sink = CameraServer.getInstance().getVideo();
  	private CvSource outputStream = CameraServer.getInstance().putVideo("ALT-Cam", 160, 120);
  	private ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
  	private Mat thresh = new Mat();
  	private Mat erode = new Mat();
  	private Mat input = new Mat();
	private Mat kernel = new Mat();
	private Mat hierarchy = new Mat();
	private Mat output = new Mat();
	private Point anchor = new Point(-1,-1);
	private double erodeIterations = 1.0;
	private Scalar RED = new Scalar(0, 0, 255);
	private Rect target = new Rect();
	private int imgWidth = 1;
	private int imgHeight = 1;
  	/**
  	 * This is the primary method that runs the entire pipeline and updates the outputs.
  	 */
  	public void filter() {
  		contours.clear();
  		sink.grabFrame(input);//Grabs frame from the 
  		//Threshold
//  		Imgproc.cvtColor(input, thresh, Imgproc.COLOR_BGR2HLS);//HSL
  		Imgproc.cvtColor(input, thresh, Imgproc.COLOR_BGR2HSV);//HSV
//  		Core.inRange(thresh, new Scalar(0, 45, 120),new Scalar(50, 200, 247), thresh);//HSL
  		Core.inRange(thresh, new Scalar(0, 45, 155),new Scalar(50, 200, 255), thresh);//HSV
  		//Erode
//  		Imgproc.erode(thresh, erode, kernel, anchor, (int)erodeIterations, Core.BORDER_CONSTANT, borderValue);
  		Imgproc.erode(thresh, erode, kernel, anchor, (int)erodeIterations);
  		//Find Contours
  		Imgproc.findContours(erode, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
  		//Filter Contours
  		for(MatOfPoint mop :contours){
				Rect rec = Imgproc.boundingRect(mop);
				Imgproc.rectangle(input, rec.br(), rec.tl(), RED);
		}
		for(Iterator<MatOfPoint> iterator = contours.iterator();iterator.hasNext();){
			MatOfPoint matOfPoint = (MatOfPoint) iterator.next();
			Rect rec = Imgproc.boundingRect(matOfPoint);
			//double aspect = rec.width/rec.height;
			//if( rec.height < 10 || rec.width < 5||rec.y<75/*||aspect<=1*/){
			//	iterator.remove();
			//continue;
			//}
			target = rec;
		}
		imgWidth = input.width();
		imgHeight = input.height();
		SmartDashboard.putNumber("Target Contours", contours.size());
		SmartDashboard.putNumber("Target X", target.x);
		SmartDashboard.putNumber("Target Width", target.width);
		SmartDashboard.putNumber("Target Height", target.height);
		//Resize For Stream
		Imgproc.resize(input, output, new Size(160,120));
		outputStream.putFrame(output);  		
  	}
  	public Thread process = new Thread(()-> {
  		while(true) {
  			filter();
  		}
  	});
  	public Rect getTarget() {
  		return target;
  	}
  	public int getWidth() {
  		return imgWidth;
  	}
  	public int getHeight() {
  		return imgHeight;
  	}
  	public double getCenterX() {
  		return (imgWidth/2);
  	}
  	public double getCenterY() {
  		return (imgHeight/2);
  	}
}

