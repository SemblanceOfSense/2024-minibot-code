package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankSubsystem;

public class DriveCommands extends Command {
    private final TankSubsystem tankSubsystem;
    private double leftX;
    private double leftY;
    private double rightY;

    public DriveCommands(TankSubsystem tankSubsystem, double leftX, double leftY, double rightY) {
        this.tankSubsystem = tankSubsystem;
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightY = rightY;
    }

    public void execute() {
        double leftXDesired = MathUtil.applyDeadband(leftX, 0.06);
        double leftYDesired = MathUtil.applyDeadband(leftY, 0.06);
        double rightYDesired = MathUtil.applyDeadband(rightY, 0.06);

        tankSubsystem.drive(leftXDesired, leftYDesired, rightYDesired);
    }

    public void end(boolean interrupted) { tankSubsystem.drive(0, 0, 0); }
}
