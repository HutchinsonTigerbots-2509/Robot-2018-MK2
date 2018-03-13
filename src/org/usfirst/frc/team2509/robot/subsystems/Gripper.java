package org.usfirst.frc.team2509.robot.subsystems;

import org.usfirst.frc.team2509.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gripper extends Subsystem {
	private static DigitalInput Limit1 = RobotMap.Gripper_Limit;
	private static DoubleSolenoid piston = RobotMap.Gripper_Piston;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public DigitalInput getLimit1() {
    	return Limit1;
    }
    public DoubleSolenoid getPiston() {
    	return piston;
    }
    public void close() {
    	piston.set(DoubleSolenoid.Value.kReverse);
    }
    public void open() {
    	piston.set(DoubleSolenoid.Value.kForward);
    }
}

