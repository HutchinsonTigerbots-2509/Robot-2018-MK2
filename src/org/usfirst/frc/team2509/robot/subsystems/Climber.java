package org.usfirst.frc.team2509.robot.subsystems;

import org.usfirst.frc.team2509.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *The climber has two motors that lift a shaft with a hook on it. When you call the Up void, you tell the motors to
 *lift the shaft up. When you call the Down void, it pulls the robot up by latching onto the bar.
 */
public class Climber extends Subsystem {
	VictorSP motor1 = RobotMap.Climb_Motor1;
	VictorSP motor2 = RobotMap.Climb_Motor2;
	SpeedControllerGroup motors = RobotMap.Climb_motors;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	/**
	 *Shoots the climber up
	 */
	public void Up() {
		motors.set(1);
	}
	/**
	 * Pulls the climber down, so the robot is pulled up
	 */
	public void Down() {
		motors.set(-0.35);
	}
	/**
	 *Stops the climber so it brakes
	 */
	public void Stop() {
		motors.set(0);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

