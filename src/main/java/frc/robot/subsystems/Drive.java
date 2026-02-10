// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase 
{
  TalonSRX leftMotor = new TalonSRX(2);
  TalonSRX rightMotor = new TalonSRX(3);

  /** Creates a new Drive. */
  public Drive() 
  {
    leftMotor.setInverted(false);
    rightMotor.setInverted(true);
    leftMotor.setNeutralMode(NeutralMode.Brake);
    rightMotor.setNeutralMode(NeutralMode.Brake);

    leftMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 40, 0.5));
    rightMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 40, 0.5));
  }

  public void setSpeeds(double leftSpeed, double rightSpeed) 
  {
    leftMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, leftSpeed);
    rightMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, rightSpeed);
  }

  public void stop() 
  {
    leftMotor.neutralOutput();
    rightMotor.neutralOutput();
  }

  public Command driveForwardCommand(double speed) 
  {
    return this.startEnd(() -> setSpeeds(speed, speed), this::stop);
  }

  public Command driveBackwardCommand(double speed) 
  {
    return this.startEnd(() -> setSpeeds(-speed, -speed), this::stop);
  }

  public Command spinCommand(double speed)
  {
    return this.startEnd(() -> setSpeeds(speed, -speed), this::stop);
  }  

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
}
