package org.usfirst.frc.team2509.robot.subsystems;

import org.usfirst.frc.team2509.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
	private static DoubleSolenoid Upper = RobotMap.Arm_UpperSolenoid;
	private static DoubleSolenoid Lower = RobotMap.Arm_LowerSolenoid;
	private static WPI_TalonSRX Motor = RobotMap.Arm_Motor;
	private static DigitalInput BackupLimit = RobotMap.Arm_LowerBackup;
	private static DigitalInput LowerLimit = RobotMap.Arm_LowerLimit;
	private static DigitalInput MiddleLimit = RobotMap.Arm_MiddleLimit;
	private static DigitalInput UpperLimit = RobotMap.Arm_UpperLimit;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void extendUpper() {
    	Upper.set(DoubleSolenoid.Value.kForward);
//    	Upper.set(DoubleSolenoid.Value.kOff);
    }
    public void retractUpper() {
    	Upper.set(DoubleSolenoid.Value.kReverse);
//    	Upper.set(DoubleSolenoid.Value.kOff);
    }
    public void extendLower() {
    	Lower.set(DoubleSolenoid.Value.kForward);
//    	Lower.set(DoubleSolenoid.Value.kOff);
    }
    public void rectractLower() {
    	Lower.set(DoubleSolenoid.Value.kReverse);
//    	Lower.set(DoubleSolenoid.Value.kOff);
    }
    public static void High() {
        	while(UpperLimit.get())	Motor.set(0.5);
        	Motor.set(0);    		
    	
    }
    public static void Middle() {
    	while(MiddleLimit.get()) Motor.set(0.5);
    	Motor.set(0);
    }
    public static void Lower(){   
    	while(LowerLimit.get()) {
    		Motor.set(-0.8);
    	}
    	Motor.set(0);
    }
    public void Up() {
    	Motor.set(1);
    }
    public void Down() {
    	Motor.set(-0.65);
    }
    public void Stop() {
    	Motor.set(0);
    }
    public DoubleSolenoid getUpper() {
    	return Upper;
    }
    public DoubleSolenoid getLower() {
    	return Lower;
    }
    public WPI_TalonSRX getMotor() {
    	return Motor;
    }
    public DigitalInput getBackupLimit() {
    	return BackupLimit;
    }
    public DigitalInput getLowerLimit() {
    	return LowerLimit;
    }
    public DigitalInput getMiddleLimit() {
    	return MiddleLimit;
    }
    public DigitalInput getUpperLimit() {
    	return UpperLimit;
    }
}

