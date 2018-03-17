package org.usfirst.frc.team2509.robot.subsystems;

import org.usfirst.frc.team2509.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *This is the gripper subsystem of our robot. This subsystem contains a piston that opens (to drop the box)
 *and closes (to grab the box).
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
    /**
     * 
     * @return Gripper_Limit
     */
    public DigitalInput getLimit1() {
    	return Limit1;
    }
    /**
     * 
     * @return Gripper_Piston
     */
    public DoubleSolenoid getPiston() {
    	return piston;
    }
    /**
     * closes the gripper so we can grab the box
     */
    public void close() {
    	piston.set(DoubleSolenoid.Value.kReverse);
    }
    /**
     * Opens the gripper so we can drop the box
     */
    public void open() {
    	piston.set(DoubleSolenoid.Value.kForward);
    }
}

