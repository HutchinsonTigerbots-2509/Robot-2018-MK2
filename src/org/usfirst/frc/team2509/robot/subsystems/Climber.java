package org.usfirst.frc.team2509.robot.subsystems;

import org.usfirst.frc.team2509.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	VictorSP motor1 = RobotMap.Climb_Motor1;
	VictorSP motor2 = RobotMap.Climb_Motor2;
	SpeedControllerGroup motors = RobotMap.Climb_motors;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void Up() {
		motors.set(1);
	}
	public void Down() {
		motors.set(-0.35);
	}
	public void Stop() {
		motors.set(0);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

