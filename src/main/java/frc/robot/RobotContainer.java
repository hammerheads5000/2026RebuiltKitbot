// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.RPM;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.FuelMechanism;
import frc.robot.subsystems.TeleopDrive;

public class RobotContainer {
  Drive drive = new Drive();
  FuelMechanism fuelMechanism = new FuelMechanism();
  CommandXboxController controller = new CommandXboxController(0);
  TeleopDrive teleopDrive = new TeleopDrive(drive, controller);
  Trigger farShootTrigger = controller.rightTrigger();
  Trigger nearShootTrigger = controller.rightBumper();
  Trigger intakeTrigger = controller.leftTrigger();
  Trigger driveForwardTrigger = controller.a();
  Trigger driveBackwardTrigger = controller.b();
  Trigger cSpinTrigger  =  controller.y(); 
  Trigger ccSpinTrigger  =  controller.x();

  public RobotContainer() {
    drive.setDefaultCommand(teleopDrive);
    configureBindings();
  }

  private void configureBindings() {
    driveForwardTrigger.whileTrue(drive.driveForwardCommand(0.4));
    driveBackwardTrigger.whileTrue(drive.driveBackwardCommand(0.4));
    cSpinTrigger.whileTrue(drive.spinCommand(0.7));
    ccSpinTrigger.whileTrue(drive.spinCommand(-0.7));
    farShootTrigger.whileTrue(fuelMechanism.shootCommand(RPM.of(4350)));
    nearShootTrigger.whileTrue(fuelMechanism.shootCommand(RPM.of(3700)));
    intakeTrigger.whileTrue(fuelMechanism.intakeCommand(RPM.of(4000)));

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
