package org.usfirst.frc.team2509.robot.commands.one;

import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto1D extends Command {
	DriveTrain driveTrain = Robot.drivetrain;
    public Auto1D() {
       
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.driveForward(38);
    	driveTrain.rotate(90);
    	driveTrain.driveForward(200);
    	driveTrain.rotate(-90);
    	
    	driveTrain.driveForward(72);
    	driveTrain.rotate(-90);
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
