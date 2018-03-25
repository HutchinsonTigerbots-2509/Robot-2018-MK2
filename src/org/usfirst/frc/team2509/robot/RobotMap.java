/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2509.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static Compressor comp;//Gives the air tanks air
	public static UsbCamera cam;//the camera that is attached to the robot
	//Drivetran Variable
	public static DoubleSolenoid DriveTrain_Shifter;//Shifts between high and low gear
	public static DoubleSolenoid DriveTrain_Wheely;//Keeps us from falling over
	public static Encoder DriveTrain_LeftEncoder;//Is the encoder that keeps track of the Left wheels distance on the driveTrain
	public static Encoder DriveTrain_RightEncoder;//Is the encoder that keeps track of the right wheels distance on the driveTrain
	public static AHRS DriveTrain_NavX;//The gyro that keeps track of our rotation during autonomous
	public static WPI_TalonSRX DriveTrain_left1;//Motor1 of DriveTrain_Left
	public static WPI_TalonSRX DriveTrain_left2;//Motor2 of DriveTrain_Left
	public static WPI_TalonSRX DriveTrain_right1;//Motor1 of DriveTrain_Right
	public static WPI_TalonSRX DriveTrain_right2;//Motor2 or DriveTrain_Right
	public static SpeedControllerGroup DriveTrain_Left;//Groups DriveTrain_left1 and DriveTrain_left2 together
	public static SpeedControllerGroup DriveTrain_Right;//Groups DriveTrain_right1 and DriveTrain_right2 together
	public static DifferentialDrive RobotDrive;//Groups DriveTrain_Left and DriveTrain_Right together
	//Arm Variable
	public static DoubleSolenoid Arm_Piston;//The piston that lifts the arm Up or Down
	public static DoubleSolenoid Arm_Punch;//Pushes the box out if we don't like it
	public static DigitalInput Arm_LowerLimit;
	public static DigitalInput Arm_UpperLimit;
	//Gripper Variable
	public static DigitalInput Gripper_Limit;
	public static DoubleSolenoid Gripper_Piston;//The piston that opens and closes the gripper
	//Intake Variable
	public static DoubleSolenoid Intake_Piston;//Extends or retracts the Intake subsystem
	public static VictorSP Intake_LeftMotor;//The motor on the right intake
	public static VictorSP Intake_RightMotor;//The motor on the left intake
	//Climb Variable
	public static VictorSP Climb_Motor1;//A Motor that allows us to climb
	public static VictorSP Climb_Motor2;//A Motor that allows us to climb
	public static SpeedControllerGroup Climb_motors;//Groups Climb_Motor1 and Climb_Motor2 together
	/**
	 * 
	 */
	public static void init(){
		comp = new Compressor();
		
		//Drivetrain Variable Initialize
		DriveTrain_Shifter = new DoubleSolenoid(0,1);		
		
		DriveTrain_LeftEncoder = new Encoder(0,1);
		DriveTrain_LeftEncoder.setDistancePerPulse(0.0179136);
//		DriveTrain_LeftEncoder.setReverseDirection(true);
		SmartDashboard.putNumber("Left Encoder", DriveTrain_LeftEncoder.get());
		
		DriveTrain_RightEncoder = new Encoder(2,3);
		DriveTrain_RightEncoder.setDistancePerPulse(0.0179136);
		DriveTrain_RightEncoder.setReverseDirection(true);
		SmartDashboard.putNumber("Right Encoder", DriveTrain_RightEncoder.get());
		
		DriveTrain_NavX = new AHRS(SPI.Port.kMXP);
		SmartDashboard.putNumber("Gyro", DriveTrain_NavX.getAngle());
		SmartDashboard.putNumber("Accel", DriveTrain_NavX.getRawAccelY());

		
//		DriveTrain_left1 = new WPI_TalonSRX(0);
		
		DriveTrain_left1 = new WPI_TalonSRX(4);//2
		
		DriveTrain_left2 = new WPI_TalonSRX(5);//1
		
//		DriveTrain_right1 = new WPI_TalonSRX(3);
		
		DriveTrain_right1 = new WPI_TalonSRX(2);//4
		
		DriveTrain_right2 = new WPI_TalonSRX(1);//5
		
		DriveTrain_Left = new SpeedControllerGroup(
				DriveTrain_left1,DriveTrain_left2);
		
		DriveTrain_Right = new SpeedControllerGroup(
				DriveTrain_right1,DriveTrain_right2);
		
		RobotDrive = new DifferentialDrive(DriveTrain_Left,DriveTrain_Right);
		//Arm Variable Initialize
		Arm_Piston = new DoubleSolenoid(2,3);
		Arm_Punch = new DoubleSolenoid(4,5);
		Arm_LowerLimit = new DigitalInput(5);
		Arm_UpperLimit = new DigitalInput(4);
		SmartDashboard.putBoolean("Arm Lower", Arm_LowerLimit.get());
		SmartDashboard.putBoolean("Arm Upper", Arm_UpperLimit.get());
		
		//Gripper Variable Initialize
		Gripper_Limit = new DigitalInput(10);
		SmartDashboard.putBoolean("Gripper1", Gripper_Limit.get());
		
		Gripper_Piston = new DoubleSolenoid(6,7);
		
		//Intake Variable Initialize
		Intake_Piston = new DoubleSolenoid(1, 0, 1);
		
		Intake_LeftMotor = new VictorSP(0);
		Intake_LeftMotor.setInverted(true);
		Intake_RightMotor = new VictorSP(1);
//		Intake_RightMotor.setInverted(true);
		
		//Climb Variable Initialize
		Climb_Motor1 = new VictorSP(2);
		Climb_Motor2 = new VictorSP(3);
		Climb_motors = new SpeedControllerGroup(Climb_Motor1,Climb_Motor2);
		
		//Vision Variable

		cam = CameraServer.getInstance().startAutomaticCapture();
		cam.setBrightness(35);
		cam.setFPS(30);
//		cam.setResolution(640, 480);
//		cam.setResolution(160, 120);
	}
}
