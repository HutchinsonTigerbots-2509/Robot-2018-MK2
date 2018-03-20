package Auto;

import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *We use this to turn in autonomous. You set a degree you want to turn (- is left and + is right) 
 *in the () when you call this command. This will set robot to turn that specific distance 
 */
public class DriveTurn extends Command {
	private DriveTrain drive = Robot.drivetrain;
	public double target = 0;
	private double turnSpeed = 0.7;
	private Boolean turnRight;
	private Boolean turnLeft;
    public DriveTurn(double x1a, double y1a, double x2a, double y2a ) {
    	double deltaX = x2a - x1a;
    	double deltaY = y2a - y1a;
    	
    	double slope = ( deltaX / deltaY);
    	
    	double target1 = (Math.toRadians(slope));
    	
    	double target = (Math.atan(target1));
    	requires(drive);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println(DriverStation.getInstance().getMatchTime()+" - Turing to "+target);
    	drive.sensorReset();
    	Timer.delay(0.1);
    	if(drive.getGyro().getAngle()>target) {
    		turnRight = true;
    		turnLeft = false;
    		drive.getDrive().tankDrive(-0.7,0.65);
    	}else if(drive.getGyro().getAngle()<target){
    		turnRight = false;
    		turnLeft = true;
    		drive.getDrive().tankDrive(0.65, -0.7);
//    	drive.getDrive().arcadeDrive(0, 0.6);
//    	drive.getDrive().tankDrive(-0.6, 0.6);
    	}else {
    		end();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	drive.getDrive().tankDrive(-0.6, 0.6);
    	if(turnRight) {
    		drive.getDrive().tankDrive(0.6,-0.6);
    	}else if(turnLeft) {
    		drive.getDrive().tankDrive(-0.6, 0.6);
    	}else {
    		end();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(turnRight) {
    		return drive.getGyro().getAngle()<(target);
    	}else if(turnLeft) {
    		return drive.getGyro().getAngle()>(target);
    	}else {
    		return false;
    	}
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
}
