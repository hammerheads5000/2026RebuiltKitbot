// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class TeleopDrive extends Command {
    private final Drive drive;
    private final CommandXboxController controller;

    public TeleopDrive(Drive drive, CommandXboxController controller) {
        this.drive = drive;
        this.controller = controller;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        double leftSpeed = -controller.getLeftY();
        double rightSpeed = -controller.getRightY();

        // deadband to prevent joystick noise
        if (Math.abs(leftSpeed) < 0.05) leftSpeed = 0;
        if (Math.abs(rightSpeed) < 0.05) rightSpeed = 0;

        drive.setSpeeds(leftSpeed, rightSpeed);
    }

    @Override
    public boolean isFinished() {
        return false; // runs continuously during teleop
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }
}
