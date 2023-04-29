// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.AbsoluteFieldDrive;
import frc.robot.subsystems.SwerveSubsystem;

import java.io.File;

public class RobotContainer {
  File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(), "swerve");
  SwerveSubsystem driveBase = new SwerveSubsystem(swerveJsonDirectory);
  CommandXboxController controller = new CommandXboxController(0);
  
  AbsoluteFieldDrive drive = new AbsoluteFieldDrive(driveBase, () -> controller.getLeftX(), () -> controller.getLeftY(), () -> controller.getRightX(), false);

  public RobotContainer() {
    configureBindings();
    driveBase.setDefaultCommand(drive);
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
