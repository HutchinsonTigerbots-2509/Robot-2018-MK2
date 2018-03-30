package org.usfirst.frc.team2509.robot.commands.one;

import org.usfirst.frc.team2509.robot.commands.ArmDown;
import org.usfirst.frc.team2509.robot.commands.ArmUp;
import org.usfirst.frc.team2509.robot.commands.BoxPickup;
import org.usfirst.frc.team2509.robot.commands.DriveForward;
import org.usfirst.frc.team2509.robot.commands.DriveTurn;
import org.usfirst.frc.team2509.robot.commands.DriveTurnCorrection;
import org.usfirst.frc.team2509.robot.commands.OpenGripper;
import org.usfirst.frc.team2509.robot.commands.driveBackward;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *1		 C
 *		A |-----| E
 *		  |     |
 *		  |     |
 *2		  |     |
 *		B |-----| F
 *3			 D
 *
 *We are going to the 'C' position from '1'
 */
public class Auto1C_3_2Box extends CommandGroup {

    public Auto1C_3_2Box() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	//Reason

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new DriveForward(125));
    	addSequential(new DriveTurn(90));
    	addParallel(new ArmUp());
    	addSequential(new DriveTurnCorrection(90));
    	addSequential(new DriveForward(5));
    	//Drop Box
    	addSequential(new OpenGripper());
    	//
    	addSequential(new driveBackward(5));
    	addSequential(new ArmDown());
    	//Start of box 2
    	addSequential(new DriveTurn(-90));
    	addSequential(new DriveTurnCorrection(-90));
    	addSequential(new DriveForward(35));
    	addSequential(new DriveTurn(90));
    	addSequential(new DriveTurnCorrection(90));
    	addSequential(new DriveForward(20));
    	addSequential(new DriveTurn(90));
    	addSequential(new DriveTurnCorrection(90));
    	addSequential(new BoxPickup());
    	addParallel(new ArmUp());
    	addSequential(new driveBackward(10));
    	addSequential(new DriveForward(5));
    	addSequential(new OpenGripper());
    	addSequential(new driveBackward(10));
    	addSequential(new ArmDown());
    }
}
