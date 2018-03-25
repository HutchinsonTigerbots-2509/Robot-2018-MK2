package org.usfirst.frc.team2509.robot.subsystems;

import java.util.ArrayList;
import java.util.Iterator;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
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
  	private CvSink cvsink = CameraServer.getInstance().getVideo();
  	private CvSource outputStream = CameraServer.getInstance().putVideo("ALT-Cam", 160, 120);
  	private ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
  	private Mat thresh = new Mat();
  	private Mat erode = new Mat();
  	private Mat source = new Mat();
	private Mat kernel = new Mat();
	private Mat hierarchy = new Mat();
	private Mat output = new Mat();
	private Point anchor = new Point(-1,-1);
	private double erodeIterations =2.0;
	private Scalar RED = new Scalar(0, 0, 255);
	private Rect target = new Rect();
	private int imgWidth = 1;
	private int imgHeight = 1;
	
	private final CvSource 
	OUTPUT_STREAM = CameraServer.getInstance().putVideo("ALT-Cam", 640, 480);
private final Mat
	BINARY = new Mat(),
	CLUSTERS = new Mat(),
	HEIRARCHY = new Mat(),
	HSV = new Mat(),
	SOURCE = new Mat(),
	THRESH = new Mat();
protected final Scalar 
//COLOR VALUES
	BLACK = new Scalar(0,0,0),
	BLUE = new Scalar(255, 0, 0),
	GREEN = new Scalar(0, 255, 0),
	YELLOW = new Scalar(0, 255, 255),
	//Thresholds values
	LOWER_BOUNDS = new Scalar(180,190,40),
	UPPER_BOUNDS = new Scalar(200,210,60);
	
	
	
  	/**
  	 * This is the primary method that runs the entire pipeline and updates the outputs.
  	 */
	public Thread oldVision = new Thread(()-> {
		while(true){
			contours.clear();
			cvsink.grabFrame(SOURCE);
			Imgproc.cvtColor(SOURCE, HSV, Imgproc.COLOR_BGR2RGB);
			Imgproc.threshold(HSV, BINARY, 180, 190, Imgproc.THRESH_BINARY_INV);	
			Imgproc.cvtColor(BINARY, THRESH, Imgproc.COLOR_HSV2BGR);
			Imgproc.cvtColor(THRESH, CLUSTERS, Imgproc.COLOR_BGR2GRAY);
			Mat GRAY = CLUSTERS;
			Imgproc.Canny(GRAY, HEIRARCHY, 2, 4);
			Imgproc.findContours(HEIRARCHY, contours, new Mat(),Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
	        for(MatOfPoint mop :contours){
				Rect rec = Imgproc.boundingRect(mop);
				Imgproc.rectangle(SOURCE, rec.br(), rec.tl(), RED);
			}
			for(Iterator<MatOfPoint> iterator = contours.iterator();iterator.hasNext();){
				MatOfPoint matOfPoint = (MatOfPoint) iterator.next();
				Rect rec = Imgproc.boundingRect(matOfPoint);
				//float aspect = (float)rec.width/(float)rec.height;
				if( rec.height < 8||rec.height>25){
					iterator.remove();
					continue;
				}
				target = rec;
				SmartDashboard.putNumber("S_Contours", contours.size());
				SmartDashboard.putNumber("S_X", rec.x);
				SmartDashboard.putNumber("S_Width", rec.width);
				SmartDashboard.putNumber("S_Height", rec.height);
			}			
			OUTPUT_STREAM.putFrame(SOURCE);
		}
	});
  	public void filter() {
  		contours.clear();
//  		sink.setSource(cam);
  		cvsink.grabFrame(source);//Grabs frame from the 	
  		if(!source.empty()) {
	  		//Threshold
	////  		Imgproc.cvtColor(input, thresh, Imgproc.COLOR_BGR2HLS);//HSL
  			Imgproc.cvtColor(source, thresh, Imgproc.COLOR_BGR2HSV);
	////  		Core.inRange(thresh, new Scalar(0, 45, 120),new Scalar(50, 200, 247), thresh);//HSL
	  		Core.inRange(thresh, new Scalar(0, 45, 155),new Scalar(50, 200, 255), thresh);//HSV
	//  		//Erode
	////  		Imgproc.erode(thresh, erode, kernel, anchor, (int)erodeIterations, Core.BORDER_CONSTANT, borderValue);
	  		Imgproc.erode(thresh, erode, kernel, anchor, (int)erodeIterations);
//	  		Imgproc.Canny(thresh, erode, 2, 4);
	  		//Find Contours
	  		Imgproc.findContours(erode, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
	//  		//Filter Contours
	  		for(MatOfPoint mop :contours){
					Rect rec = Imgproc.boundingRect(mop);
					Imgproc.rectangle(source, rec.br(), rec.tl(), RED);
			}
			for(Iterator<MatOfPoint> iterator = contours.iterator();iterator.hasNext();){
				MatOfPoint matOfPoint = (MatOfPoint) iterator.next();
				Rect rec = Imgproc.boundingRect(matOfPoint);
				//double aspect = rec.width/rec.height;
				if( rec.height < 25 || rec.width < 25||rec.x<20||rec.x>100){//Max 50
					iterator.remove();
					continue;
				}
				target = rec;
				Imgproc.rectangle(source, target.br(), target.tl(), BLUE);
			}
			imgWidth = source.width();
			imgHeight = source.height();
	
			//Resize For Stream
//			Imgproc.resize(input, output, new Size(160,120));
			outputStream.putFrame(source);  
		}		
  	}
  	public Thread process = new Thread(()-> {
  		while(true) {
  			filter();
//  			cvsink.grabFrameNoTimeout(source);//Grabs frame from the 	
//  			if(!source.empty()) {
//  	  		System.out.println(source.width());
//  	  		Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
//  			outputStream.putFrame(output);  }

//  	  		System.out.println(source.width());
  			SmartDashboard.putNumber("Target Contours", contours.size());
  			SmartDashboard.putNumber("Target X", target.x);
  			SmartDashboard.putNumber("Target Y", target.y);
  			SmartDashboard.putNumber("Target Width", target.width);
  			SmartDashboard.putNumber("Target Height", target.height);
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

