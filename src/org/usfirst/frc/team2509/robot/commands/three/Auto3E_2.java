package org.usfirst.frc.team2509.robot.commands.three;

import org.usfirst.frc.team2509.robot.commands.ArmHome;
import org.usfirst.frc.team2509.robot.commands.ArmMid_2;
import org.usfirst.frc.team2509.robot.commands.DriveForward;
import org.usfirst.frc.team2509.robot.commands.DriveTurn;
import org.usfirst.frc.team2509.robot.commands.OpenGripper;
import org.usfirst.frc.team2509.robot.commands.WristHalfDown;
import org.usfirst.frc.team2509.robot.commands.WristUp;
import org.usfirst.frc.team2509.robot.commands.driveBackward;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto3E_2 extends CommandGroup {

    public Auto3E_2() {
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
//    	addSequential(new DriveForward(60));
//    	addSequential(new DriveTurn(-90));
//    	addSequential(new DriveForward(240)); 
//    	addSequential(new DriveTurn(90));
//    	addSequential(new DriveForward(180));
//    	addSequential(new DriveTurn(90));
//    	addParallel(new ArmMid_2());
//    	addSequential(new DriveForward(60));
//    	addSequential(new DriveTurn(90));
//    	addSequential(new DriveForward(30));
//    	addSequential(new DropBox());
//    	addSequential(new driveBackward(15));
//    	addSequential(new ArmHome());
    	addSequential(new DriveForward(210));
    	addSequential(new DriveTurn(-90));
    	addParallel(new ArmMid_2());
    	addSequential(new DriveForward(163)); 
    	addSequential(new ArmMid_2());
    	addSequential(new DriveTurn(-90));
    	addSequential(new DriveForward(15));
    	//Drop Box 
    	addSequential(new WristHalfDown());
    	addSequential(new OpenGripper());
    	addSequential(new WristUp());
    	//
    	addSequential(new driveBackward(15));
    	addSequential(new ArmHome());
    }
}
