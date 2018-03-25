package org.usfirst.frc.team2509.robot.subsystems;

import org.usfirst.frc.team2509.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 This is the Arm subsystem. This is all of our stuff in dealing with the box. There are two main functions of the subsystem-
 *Pushing out the box and lifting the box to drop it. We use two pistons to do that, Arm_Piston and Arm_Punch. The Arm_Piston moves
 *upwards when you call the Up void and moves down when you call the Down void. Arm_Punch, however, pushes out the box when it
 *extends, or the Out void, and retracts when you call the In void.
 */
public class Arm extends Subsystem {
	private static DoubleSolenoid piston = RobotMap.Arm_Piston;
	private static DoubleSolenoid punch = RobotMap.Arm_Punch;
	private static DigitalInput lowerLimit = RobotMap.Arm_LowerLimit;
	private static DigitalInput upperLimit = RobotMap.Arm_UpperLimit;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Extends the piston attached to the arm	
     */	
    public void Up() {
    	piston.set(DoubleSolenoid.Value.kForward);
    }
    /**
     * Retracts the piston attached to the arm
     */
    public void Down() {
    	piston.set(DoubleSolenoid.Value.kReverse);
    }
    /**
     * Returns RobotMap.Arm_Piston
     * @return Arm_Piston
     */
    public DoubleSolenoid getPiston() {
    	return piston;
    }
    /**
     * Shoots out the box with the piston located at the bottem of our robot. It is Underneath the battery
     */
    public void Out() {
    	punch.set(DoubleSolenoid.Value.kForward);
    }
    /**
     * Retracts the piston described above
     */
    public void In() {
    	punch.set(DoubleSolenoid.Value.kReverse);
    }
    /**
     * Returns RobotMap.Arm_Punch
     * @return Arm_Punch
     */
    public DoubleSolenoid getPunch() {
    	return punch;
    }
    public DigitalInput getLowerLimit() {
    	return lowerLimit;
    }
    public DigitalInput getUpperLimit() {
    	return upperLimit;
    }
  }
