// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  TalonSRX leftFrontMotor = new TalonSRX(0);
  TalonSRX leftBackmotor = new TalonSRX(0);
  TalonSRX rightFrontMotor = new TalonSRX(0);
  TalonSRX rightBackMotor = new TalonSRX(0);

  /** Creates a new Drive. */
  public Drive() {
    leftFrontMotor.setInverted(false);
    leftBackmotor.setInverted(false);
    rightFrontMotor.setInverted(true);
    rightBackMotor.setInverted(true);

    leftFrontMotor.setNeutralMode(NeutralMode.Brake);
    leftBackmotor.setNeutralMode(NeutralMode.Brake);
    rightFrontMotor.setNeutralMode(NeutralMode.Brake);
    rightBackMotor.setNeutralMode(NeutralMode.Brake);

    leftFrontMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 40, 0.5));
    leftBackmotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 40, 0.5));
    rightFrontMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 40, 0.5));
    rightBackMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 40, 0.5));
  }

  public void setSpeeds(double leftSpeed, double rightSpeed) {
        leftFrontMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, leftSpeed);
        leftBackmotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, leftSpeed);
        rightFrontMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, rightSpeed);
        rightBackMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, rightSpeed);
    }

  public void stop() {
        leftFrontMotor.neutralOutput();
        leftBackmotor.neutralOutput();
        rightFrontMotor.neutralOutput();
        rightBackMotor.neutralOutput();
    }

  public Command driveForwardCommand(double speed) {
        return this.startEnd(() -> setSpeeds(speed, speed), this::stop);
    }

  public Command driveBackwardCommand(double speed) {
        return this.startEnd(() -> setSpeeds(-speed, -speed), this::stop);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
