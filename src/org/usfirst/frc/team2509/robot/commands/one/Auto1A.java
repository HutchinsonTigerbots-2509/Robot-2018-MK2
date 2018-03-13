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
public class Auto1A extends Command {
	Arm arm = Robot.arm;
	DriveTrain driveTrain = Robot.drivetrain;
	Gripper grip =  Robot.gripper;
	Wrist wrist = Robot.wrist;
    public Auto1A() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.retractUpper();
    	driveTrain.driveForward(75); //Drive forward 66 inches
    	driveTrain.rotate(90.0); //Turn right 90 degrees
//    	arm.armThreadMid.start();
    	driveTrain.driveForward(45.0); //Drive forward 70 inches
    	driveTrain.rotate(-90.0); //Turn left 78 degrees
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
//    	arm.armThreadMid.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
