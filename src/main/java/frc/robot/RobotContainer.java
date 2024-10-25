package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveCommands;
import frc.robot.subsystems.TankSubsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class RobotContainer {
    TankSubsystem tankSubsystem = new TankSubsystem();

    XboxController primaryController = new XboxController(0);

    public RobotContainer() {
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        tankSubsystem.setDefaultCommand(
            new DriveCommands(tankSubsystem, primaryController.getLeftX(), primaryController.getLeftY(), primaryController.getRightY())
        );

        // Zero gyro: Y button
        new JoystickButton(primaryController, XboxController.Button.kY.value).whileTrue(
        new RunCommand(() -> tankSubsystem.zeroGyro())
    );
    }
}
