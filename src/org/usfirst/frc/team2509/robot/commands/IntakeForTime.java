package org.usfirst.frc.team2509.robot.commands;

import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeForTime extends Command {
	private Intake intake = Robot.intake;
	DigitalInput IntakeSwitch1 = Robot.gripper.getLimit1();
    public IntakeForTime() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	intake.extend();
    	intake.on();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (IntakeSwitch1.get()== false);
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.retract();
    	intake.off();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
