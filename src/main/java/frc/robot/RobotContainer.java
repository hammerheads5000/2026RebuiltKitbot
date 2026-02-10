// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.FuelMechanism;
import frc.robot.subsystems.TeleopDrive;

public class RobotContainer 
{
  Drive drive = new Drive();

  FuelMechanism fuelMechanism = new FuelMechanism();

  CommandXboxController controller = new CommandXboxController(0);
  TeleopDrive teleopDrive = new TeleopDrive(drive, controller);

  Trigger shootTrigger = controller.rightTrigger();
  Trigger intakeTrigger = controller.leftTrigger();
  Trigger driveForward = controller.b();
  Trigger driveBackward = controller.a();
  Trigger spinTrigger  =  controller.y(); 

  public RobotContainer() 
  {
    drive.setDefaultCommand(teleopDrive);
    configureBindings();
  }

  private void configureBindings() 
  {
    driveForward.whileTrue(drive.driveForwardCommand(1));
    driveBackward.whileTrue(drive.driveBackwardCommand(-1));
    spinTrigger.whileTrue(drive.spinCommand(1));

    shootTrigger.whileTrue(fuelMechanism.shootCommand(2));
    intakeTrigger.whileTrue(fuelMechanism.intakeCommand(1));
  }

  public Command getAutonomousCommand() 
  {
    return Commands.print("No autonomous command configured");
  }
}
