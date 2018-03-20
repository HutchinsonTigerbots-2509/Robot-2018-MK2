package org.usfirst.frc.team2509.robot.subsystems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

  	/**
  	 * This is the primary method that runs the entire pipeline and updates the outputs.
  	 */
  	public void process() {
  		findContoursOutput.clear();
  		sink.grabFrame(input);
  		Imgproc.cvtColor(input, hslThresholdOutput, Imgproc.COLOR_BGR2HLS);
  		Core.inRange(hslThresholdOutput, new Scalar(0, 45, 120),new Scalar(50, 200, 247), hslThresholdOutput);
  		Imgproc.erode(cvErodeSrc, cvErodeOutput, kernel, anchor, (int)cvErodeIterations, cvErodeBordertype, borderValue);
  		Imgproc.findContours(findContoursInput, findContoursOutput, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
  		for(MatOfPoint mop :findContoursOutput){
				Rect rec = Imgproc.boundingRect(mop);
				Imgproc.rectangle(input, rec.br(), rec.tl(), RED);
			}
			for(Iterator<MatOfPoint> iterator = findContoursOutput.iterator();iterator.hasNext();){
				MatOfPoint matOfPoint = (MatOfPoint) iterator.next();
				Rect rec = Imgproc.boundingRect(matOfPoint);
				//float aspect = (float)rec.width/(float)rec.height;
				//if( rec.height < 10 || rec.width < 5||rec.y<75/*||aspect<=1*/){
				//	iterator.remove();
				//continue;
				//}
				Rect target = rec;
				SmartDashboard.putNumber("S_Contours", findContoursOutput.size());
				SmartDashboard.putNumber("S_X", rec.x);
				SmartDashboard.putNumber("S_Width", rec.width);
				SmartDashboard.putNumber("S_Height", rec.height);
			}			
			outputStream.putFrame(input);  		
  	}

  	/**
  	 * This method is a generated getter for the output of a HSL_Threshold.
  	 * @return Mat output from HSL_Threshold.
  	 */
  	public Mat hslThresholdOutput() {
  		return hslThresholdOutput;
  	}

  	/**
  	 * This method is a generated getter for the output of a CV_erode.
  	 * @return Mat output from CV_erode.
  	 */
  	public Mat cvErodeOutput() {
  		return cvErodeOutput;
  	}

  	/**
  	 * This method is a generated getter for the output of a Find_Contours.
  	 * @return ArrayList<MatOfPoint> output from Find_Contours.
  	 */
  	public ArrayList<MatOfPoint> findContoursOutput() {
  		return findContoursOutput;
  	}


  	/**
  	 * Segment an image based on hue, saturation, and luminance ranges.
  	 *
  	 * @param input The image on which to perform the HSL threshold.
  	 * @param hue The min and max hue
  	 * @param sat The min and max saturation
  	 * @param lum The min and max luminance
  	 * @param output The image in which to store the output.
  	 */
  	private void hslThreshold(Mat input, double[] hue, double[] sat, double[] lum,
  		Mat out) {
  		;
  	}

  	/**
  	 * Expands area of lower value in an image.
  	 * @param src the Image to erode.
  	 * @param kernel the kernel for erosion.
  	 * @param anchor the center of the kernel.
  	 * @param iterations the number of times to perform the erosion.
  	 * @param borderType pixel extrapolation method.
  	 * @param borderValue value to be used for a constant border.
  	 * @param dst Output Image.
  	 */
  	private void cvErode(Mat src, Mat kernel, Point anchor, double iterations,
  		int borderType, Scalar borderValue, Mat dst) {
  		if (kernel == null) {
  			kernel = new Mat();
  		}
  		if (anchor == null) {
  			anchor = new Point(-1,-1);
  		}
  		if (borderValue == null) {
  			borderValue = new Scalar(-1);
  		}
  		Imgproc.erode(src, dst, kernel, anchor, (int)iterations, borderType, borderValue);
  	}

  	/**
  	 * Sets the values of pixels in a binary image to their distance to the nearest black pixel.
  	 * @param input The image on which to perform the Distance Transform.
  	 * @param type The Transform.
  	 * @param maskSize the size of the mask.
  	 * @param output The image in which to store the output.
  	 */
  	private void findContours(Mat input, boolean externalOnly,
  		List<MatOfPoint> contours) {
  		Mat hierarchy = new Mat();
  		contours.clear();
  		int mode;
  		if (externalOnly) {
  			mode = Imgproc.RETR_EXTERNAL;
  		}
  		else {
  			mode = Imgproc.RETR_LIST;
  		}
  		int method = Imgproc.CHAIN_APPROX_SIMPLE;
  		Imgproc.findContours(input, contours, hierarchy, mode, method);
  	}
}

