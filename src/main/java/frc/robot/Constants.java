package frc.robot;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;

public final class Constants {
    public static final double robotMass = Units.lbsToKilograms(95);
    public static final Matter chassis = new Matter(new Translation3d(0, 0, 0), robotMass);
    public static final double loopTime = 0.13; //20ms + 100ms motor controller velocity lag CHECK ARBITRARY VALUE
}
