package org.usfirst.frc.team2509.robot.commands.two;

import org.usfirst.frc.team2509.robot.commands.DriveForward;
import org.usfirst.frc.team2509.robot.commands.DriveTurn;
import org.usfirst.frc.team2509.robot.commands.driveBackward;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *1			 C
 *		A |-----| E
 *		  |     |
 *X		  |     |
 *2		  |     |
 *		B |-----| F
 *3			 D
 *
 *There is a vault next to '2'. We put a push a box into there and then go grab another from the group in between
 *positions 'A' and 'B'. After gaining another box, we put the box into the same vault as before.
 */
public class Auto2X_2 extends CommandGroup {

    public Auto2X_2() {
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
    	
    	addSequential(new DriveForward(30));
    	addSequential(new DriveTurn(-90));
    	addSequential(new DriveForward(10));
    	addSequential(new DriveTurn(-90));
    	addSequential(new DriveForward(25));
//    	Timer.delay(3); Sorry didn't know how this would work out since it is not a command
    	
    	addSequential(new driveBackward(30));
    	addSequential(new DriveTurn(90));
    	addSequential(new DriveForward(90));
    	addSequential(new DriveTurn(90));
    	addSequential(new DriveForward(135));
    	addSequential(new DriveTurn(-90));
    	addSequential(new DriveForward(10));
    	addSequential(new DriveTurn(-90));
    	addSequential(new DriveForward(10));
    }
}
