// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FuelMechanism extends SubsystemBase {
  VoltageOut voltageRequest = new VoltageOut(0);
  TalonFX intakeAndShooterMotor = new TalonFX(16);
  TalonSRX deciderFuelMotor = new TalonSRX(1);
  DoubleEntry speedEntry = NetworkTableInstance.getDefault().getDoubleTopic("name").getEntry(0);

  /** Creates a new FuelMechanism. */
  public FuelMechanism() {
    deciderFuelMotor.setInverted(false);
    deciderFuelMotor.setNeutralMode(NeutralMode.Brake);
    deciderFuelMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 40, 0.5));
  MotorOutputConfigs outputConfigs = new MotorOutputConfigs()
        .withInverted(InvertedValue.CounterClockwise_Positive)
        .withNeutralMode(NeutralModeValue.Brake);
    speedEntry.set(0);
    intakeAndShooterMotor.getConfigurator().apply(outputConfigs);
}

  public void setSpeeds(double leftSpeed, double rightSpeed) {
        intakeAndShooterMotor.setControl(voltageRequest.withOutput(leftSpeed));
        deciderFuelMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, rightSpeed);
    }

  public void stop() {
        deciderFuelMotor.neutralOutput();
    }  

  public Command shootCommand(double speed) {
    return this.startEnd(() -> setSpeeds(speed, -speed), this::stop);
  }

  public Command intakeCommand(double speed) {
    return this.startEnd(() -> setSpeeds(speed, speed), this::stop);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
