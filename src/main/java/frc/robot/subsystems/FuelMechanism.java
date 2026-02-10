// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FuelMechanism extends SubsystemBase 
{
  TalonSRX intakeAndShooterMotor = new TalonSRX(11);
  TalonSRX deciderFuelMotor = new TalonSRX(1);

  /** Creates a new FuelMechanism. */
  public FuelMechanism() 
  {
    intakeAndShooterMotor.setInverted(false);
    deciderFuelMotor.setInverted(false);

    intakeAndShooterMotor.setNeutralMode(NeutralMode.Brake);
    deciderFuelMotor.setNeutralMode(NeutralMode.Brake);

    intakeAndShooterMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 40, 0.5));
    deciderFuelMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 40, 0.5));
  }

  public void setSpeeds(double leftSpeed, double rightSpeed) 
  {
    intakeAndShooterMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, leftSpeed);
    deciderFuelMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, rightSpeed);
  }

  public void stop() 
  {
    intakeAndShooterMotor.neutralOutput();
    deciderFuelMotor.neutralOutput();
  }  

  public Command shootCommand(double speed) 
  {
    return this.startEnd(() -> setSpeeds(speed, -speed), this::stop);
  }

  public Command intakeCommand(double speed) 
  {
    return this.startEnd(() -> setSpeeds(speed, speed), this::stop);
  }


  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
}
