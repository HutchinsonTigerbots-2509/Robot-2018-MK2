/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2509.robot;


import org.usfirst.frc.team2509.robot.commands.OperatorDrive;
import org.usfirst.frc.team2509.robot.subsystems.Arm;
import org.usfirst.frc.team2509.robot.subsystems.Climber;
import org.usfirst.frc.team2509.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2509.robot.subsystems.Gripper;
import org.usfirst.frc.team2509.robot.subsystems.Intake;
import org.usfirst.frc.team2509.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically ruSn this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot{
	
	public static OI oi;//Defines the OI.java so it is easier to import
	
	//Subsystems
	public static DriveTrain drivetrain;//The DriveTrain Subsystem
	public static Arm arm;//The arm Subsystem
	public static Climber climber;//The climber subsystem
	public static Gripper gripper;//The gripper subsystem
	public static Intake intake;//The Intake subsystem
	public static Vision vision;
	//Commands
	public Command autonomousCommand;//The command we send to our robot to tell it where to go during Autonomous
	public Command operatorDrive;//The command that allows us to drive during TeleOp

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		drivetrain = new DriveTrain();
		arm = new Arm();
		climber = new Climber();
		intake =new Intake();
		gripper = new Gripper();
		vision = new Vision();
		oi = new OI();
		operatorDrive = new OperatorDrive();
		SmartDashboard.putData("Auto Chooser", oi.chooser);	
		oi.UpdateDashboard.start();
		DriverStation.reportError("Robot Ready", false);
//		vision.visionProcces.start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Scheduler.getInstance().removeAll();
    	System.out.println("Disabled");
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	
	@Override
	public void autonomousInit() {
		RobotMap.comp.stop();
		gripper.close();
		drivetrain.lowGear();
//		autonomousCommand = new AUTOTEST();
		autonomousCommand = oi.getAutonomous(oi.chooser.getSelected());
		DriverStation.reportError(DriverStation.getInstance().getGameSpecificMessage(), false);
		DriverStation.reportError(oi.chooser.getSelected(), false);
		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
    	System.out.println("Autonomous Starting");
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
//		DriverStation.reportError(oi.chooser.getSelected(), false);
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
    	System.out.println("Teleop Starting");
		operatorDrive.start();
		drivetrain.lowGear();
		RobotMap.comp.start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}