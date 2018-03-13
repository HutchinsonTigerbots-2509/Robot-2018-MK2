/*----------------------------------------------------------------------------*/


/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2509.robot;

import org.usfirst.frc.team2509.robot.commands.ArmDown;
import org.usfirst.frc.team2509.robot.commands.ArmUp;
import org.usfirst.frc.team2509.robot.commands.ClimbDown;
import org.usfirst.frc.team2509.robot.commands.ClimbUp;
import org.usfirst.frc.team2509.robot.commands.DefualtAuto;
import org.usfirst.frc.team2509.robot.commands.DropBox;
import org.usfirst.frc.team2509.robot.commands.Grip;
import org.usfirst.frc.team2509.robot.commands.IntakeIn;
import org.usfirst.frc.team2509.robot.commands.IntakeOut;
import org.usfirst.frc.team2509.robot.commands.ShiftDrive;
import org.usfirst.frc.team2509.robot.commands.ToggleUpper;
import org.usfirst.frc.team2509.robot.commands.Wheely;
import org.usfirst.frc.team2509.robot.commands.one.Auto1A_2;
import org.usfirst.frc.team2509.robot.commands.one.Auto1B_2;
import org.usfirst.frc.team2509.robot.commands.one.Auto1C_2;
import org.usfirst.frc.team2509.robot.commands.one.Auto1F_2;
import org.usfirst.frc.team2509.robot.commands.three.Auto3A_2;
import org.usfirst.frc.team2509.robot.commands.three.Auto3B_2;
import org.usfirst.frc.team2509.robot.commands.three.Auto3D_2;
import org.usfirst.frc.team2509.robot.commands.three.Auto3E_2;
import org.usfirst.frc.team2509.robot.commands.two.Auto2A_2;
import org.usfirst.frc.team2509.robot.commands.two.Auto2B_2;
import org.usfirst.frc.team2509.robot.commands.two.Auto2C_2;
import org.usfirst.frc.team2509.robot.commands.two.Auto2D_2;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick OperatorStick;
	public Joystick CoOperatorStick;
	private JoystickButton ShiftButton;
	private JoystickButton wheelyButton;
	private JoystickButton ArmUpButton;
	private JoystickButton ArmDownButton;
	private JoystickButton GripButton;
	private JoystickButton IntakeInButton;
	private JoystickButton IntakeOutButton;
	private JoystickButton RetractButton;
	private JoystickButton ClimbButton;
	private JoystickButton ClimbDownButton;
	private JoystickButton DropBoxButton;
	//private JoystickButton ParaTestButton;
	public SendableChooser<String> chooser = new SendableChooser<>();
	public String defaultAuto = "Default";
	public String X1 = "1X";
	public String AB1 = "1AB";
	public String CF1 = "1CF";
	public String IH1 = "1IH";
	public String X2 = "2X";
	public String AB2 = "2AB";
	public String CD2 = "2CD";
	public String AB3 = "3AB";
	public String DE3 = "3DE";
	public String GJ3 = "3GJ";
	private Command autoCommand;
	
	/**
	 * CREATING BUTTONS - 
	 * <p>One type of button is a joystick button which is any button on a joystick.
	 * You create one by telling it which joystick it's on and which button number it is.
	 * <p>Joystick stick = new Joystick(port);
	 * <p>Button button = new JoystickButton(stick, buttonNumber);
	 * <p>There are a few additional built in buttons you can use. Additionally,
	 * by subclassing Button you can create custom triggers and bind those to
	 * commands the same as any other Button.
	 * 
	 * <p>TRIGGERING COMMANDS WITH BUTTONS
	 * <p>Once you have a button, it's trivial to bind it to a button in one of three ways:
	 * <p>Start the command when the button is pressed and let it run the command until 
	 * it is finished as determined by it's isFinished method.
	 * <p>button.whenPressed(new ExampleCommand());
	 * <p>Run the command while the button is being held down and interrupt it once the 
	 * button is released.
	 * <p>button.whileHeld(new ExampleCommand());
	 * <p>Start the command when the button is released and let it run the command until 
	 * it is finished as determined by it's isFinished method.
	 * <p>button.whenReleased(new ExampleCommand());
	 * 
	 */
	public OI() {
		OperatorStick = new Joystick(0);
		CoOperatorStick = new Joystick(1);
		ShiftButton = new JoystickButton(OperatorStick, 2);
			ShiftButton.whenPressed(new ShiftDrive());
			
		wheelyButton = new JoystickButton(OperatorStick,3);
			wheelyButton.toggleWhenActive(new Wheely());
			
		ArmUpButton = new JoystickButton(CoOperatorStick, 4);
			ArmUpButton.whileHeld(new ArmUp());
		
		ArmDownButton = new JoystickButton(CoOperatorStick, 8);
			ArmDownButton.whileHeld(new ArmDown());
		
		RetractButton = new JoystickButton(CoOperatorStick, 7);
			RetractButton.toggleWhenPressed(new ToggleUpper());
	
		GripButton = new JoystickButton(CoOperatorStick, 1);
			GripButton.toggleWhenPressed(new Grip());
		
		IntakeInButton = new JoystickButton(OperatorStick, 1);
			IntakeInButton.whileHeld(new IntakeIn());
		
		IntakeOutButton = new JoystickButton(OperatorStick, 10);
			IntakeOutButton.whileHeld(new IntakeOut());
		
		ClimbButton = new JoystickButton(CoOperatorStick, 5);
			ClimbButton.whileHeld(new ClimbUp());
		
		ClimbDownButton = new JoystickButton(CoOperatorStick, 9);
			ClimbDownButton.whileHeld(new ClimbDown());
			
		DropBoxButton = new JoystickButton(CoOperatorStick,6);
			DropBoxButton.whileHeld(new DropBox());
			
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("1X", X1);
		chooser.addObject("1AB", AB1);
		chooser.addObject("1CF", CF1);
		chooser.addObject("1IH", IH1);
		chooser.addObject("2X", X2);
		chooser.addObject("2AB", AB2);
		chooser.addObject("2CD", CD2);
		chooser.addObject("3AB", AB3);
		chooser.addObject("3DE", DE3);
		chooser.addObject("3GJ", GJ3);
		SmartDashboard.putData("Auto Chooser", chooser);
		

		SmartDashboard.putData(new ShiftDrive());
		SmartDashboard.putData(new ToggleUpper());
		SmartDashboard.putData(new Grip());
		SmartDashboard.putData(new IntakeIn());
		SmartDashboard.putData(new IntakeOut());
		SmartDashboard.putData(new ClimbUp());
		SmartDashboard.putData(new ClimbDown());
		SmartDashboard.putData(new DropBox());
		SmartDashboard.putData(new Wheely());
	}
	/**
	 * When called constantly updates the SmartDashboard
	 */
	public Thread UpdateDashboard = new Thread(()->{
		while(true) {
			SmartDashboard.putData("Auto Chooser", chooser);
			SmartDashboard.putNumber("Left Encoder", Robot.drivetrain.getLeftEncoder().get());
			SmartDashboard.putNumber("Right Encoder", Robot.drivetrain.getRightEncoder().get());
			SmartDashboard.putNumber("Gyro", Robot.drivetrain.getGyro().getAngle());
			SmartDashboard.putNumber("Accel", Robot.drivetrain.getGyro().getRawAccelY());
//    		SmartDashboard.putNumber("Left Motors", Robot.drivetrain.getLeft().get());
//    		SmartDashboard.putNumber("Right Motors", Robot.drivetrain.getRight().get());
			
		}
	});
	public Command getAutonomous(String autoChoice){
		
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		SmartDashboard.putString("Game Data", gameData);
		SmartDashboard.putString("AutoSelect", autoChoice);
		switch(autoChoice) {
		case "1X":
			autoCommand = null;
			break;
		case "1AB":
			if(gameData.length()>0) {
				if(gameData.charAt(0)=='L') {
					autoCommand = new Auto1A_2();
				}else if(gameData.charAt(0)=='R') {
					autoCommand = new Auto1B_2();
				}
			}
			break;
		case "1CF":
			if(gameData.length()>0) {
				if(gameData.charAt(0)=='L') {
					autoCommand = new Auto1C_2();
				}else if(gameData.charAt(0)=='R') {
					autoCommand = new Auto1F_2();
				}
			}
			break;
		case "2X":
			autoCommand = null;
			break;
		case "2AB":
			if(gameData.length()>0) {
				if(gameData.charAt(0)=='L') {
					autoCommand = new Auto2A_2();
				}else if(gameData.charAt(0)=='R') {
					autoCommand = new Auto2B_2();
				}
			}
			break;
		case "2CD":
			if(gameData.length()>0) {
				if(gameData.charAt(0)=='L') {
					autoCommand = new Auto2C_2();
				}else if(gameData.charAt(0)=='R') {
					autoCommand = new Auto2D_2();
				}
			}
			break;
		case "3AB":
			if(gameData.length()>0) {
				if(gameData.charAt(0)=='L') {
					autoCommand = new Auto3A_2();
					SmartDashboard.putString("Auto", "3A");
				}else if(gameData.charAt(0)=='R') {
					autoCommand = new Auto3B_2();
					SmartDashboard.putString("Auto", "3B");
				}
			}
			break;
		case "3DE":
			if(gameData.length()>0) {
				if(gameData.charAt(0)=='L') {
					autoCommand = new Auto3E_2();
					SmartDashboard.putString("Auto", "3E");
				}else if(gameData.charAt(0)=='R') {
					SmartDashboard.putString("Auto", "3D");
					autoCommand = new Auto3D_2();
				}
			}
			break;
		case "Default":
			default:
				autoCommand = new DefualtAuto();
				break;
		}
		return autoCommand;
			
	}
}
