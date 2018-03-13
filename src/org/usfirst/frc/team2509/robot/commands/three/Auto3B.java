package org.usfirst.frc.team2509.robot.commands.three;

import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.subsystems.Arm;
import org.usfirst.frc.team2509.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto3B extends Command {
	Arm arm = Robot.arm;
	DriveTrain driveTrain = Robot.drivetrain;

    public Auto3B() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	    driveTrain.driveForward(66); //Drive forward 66 inches
	    driveTrain.rotate(-90.0); //Turn left 90 degrees
	    driveTrain.driveForward(70.0); //Drive forward 70 inches
	    driveTrain.rotate(78.0); //Turn right 78 degrees
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
