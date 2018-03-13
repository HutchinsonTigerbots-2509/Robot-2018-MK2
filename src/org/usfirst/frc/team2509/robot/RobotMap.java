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
	
	public static Compressor comp;
	public static UsbCamera cam;
	//Drivetran Variable
	public static DoubleSolenoid DriveTrain_Shifter;
	public static DoubleSolenoid DriveTrain_Wheely;
	public static Encoder DriveTrain_LeftEncoder;
	public static Encoder DriveTrain_RightEncoder;
	public static AHRS DriveTrain_NavX;
	public static WPI_TalonSRX DriveTrain_left1;
	public static WPI_TalonSRX DriveTrain_left2;
	public static WPI_TalonSRX DriveTrain_right1;
	public static WPI_TalonSRX DriveTrain_right2;
	public static SpeedControllerGroup DriveTrain_Left;
	public static SpeedControllerGroup DriveTrain_Right;
	public static DifferentialDrive RobotDrive;
	//Arm Variable
	public static DoubleSolenoid Arm_LowerSolenoid;
	public static DoubleSolenoid Arm_UpperSolenoid;
	public static WPI_TalonSRX Arm_Motor;
	public static DigitalInput Arm_LowerBackup;
	public static DigitalInput Arm_LowerLimit;
	public static DigitalInput Arm_MiddleLimit;
	public static DigitalInput Arm_UpperLimit;
	//Gripper Variable
	public static DigitalInput Gripper_Limit;
	public static DoubleSolenoid Gripper_Piston;
	//Intake Variable
	public static DoubleSolenoid Intake_Piston;
	public static VictorSP Intake_LeftMotor;
	public static VictorSP Intake_RightMotor;
	//Wrist Variable
	public static WPI_TalonSRX Wrist;
	public static Encoder WristEncoder;
//	public static DigitalInput Wrist_UpperLimit;
//	public static DigitalInput Wrist_LowerLimit;
	//Climb Variable
	public static VictorSP Climb_Motor1;
	public static VictorSP Climb_Motor2;
	public static SpeedControllerGroup Climb_motors;
	/**
	 * 
	 */
	public static void init(){
		comp = new Compressor();
		cam = CameraServer.getInstance().startAutomaticCapture();
		cam.setBrightness(50);
		//Drivetrain Variable Initialize
		DriveTrain_Shifter = new DoubleSolenoid(0,1);
		
		DriveTrain_Wheely = new DoubleSolenoid(1,2,3);
		
		
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
		Arm_LowerSolenoid = new DoubleSolenoid(3,2);
	
		Arm_UpperSolenoid = new DoubleSolenoid(4,5);
		
		Arm_Motor = new WPI_TalonSRX(3);
		
		Arm_LowerBackup = new DigitalInput(9);
		SmartDashboard.putBoolean("Arm Backup", Arm_LowerBackup.get());
		
		Arm_LowerLimit = new DigitalInput(4);
		SmartDashboard.putBoolean("Arm Lower", Arm_LowerLimit.get());
		
		Arm_MiddleLimit = new DigitalInput(6);
		SmartDashboard.putBoolean("Arm Middle", Arm_MiddleLimit.get());
		
		Arm_UpperLimit = new DigitalInput(5);
		SmartDashboard.putBoolean("Arm Upper", Arm_UpperLimit.get());
		
		//Gripper Variable Initialize
		Gripper_Limit = new DigitalInput(10);
		SmartDashboard.putBoolean("Gripper1", Gripper_Limit.get());
		
		Gripper_Piston = new DoubleSolenoid(6,7);
		
		//Intake Variable Initialize
		Intake_Piston = new DoubleSolenoid(1, 0, 1);
		
		Intake_LeftMotor = new VictorSP(0);
		Intake_RightMotor = new VictorSP(1);
		
		//Wrist Variable Initialize
		Wrist = new WPI_TalonSRX(0);
		
//		Wrist_LowerLimit = new DigitalInput(8);
//		SmartDashboard.putBoolean("Wrist Lower", Wrist_LowerLimit.get());
//		
//		Wrist_UpperLimit = new DigitalInput(7);
//		SmartDashboard.putBoolean("Wrist Upper", Wrist_UpperLimit.get());
		
		WristEncoder = new Encoder(8,7);
		WristEncoder.setReverseDirection(true);
		SmartDashboard.putNumber("WristEncoder", WristEncoder.get());
		
		Climb_Motor1 = new VictorSP(2);
		Climb_Motor2 = new VictorSP(3);
		Climb_motors = new SpeedControllerGroup(Climb_Motor1,Climb_Motor2);
	}
}
