// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FuelMechanism extends SubsystemBase {
  TalonSRX leftFuelMotor = new TalonSRX(0);
  TalonSRX rightFuelMotor = new TalonSRX(0);

  /** Creates a new FuelMechanism. */
  public FuelMechanism() {
    leftFuelMotor.setInverted(false);
    rightFuelMotor.setInverted(false);

    leftFuelMotor.setNeutralMode(NeutralMode.Brake);
    rightFuelMotor.setNeutralMode(NeutralMode.Brake);

    leftFuelMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 40, 0.5));
    rightFuelMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 40, 0.5));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
