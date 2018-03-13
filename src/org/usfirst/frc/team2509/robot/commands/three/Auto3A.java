package org.usfirst.frc.team2509.robot.commands.three;

import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.commands.ArmMid;
import org.usfirst.frc.team2509.robot.subsystems.Arm;
import org.usfirst.frc.team2509.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto3A extends Command {
	DriveTrain driveTrain = Robot.drivetrain;
	Arm arm = Robot.arm;
	Command armMid = new ArmMid();
    public Auto3A() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.rectractLower();
//    	driveTrain.driveForward(35);
//    	driveTrain.rotate(-90);
//    	driveTrain.driveForward(140);
//    	driveTrain.rotate(90);
//    	arm.armThreadMid.start();
//    	driveTrain.driveForward(25);
//    	Timer.delay(3);
//    	driveTrain.driveBackward(30);
//    	armMid.cancel();
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
