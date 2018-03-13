package org.usfirst.frc.team2509.robot.commands.two;

import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto2X extends Command {

	DriveTrain driveTrain = Robot.drivetrain;
	
	//so this is the very primitive version of 2x, basically without the gyro turn 
    public Auto2X() {
       
    	driveTrain.driveForward(30);
    	driveTrain.rotate(-90);
    	driveTrain.driveForward(10);
    	driveTrain.rotate(-90);
    	driveTrain.driveForward(25);
    	Timer.delay(3);
    	
    	driveTrain.driveForward(-30);
    	driveTrain.rotate(90);
    	driveTrain.driveForward(90);
    	driveTrain.rotate(90);
    	driveTrain.driveForward(135);
    	driveTrain.rotate(-90);
    	driveTrain.driveForward(10);
    	driveTrain.rotate(-90);
    	driveTrain.driveForward(10);

    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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
