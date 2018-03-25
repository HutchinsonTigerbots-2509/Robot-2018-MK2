package org.usfirst.frc.team2509.robot.commands;

import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Our default Autonomous command so we can cross the line of autonomous and get a ranking point
 */
public class DefualtAuto extends Command {
	DriveTrain driveTrain = Robot.drivetrain;

    public DefualtAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    driveTrain.driveForward(150);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
