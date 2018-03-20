package Auto;

import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpeningLineMove extends Command {
	private DriveTrain drive = Robot.drivetrain;
	private double target = 5;
    public OpeningLineMove() {
    	requires(drive);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
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
