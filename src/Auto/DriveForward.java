package Auto;

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
	private double target = 0;
//	private double wheelDiameter = 6;
	/**
	 * 
	 * DriveForward( x1, y1, x2, y2)
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
    public DriveForward(double x1a, double y1a, double x2a, double y2a) {
    	double x1 = x1a;
    	double x2 = x2a;
    	double y1 = y1a;
    	double y2 = y2a;
    	
    	double deltaX = x2 - x1;
    	double deltaY = y2 - y1;
    	
    	double SquareDeltaX = deltaX * deltaX;
    	double SquareDeltaY = deltaY * deltaY;
    	
    	double AlmostDistance = SquareDeltaX + SquareDeltaY;
    	
    	double Distance = Math.sqrt(AlmostDistance);
    	
    	double target = Distance;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drive);
//    	target = (targetDistance/(wheelDiameter*Math.PI))*3*360;
    	
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
}
