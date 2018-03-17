package org.usfirst.frc.team2509.robot.subsystems;

import org.usfirst.frc.team2509.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import  edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *This is the Intake subsystem. Its function is to pull in the box or shoot it out, depending on the situation. This
 *allows our gripper to take in boxes and shoot them out again if it is a failed attempt (along with using Arm.punch).
 */
public class Intake extends Subsystem {
	private static VictorSP leftMotor = RobotMap.Intake_LeftMotor;
	private static VictorSP rightMotor = RobotMap.Intake_RightMotor;
	
	private static DoubleSolenoid piston = RobotMap.Intake_Piston;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    /**
     * Extends the two arms for the box pickup.
     */
    public void extend() {
    	
    	piston.set(DoubleSolenoid.Value.kForward);
//    	piston.set(DoubleSolenoid.Value.kOff);
    }
    /**
     * Retracts the two arms for the box pickup.
     */
    public void retract() {
    	piston.set(DoubleSolenoid.Value.kReverse);
//    	piston.set(DoubleSolenoid.Value.kOff);
    }
    /**
     * Turns on the wheels for the box pickup method.
     */
    public void on() {
    	leftMotor.set(-0.9);
    	rightMotor.set(-0.9);
    }
    /**
     * Turns off the wheels for the box pickup method.
     */
    public void off() {
    	leftMotor.set(0);
    	rightMotor.set(0);
    	
    }
    /**
     * Sets the motors in reverse direction so it shoots out the box
     */
    public void reverse() {
    	leftMotor.set(0.9);
    	rightMotor.set(0.9);
    }
}

