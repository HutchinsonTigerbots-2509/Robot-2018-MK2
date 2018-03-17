package org.usfirst.frc.team2509.robot.subsystems;

import org.usfirst.frc.team2509.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Contains Arm.Up, Arm.Down, and getArmPiston.
 *@
 *The Arm has the gripper and wrist attached to it. When you extend/retract the Arm, it will move the gripper and wrist
 *in the place where you want it to. We use this mainly to move the arm into a place where
 */
public class Arm extends Subsystem {
	private static DoubleSolenoid piston = RobotMap.Arm_Piston;
	private static DoubleSolenoid punch = RobotMap.Arm_Punch;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //Extends the piston attached to the arm	
    public void Up() {
    	piston.set(DoubleSolenoid.Value.kForward);
    }
     //Retracts the piston attached to the arm
    public void Down() {
    	piston.set(DoubleSolenoid.Value.kReverse);
    }
    public DoubleSolenoid getPiston() {
    	return piston;
    }
    public void Out() {
    	punch.set(DoubleSolenoid.Value.kForward);
    }
     //Retracts the piston attached to the arm
    public void In() {
    	punch.set(DoubleSolenoid.Value.kReverse);
    }
    public DoubleSolenoid getPunch() {
    	return punch;
    }
  }
