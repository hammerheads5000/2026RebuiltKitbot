// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.RPM;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import com.ctre.phoenix6.swerve.SwerveRequest;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.Constants.SwerveConstants;
import frc.robot.subsystems.FuelMechanism;

public class RobotContainer {
  
  public final CommandSwerveDrivetrain drivetrain = new CommandSwerveDrivetrain(
      SwerveConstants.DRIVETRAIN_CONSTANTS,
      SwerveConstants.FrontLeft.MODULE_CONSTANTS,
      SwerveConstants.FrontRight.MODULE_CONSTANTS,
      SwerveConstants.BackLeft.MODULE_CONSTANTS,
      SwerveConstants.BackRight.MODULE_CONSTANTS
  );

  FuelMechanism fuelMechanism = new FuelMechanism();
  CommandXboxController controller = new CommandXboxController(0);

  private final SendableChooser<Command> autoChooser; 

  private final SwerveRequest.FieldCentric swerveDrive = new SwerveRequest.FieldCentric()
      .withDeadband(SwerveConstants.LINEAR_VEL_DEADBAND.in(MetersPerSecond))
      .withRotationalDeadband(SwerveConstants.ANGLULAR_VEL_DEADBAND.in(RadiansPerSecond))
      .withDriveRequestType(SwerveConstants.DRIVE_REQUEST_TYPE);

  Trigger farShootTrigger = controller.rightTrigger();
  Trigger nearShootTrigger = controller.rightBumper();
  Trigger intakeTrigger = controller.leftTrigger();
  Trigger resetGyroTrigger = controller.y();

  public RobotContainer() {
    autoChooser = com.pathplanner.lib.auto.AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", autoChooser);

    configureBindings();

    drivetrain.setDefaultCommand(
        drivetrain.applyRequest(() -> swerveDrive
            .withVelocityX(-controller.getLeftY() * SwerveConstants.FAST_DRIVE_SPEED.in(MetersPerSecond)) 
            .withVelocityY(-controller.getLeftX() * SwerveConstants.FAST_DRIVE_SPEED.in(MetersPerSecond)) 
            .withRotationalRate(-controller.getRightX() * SwerveConstants.FAST_ROT_SPEED.in(RadiansPerSecond))
        )
    );
  }

  private void configureBindings() {
    farShootTrigger.whileTrue(fuelMechanism.shootCommand(RPM.of(4350)));
    nearShootTrigger.whileTrue(fuelMechanism.shootCommand(RPM.of(3700)));
    intakeTrigger.whileTrue(fuelMechanism.intakeCommand(RPM.of(4000)));

    resetGyroTrigger.onTrue(drivetrain.runOnce(() -> drivetrain.resetPose(new Pose2d(0, 0, new Rotation2d(0)))));
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
