// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class TeleopDrive extends Command 
{
  Drive drive;
  CommandXboxController controller;

  /** Creates a new TeleopDrive. */
  public TeleopDrive(Drive drive, CommandXboxController controller) 
  {
    this.drive = drive;
    this.controller = controller;
    addRequirements(drive);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() 
  {
    double horizontalSpeed = -controller.getRightX();
    double verticalSpeed = -controller.getRightY();

    drive.setSpeeds(verticalSpeed + horizontalSpeed, verticalSpeed - horizontalSpeed);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() 
  {
    return false;
  }
}
