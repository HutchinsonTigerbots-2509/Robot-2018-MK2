package org.usfirst.frc.team2509.robot.commands.one;

import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.subsystems.Arm;
import org.usfirst.frc.team2509.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2509.robot.subsystems.Gripper;
import org.usfirst.frc.team2509.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto1B extends Command {
	DriveTrain driveTrain = Robot.drivetrain;
	Arm arm = Robot.arm;
	Gripper grip =  Robot.gripper;
	Wrist wrist = Robot.wrist;

    public Auto1B() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.retractUpper();
    	driveTrain.driveForward(35);
    	driveTrain.rotate(90);
    	driveTrain.driveForward(152);
//    	arm.armThreadMid.start();
    	driveTrain.rotate(-90);
    	driveTrain.driveForward(32);
//    	wrist.Down();
    	grip.open();
//    	wrist.Up();
    	driveTrain.driveBackward(15);
    	arm.Down();
    	arm.extendUpper();
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
