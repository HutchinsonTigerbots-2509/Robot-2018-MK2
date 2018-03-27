package org.usfirst.frc.team2509.robot.commands;

import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *We use this command in autonomous to drive forward a certain distance. Enter the distance you
 *want the robot to go in () when we call this command.
 */
public class DriveForward extends Command {
	private DriveTrain drive = Robot.drivetrain;
	public double target = 0;
	private double wheelDiameter = 6;
    public DriveForward(double targetDistance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drive);
    	target = (targetDistance/(wheelDiameter*Math.PI))*3*360;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	System.out.println(DriverStation.getInstance().getMatchTime()+"Driving Forwards");
    	drive.sensorReset();
    	Timer.delay(0.1);
    	drive.getDrive().arcadeDrive(0.7, drive.getGyro().getAngle()*(0.1));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drive.getDrive().arcadeDrive(0.7, drive.getGyro().getAngle()*(0.1));
//    	if((distanceRight()>(target*0.25)||distanceLeft()>(target*0.25))&&(distanceRight()<(target*0.8)||distanceLeft()<(target*0.8))){
//    		drive.highGear();
//    	}else {
//    		drive.lowGear();
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return (drive.getRightEncoder().get()+drive.getLeftEncoder().get())/2>=target;
    	return (drive.getRightEncoder().get()>=target||drive.getLeftEncoder().get()>=target);
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.getDrive().tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
//    private double distanceRight() {
//    	return drive.getRightEncoder().get();
//    }
//    private double distanceLeft() {
//    	return drive.getLeftEncoder().get();
//    }
}
