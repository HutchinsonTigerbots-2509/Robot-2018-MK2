package org.usfirst.frc.team2509.robot.subsystems;

import org.usfirst.frc.team2509.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Contains extendArmPiston, retractArmPiston, and getArmPiston.
 *@
 *The Arm has the gripper and wrist attached to it. When you extend/retract the Arm, it will move the gripper and wrist
 *in the place where you want it to. We use this mainly to move the arm into a place where
 */
public class Arm extends Subsystem {
	private static DoubleSolenoid ArmPiston = RobotMap.Arm_Piston;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //Extends the piston attached to the arm	
    public void Up() {
    	ArmPiston.set(DoubleSolenoid.Value.kForward);
    }
     //Retracts the piston attached to the arm
    public void Down() {
    	ArmPiston.set(DoubleSolenoid.Value.kReverse);
    }
    public DoubleSolenoid getArmPiston() {
    	return ArmPiston;
    }
  }
