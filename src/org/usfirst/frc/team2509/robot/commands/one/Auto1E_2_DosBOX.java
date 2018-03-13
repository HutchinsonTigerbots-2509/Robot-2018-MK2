package org.usfirst.frc.team2509.robot.commands.one;

import org.usfirst.frc.team2509.robot.commands.ArmHome;
import org.usfirst.frc.team2509.robot.commands.ArmMid_2;
import org.usfirst.frc.team2509.robot.commands.CloseGripper;
import org.usfirst.frc.team2509.robot.commands.DriveForward;
import org.usfirst.frc.team2509.robot.commands.DriveTurn;
import org.usfirst.frc.team2509.robot.commands.DropBox;
import org.usfirst.frc.team2509.robot.commands.IntakeIn;
import org.usfirst.frc.team2509.robot.commands.OpenGripper;
import org.usfirst.frc.team2509.robot.commands.driveBackward;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto1E_2_DosBOX extends CommandGroup {

    public Auto1E_2_DosBOX() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

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
    	//Hey i need this to push?
    	addSequential(new DriveForward(210));
    	addSequential(new DriveTurn(90));
    	addParallel(new ArmMid_2());
    	addSequential(new DriveForward(50));
    	addSequential(new DriveTurn(90));
    	addSequential(new DriveForward(2));
    	addSequential(new DropBox());
    	addSequential(new driveBackward(20));
    	addSequential(new ArmHome());
    	addSequential(new OpenGripper());
    	addSequential(new DriveTurn(30));
    	addParallel(new IntakeIn());
    	addSequential(new DriveForward(5));
    	addSequential(new CloseGripper());
    	addParallel(new ArmMid_2());
    	addSequential(new driveBackward(5));
    	addSequential(new DriveTurn(-30));
    	addSequential(new DriveForward(5));
    	addSequential(new DropBox());
    	addSequential(new driveBackward());
    	addSequential(new ArmMid_2());
    }
}
