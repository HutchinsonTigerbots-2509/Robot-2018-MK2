package org.usfirst.frc.team2509.robot.subsystems;

import org.usfirst.frc.team2509.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wrist extends Subsystem {
	private static WPI_TalonSRX motor = RobotMap.Wrist;
	private static Encoder Encoder = RobotMap.WristEncoder;
//	private static DigitalInput upperLimit = RobotMap.Wrist_UpperLimit;
//	private static DigitalInput lowerLimit = RobotMap.Wrist_LowerLimit;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
//    public void Up() {
//    	while(upperLimit.get()) motor.set(.45);
//    	motor.set(0);
//    	
//    }
    public void Up() {
    		motor.set(0.45);
    }
    public void Down() {
    		motor.set(-0.45);
    }
//    public void Down() {
//    	while(lowerLimit.get()) motor.set(-.45);
//    	motor.set(0);
//    }
    public void Idle() { //Probably not needed, but I'm paranoid.
    	motor.set(0);
    }
    
    public WPI_TalonSRX getMotor() {
    	return motor;
    }
    public Encoder getEncoder() {
    	return Encoder;
    }
//    public DigitalInput getUpperLimit() {
//    	return upperLimit;
//    }
//    public DigitalInput getLowerLimit() {
//    	return lowerLimit;
//    }
}

