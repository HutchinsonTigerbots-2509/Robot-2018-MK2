
package org.usfirst.frc.team2509.robot.commands;

import org.usfirst.frc.team2509.robot.Robot;
import org.usfirst.frc.team2509.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmHigh_2 extends Command {
	private Arm arm = Robot.arm;
    public ArmHigh_2() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println(DriverStation.getInstance().getMatchTime()+"Arm High");
    	arm.retractUpper();
    	arm.extendLower();
    	arm.Up();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !arm.getUpperLimit().get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	arm.Stop();
    	if(!arm.getUpperLimit().get()) arm.extendUpper();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
