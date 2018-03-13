package org.usfirst.frc.team2509.robot.subsystems;

import org.usfirst.frc.team2509.robot.OI;
import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem implements PIDOutput{
	//Subsystem Variables
	private static DoubleSolenoid Shifter = RobotMap.DriveTrain_Shifter;
	private static DoubleSolenoid Wheely = RobotMap.DriveTrain_Wheely;
	private static Encoder LeftEncoder = RobotMap.DriveTrain_LeftEncoder;
	private static Encoder RightEncoder = RobotMap.DriveTrain_RightEncoder;
	private static AHRS Gyro = RobotMap.DriveTrain_NavX;
	
	private static WPI_TalonSRX Left_1 = RobotMap.DriveTrain_left1;
	private static WPI_TalonSRX Left_2 = RobotMap.DriveTrain_left2;
	
	private static WPI_TalonSRX Right_1 = RobotMap.DriveTrain_right1;
	private static WPI_TalonSRX Right_2 = RobotMap.DriveTrain_right2;
	private static SpeedControllerGroup Left = RobotMap.DriveTrain_Left;
	private static SpeedControllerGroup Right = RobotMap.DriveTrain_Right;
	private static DifferentialDrive Drive = RobotMap.RobotDrive;
	private static OI oi = Robot.oi;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//For a reason unknown to you
    }
    public static void drive(Joystick stick) {
    	Drive.arcadeDrive(slowReverse(getScaledDrive(-stick.getY())), getScaledDrive(-stick.getZ()));
    }
    public static double slowReverse(double input) {
    	if(input<0) {
    		input = input*0.8;
    	}
    	return input;
    }
    /**
     * Resets all sensors
     */
    public void sensorReset() {
    	Gyro.reset();
    	RightEncoder.reset();
    	LeftEncoder.reset();
    }
    /**
     * Gets angle from the Gyro to tell the motors to make a specific turn
     * @param Angle
     */
    public static double getScaledDrive(double input) {
		return input*((Robot.oi.OperatorStick.getRawAxis(3)+3)*0.25);
    }
    public void rotate(double targetAngle) {
    	Gyro.reset();
    	Timer.delay(0.1);
    	if(Gyro.getAngle()<targetAngle) {
    		while(Gyro.getAngle()<targetAngle)	Drive.tankDrive(-0.6, 0.6);
    		Drive.tankDrive(0, 0);
    	}else if(Gyro.getAngle()>targetAngle) {
    		while(Gyro.getAngle()>targetAngle)Drive.tankDrive(0.6, -0.6);
    		Drive.tankDrive(0, 0);
    	}else {
    		Drive.tankDrive(0, 0);
    	}
    	if(Gyro.getAngle()<targetAngle) {
    		while(Gyro.getAngle()<targetAngle)	Drive.tankDrive(-0.6, 0.6);
    		Drive.tankDrive(0, 0);
    	}else if(Gyro.getAngle()>targetAngle) {
    		while(Gyro.getAngle()>targetAngle)Drive.tankDrive(0.6, -0.6);
    		Drive.tankDrive(0, 0);
    	}else {
    		Drive.tankDrive(0, 0);
    	}
    }
    public void AccDrive(double targetDistance) {
    	sensorReset();
    	double wheelDiameter = 6;
    	double target = (targetDistance/(wheelDiameter*Math.PI))*3*360;
    	Timer.delay(0.1);
    	double CurrentDistance = ((RightEncoder.get()+LeftEncoder.get())/2);
    	double AccelGain = 1.05;
    	double DecelGain = 0.95;
    	double Speed = 0.25;
    	double TimeDelay = 0.015;
    	while(CurrentDistance <= target/2) {
    		if(Speed >= 0.9) {
    			Drive.arcadeDrive(Speed, Gyro.getAngle()*(0.15));
    			Timer.delay(TimeDelay);
    		}
    		else {
    			Speed = Speed * AccelGain;
    			Drive.arcadeDrive(Speed, Gyro.getAngle()*(0.15));
    			Timer.delay(TimeDelay);
    		}
    	}
    	while(CurrentDistance < target){
    		if(Speed > 0.35) {
    			Speed = Speed * DecelGain;
    			Drive.arcadeDrive(Speed, Gyro.getAngle()*(0.15));
    			Timer.delay(TimeDelay);
    		}
    		else {
    			Drive.arcadeDrive(Speed, Gyro.getAngle()*(0.15));
    			Timer.delay(TimeDelay);
    		}
    	}
    	Drive.tankDrive(0, 0);
    }
    public void AccDriveMark2(double targetDistance) {
    	sensorReset();
    	double wheelDiameter = 6;
//    	double target = (targetDistance/(wheelDiameter*Math.PI))*3*360;
    	double target = targetDistance;
    	Timer.delay(0.1);
    	double CurrentDistance = ((RightEncoder.getDistance()+LeftEncoder.getDistance())/2);
    	double TimeDelay = 0.015;
    	double MaxVolts = 0.9;
    	double MinVolts = 0.35;
    	double VoltsRange = MaxVolts-MinVolts;
    	while(CurrentDistance <= target/2) {
    		SmartDashboard.putNumber("Left", Left.get());
    		SmartDashboard.putNumber("Right", Right.get());
    		SmartDashboard.putNumber("Rate Left", LeftEncoder.getRate());
    		SmartDashboard.putNumber("Right Rate", RightEncoder.getRate());
    		double AccelVolts = ((VoltsRange*CurrentDistance)/(target/2));
//    		AccelVolts = AccelVolts * AccelVolts;
    		CurrentDistance = ((RightEncoder.getDistance()+LeftEncoder.getDistance())/2);
    		Drive.arcadeDrive(Math.max(AccelVolts+MinVolts, MinVolts), Gyro.getAngle()*(0.15));
			Timer.delay(TimeDelay);
    	}
    	CurrentDistance = ((RightEncoder.getDistance()+LeftEncoder.getDistance())/2);
    	while(CurrentDistance < target){
    		SmartDashboard.putNumber("Left", Left.get());
    		SmartDashboard.putNumber("Right", Right.get());
    		SmartDashboard.putNumber("Rate Left", LeftEncoder.getRate());
    		SmartDashboard.putNumber("Right Rate", RightEncoder.getRate());
    		double DecelVolts = (VoltsRange*(target-CurrentDistance))/(target/2);
    		CurrentDistance = ((RightEncoder.getDistance()+LeftEncoder.getDistance())/2);
    		Drive.arcadeDrive(Math.max(DecelVolts+MinVolts, MinVolts), Gyro.getAngle()*(0.15));
			Timer.delay(TimeDelay);
    	}
    	Drive.tankDrive(0, 0);
    }
    /**
     * Switches the gear between high and low, with a double solenoid.
     * @param isExtended
     */
    public void shift(boolean isExtended) {
    	if(isExtended) {
    		Shifter.set(DoubleSolenoid.Value.kForward);
    	}
    	else {
    		Shifter.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
    /**
     * When called will drive forward.
     * You need to input inches as a target value.
     * @param targetDistance
     */
    public void driveForward(double targetDistance) {
    	sensorReset();
    	double wheelDiameter = 6;
    	double target = (targetDistance/(wheelDiameter*Math.PI))*3*360;
    	Timer.delay(0.1);
    	while((RightEncoder.get()+LeftEncoder.get())/2<=target) {
    		Drive.arcadeDrive(0.7, Gyro.getAngle()*(0.1));
    	}
    	Drive.tankDrive(0, 0);
    }
    public void driveBackward(double targetDistance) {
    	sensorReset();
    	double wheelDiameter = 6;
    	double target = (targetDistance/(wheelDiameter*Math.PI))*3*360;
    	Timer.delay(0.1);
    	while((RightEncoder.get()+LeftEncoder.get())/2>=(target*(-1))) {
    		Drive.arcadeDrive(-0.5, Gyro.getAngle()*(0.1));
    	}
    	Drive.tankDrive(0, 0);
    }
    public void AccelMark3(double targetDistance) {
    	sensorReset();
    	double wheelDiameter = 6;
    	double target = (targetDistance/(wheelDiameter*Math.PI))*3*360;
    	Timer.delay(0.1);
    	double CurrentRawDistance = ((RightEncoder.getDistance()+LeftEncoder.getDistance())/2);
		double CurrentDistance = ((RightEncoder.getDistance()+LeftEncoder.getDistance())/(2*3*360));
    	double MinVolts = 0.3;
    	double MaxVolts = 0.75;
    	double BacklashRate = 0.005;//0.005
    	double AccelRate = 0.05;//0.05
    	double DeccelRate = 0.001;//0.03
    	double Volts = 0.00;
    	double GyroGain = 0.1;
    	while (Volts < (MinVolts/2)){
    		Drive.arcadeDrive(Volts, Gyro.getAngle()*(GyroGain));
    		Timer.delay(BacklashRate);
    		Volts = Volts + (MinVolts/20);
    	}
    	Volts = MinVolts;
    	while(CurrentRawDistance < target/1.25) {
    		SmartDashboard.putNumber("Target", target);
    		SmartDashboard.putNumber("Current", CurrentRawDistance);
    		
    		CurrentRawDistance = ((RightEncoder.get()+LeftEncoder.get())/(2));
    		CurrentDistance = ((RightEncoder.get()+LeftEncoder.get())/(2*3*360*wheelDiameter*Math.PI));
    		Drive.arcadeDrive(Math.min(MaxVolts, Volts), Gyro.getAngle()*(GyroGain));
    		Volts = Volts + (AccelRate*CurrentDistance);
    	}
    	while(CurrentRawDistance < target) {
    		SmartDashboard.putNumber("Target", target);
    		SmartDashboard.putNumber("Current", CurrentRawDistance);
    		CurrentRawDistance = ((RightEncoder.get()+LeftEncoder.get())/(2));
    		CurrentDistance = ((RightEncoder.get()+LeftEncoder.get())/(2*3*360*wheelDiameter*Math.PI));
    		if(Volts >= MaxVolts) {
    			Drive.arcadeDrive(MaxVolts, Gyro.getAngle()*(GyroGain));
    			Volts = Volts-(DeccelRate*(target-CurrentDistance));
    		}
    		else {
    			Drive.arcadeDrive(Math.max(Volts, MinVolts), Gyro.getAngle()*(GyroGain));
    			Volts = Volts-(DeccelRate*(target-CurrentDistance));
    		}
    	}
    	Drive.tankDrive(0, 0);
    }
    public void wheely() {
    	Wheely.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * 
     * @return DriveTrain_Shifter
     */
    public DoubleSolenoid getShifter() {
    	return Shifter;
    }
    public DoubleSolenoid getWheely() {
    	return Wheely;
    }
    /**
     * 
     * @return DriveTrain_LeftEncoder
     */
    public Encoder getLeftEncoder() {
    	return LeftEncoder;
    }
    /**
     * 
     * @return DriveTrain_RightEncoder
     */
    public Encoder getRightEncoder() {
    	return RightEncoder;
    }
    /**
     * 
     * @return DriveTrain_Gyro
     */
    public AHRS getGyro() {
    	//return Gyro;
    	return Gyro;
    }
    /**
     * 
     * @return DriveTrain_Left
     */
    public SpeedControllerGroup getLeft() {
    	return Left;
    }
    /**
     * 
     * @return DriveTrain_Right
     */
    public SpeedControllerGroup getRight() {
    	return Right;
    }
    /**
     * 
     * @return DriveTrain_Drive
     */
    public DifferentialDrive getDrive() {
    	return Drive;
    }
    /**
     * 
     * @return DriveTrain_Left_1
     */
    public WPI_TalonSRX getLeft1() {
    	return Left_1;
    }
    /**
     * 
     * @return DriveTrain_Left_2
     */
    public WPI_TalonSRX getLeft2() {
    	return Left_1;
    }
    /**
     * 
     * @return DriveTrain_Left_3
     */
    public WPI_TalonSRX getLeft3() {
    	return Left_2;
    }
    /**
     * 
     * @return DriveTrain_Right_1
     */
    public WPI_TalonSRX getRight1() {
    	return Right_1;
    }
    /**
     * 
     * @return DriveTrain_Right_2
     */
    public WPI_TalonSRX getRight2() {
    	return Right_1;
    }
    /**
     * 
     * @return DriveTrain_Ri6ght_3
     */
    public WPI_TalonSRX getRight3() {
    	return Right_2;
    }
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}

}