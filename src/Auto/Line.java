package Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Line extends CommandGroup {

    public Line(double x1, double y1, double x2, double y2 ) {
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
    	addSequential(new OpeningLineMove());
    	addSequential(new DriveTurn(x1, y1, x2, y2 ));
    	addSequential(new DriveForward(x1, y1, x2, y2));
    }
}
