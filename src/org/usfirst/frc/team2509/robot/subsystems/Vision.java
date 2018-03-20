package org.usfirst.frc.team2509.robot.subsystems;

import java.util.ArrayList;

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
  //Outputs
  	private Mat hslThresholdOutput = new Mat();
  	private Mat cvErodeOutput = new Mat();
  	private ArrayList<MatOfPoint> findContoursOutput = new ArrayList<MatOfPoint>();

  	static {
  		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  	}
  	UsbCamera cam = RobotMap.cam;
  	CvSink sink = CameraServer.getInstance().getVideo();
  	CvSource outputStream = CameraServer.getInstance().putVideo("ALT-Cam", 160, 120);
	Mat input = new Mat();
	Mat cvErodeSrc = hslThresholdOutput;
	Mat cvErodeKernel = new Mat();
	Point cvErodeAnchor = new Point(-1, -1);
	double cvErodeIterations = 1.0;
	int cvErodeBordertype = Core.BORDER_CONSTANT;
	Scalar cvErodeBordervalue = new Scalar(-1);
	Mat kernel = new Mat();
	Point anchor = new Point(-1,-1);
	Scalar borderValue = new Scalar(-1);
	Mat findContoursInput = cvErodeOutput;
	Mat hierarchy = new Mat();
	Scalar RED = new Scalar(0, 0, 255);
	public Rect target;

  	/**
  	 * This is the primary method that runs the entire pipeline and updates the outputs.
  	 */
  	public void process() {
//  		findContoursOutput.clear();
  		sink.grabFrame(input);
  		Imgproc.cvtColor(input, hslThresholdOutput, Imgproc.COLOR_BGR2HLS);
//  		Core.inRange(hslThresholdOutput, new Scalar(0, 45, 120),new Scalar(50, 200, 247), hslThresholdOutput);
//  		Imgproc.erode(cvErodeSrc, cvErodeOutput, kernel, anchor, (int)cvErodeIterations, cvErodeBordertype, borderValue);
//  		Imgproc.findContours(findContoursInput, findContoursOutput, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
//  		for(MatOfPoint mop :findContoursOutput){
//				Rect rec = Imgproc.boundingRect(mop);
//				Imgproc.rectangle(input, rec.br(), rec.tl(), RED);
//			}
//			for(Iterator<MatOfPoint> iterator = findContoursOutput.iterator();iterator.hasNext();){
//				MatOfPoint matOfPoint = (MatOfPoint) iterator.next();
//				Rect rec = Imgproc.boundingRect(matOfPoint);
//				//float aspect = (float)rec.width/(float)rec.height;
//				//if( rec.height < 10 || rec.width < 5||rec.y<75/*||aspect<=1*/){
//				//	iterator.remove();
//				//continue;
//				//}
//				target = rec;
//				SmartDashboard.putNumber("Target Contours", findContoursOutput.size());
//				SmartDashboard.putNumber("Target X", rec.x);
//				SmartDashboard.putNumber("Target Width", rec.width);
//				SmartDashboard.putNumber("Target Height", rec.height);
//			}			
			outputStream.putFrame(input);  		
  	}
  	public Thread visionProcces = new Thread(()-> {
  		while(true) {
  			process();
  		}
  	});
  	public Rect getTarget() {
  		return target;
  	}
}

