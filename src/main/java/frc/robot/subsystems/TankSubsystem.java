package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankSubsystem extends SubsystemBase {
    // Device IDs: Left 1, Right 0
    private final CANSparkMax right = new CANSparkMax(0, CANSparkLowLevel.MotorType.kBrushless);
    private final CANSparkMax left = new CANSparkMax(1, CANSparkLowLevel.MotorType.kBrushless);

    private final AHRS gyro = new AHRS();

    private double theta;

    public TankSubsystem() {
        this.theta = gyro.getAngle();
    }

    private double leftX;
    private double leftY;
    private double rightY;

    public void periodic() {
        // Max Voltage: 4

        // Average to account for controller inaccuracy
        double targetTheta = (Math.sin(leftY) + Math.cos(leftX)) / 2;
        double arc = this.theta - targetTheta;

        double rightVolatage = 2 * rightY, leftVoltage = 2 * rightY;
        if (leftX > 0) {
            leftVoltage *= Math.sin(arc);
        } else {
            rightVolatage *= Math.sin(arc);
        }

        this.theta = gyro.getAngle();
    }

    public void drive(double leftX, double leftY, double rightY) {
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightY = rightY;
    }

    public void zeroGyro() {
        gyro.reset();
    }
}
