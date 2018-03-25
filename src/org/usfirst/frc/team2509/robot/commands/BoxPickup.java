package org.usfirst.frc.team2509.robot.commands;

import org.opencv.core.Rect;
import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2509.robot.subsystems.Gripper;
import org.usfirst.frc.team2509.robot.subsystems.Intake;
import org.usfirst.frc.team2509.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BoxPickup extends Command {
	private DriveTrain drive = Robot.drivetrain;
	private Gripper grip = Robot.gripper;
	private Intake intake = Robot.intake;
	private Vision vision = Robot.vision;
	private int width = vision.getWidth();
	private int height = vision.getHeight();
	private double centerX = vision.getCenterX();
	private double targetX = 60;
	private double centerY = vision.getCenterY();
	private double targetY = 58;
	private double rangeOfError = 7;
	private Rect target = vision.getTarget();
	
    public BoxPickup() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	updateVariables();
    	intake.extend();
    	intake.on();
    	grip.open();
    	if(target.x>(targetX+rangeOfError)) {
    		drive.getDrive().tankDrive(-0.6, 0.6);
    	}else if(target.x<(targetX-rangeOfError)) {
    		drive.getDrive().tankDrive(0.6, -0.6);
    	}else if((targetX-rangeOfError)<target.x&&target.x<(targetX+rangeOfError)) {
    		drive.getDrive().tankDrive(0.6, 0.6);
    	}else {
    		drive.getDrive().tankDrive(0, 0);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	updateVariables();
    	if(target.x>(targetX+rangeOfError)) {
    		drive.getDrive().tankDrive(-0.6, 0.6);
    	}else if(target.x<(targetX-rangeOfError)) {
    		drive.getDrive().tankDrive(0.6, -0.6);
    	}else if((targetX-rangeOfError)<target.x&&target.x<(targetX+rangeOfError)) {
    		drive.getDrive().tankDrive(0.6, 0.6);
    	}else {
    		drive.getDrive().tankDrive(0, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return target.y>targetY;
//    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	grip.close();
    	drive.getDrive().tankDrive(0, 0);
    	Timer.delay(0.1);
    	intake.off();
    	intake.retract();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    private void updateVariables() {

    	width = vision.getWidth();
    	height = vision.getHeight();
    	centerX = vision.getCenterX();
    	centerY = vision.getCenterY();
//    	rangeOfError = (width*0.025);
    	rangeOfError = 7;
    	target = vision.getTarget();
    }
}
