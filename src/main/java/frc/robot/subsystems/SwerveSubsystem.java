package frc.robot.subsystems;

import java.io.File;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveDriveConfiguration;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;

public class SwerveSubsystem extends SubsystemBase {
    private final SwerveDrive swerveDrive;

    public SwerveSubsystem(File directory) {
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;

        try {
            swerveDrive = new SwerveParser(directory).createSwerveDrive();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop) {
        swerveDrive.drive(translation, rotation, fieldRelative, isOpenLoop);
    }

    public Rotation2d getHeading() {
        return swerveDrive.getYaw();
    }

    public ChassisSpeeds getTargetSpeeds(double xInput, double yInput, Rotation2d angle) {
        xInput = Math.pow(xInput, 3);
        yInput = Math.pow(yInput, 3);
        return swerveDrive.swerveController.getTargetSpeeds(xInput, yInput, angle.getRadians(), getHeading().getRadians());
    }

    public void setChassisSpeeds(ChassisSpeeds chassisSpeeds) {
        swerveDrive.setChassisSpeeds(chassisSpeeds);
    }

    public Pose2d getPose() {
        return swerveDrive.getPose();
    }

    public SwerveDriveConfiguration getSwerveDriveConfiguration() {
        return swerveDrive.swerveDriveConfiguration;
    }

    public ChassisSpeeds getFieldVelocity() {
        return swerveDrive.getFieldVelocity();
    }

    public void postTrajectory(Trajectory trajectory) {
        swerveDrive.postTrajectory(trajectory);
    }

    public void lock() {
        swerveDrive.lockPose();
    }

    @Override
    public void periodic() {
        swerveDrive.updateOdometry();
    }
}
