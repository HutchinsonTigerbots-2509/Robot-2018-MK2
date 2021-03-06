package org.usfirst.frc.team2509.robot.commands;

import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *We us this to climb. This brings the climber down so that the robot is moved up
 */
public class ClimbDown extends Command {
	public Climber climb = Robot.climber;
    public ClimbDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	climb.Down();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	climb.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
