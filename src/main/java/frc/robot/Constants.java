// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.MountPoseConfigs;
import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import com.ctre.phoenix6.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.swerve.SwerveModuleConstants.ClosedLoopOutputType;
import com.ctre.phoenix6.swerve.SwerveModuleConstants.DriveMotorArrangement;
import com.ctre.phoenix6.swerve.SwerveModuleConstants.SteerFeedbackType;
import com.ctre.phoenix6.swerve.SwerveModuleConstants.SteerMotorArrangement;
import com.ctre.phoenix6.swerve.SwerveModuleConstantsFactory;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.pathplanner.lib.config.PIDConstants;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;

public class Constants {
    // Note: Bus name must match your Tuner X configuration (e.g., "Bobby")
    public static final CANBus CAN_FD_BUS = new CANBus("Bobby");
    public static final Distance BUMPER_THICKNESS = Inches.of(3.5);

    public static class SwerveConstants {
        public static final LinearVelocity FAST_DRIVE_SPEED = MetersPerSecond.of(3.8);
        public static final AngularVelocity FAST_ROT_SPEED = RotationsPerSecond.of(4);
        public static final Distance MODULE_DISTANCE = Inches.of(23.75);

        private static final Slot0Configs STEER_GAINS = new Slot0Configs()
                .withKP(250).withKI(5).withKD(8).withKS(0.5).withKV(2.54).withKA(0.09)
                .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign);
        private static final Slot0Configs DRIVE_GAINS = new Slot0Configs()
                .withKP(0.25).withKI(0.01).withKD(0.0).withKS(0.182).withKV(0.124);

        private static final TalonFXConfiguration DRIVE_CONFIGS = new TalonFXConfiguration()
                .withCurrentLimits(new CurrentLimitsConfigs().withStatorCurrentLimit(Amps.of(120)).withStatorCurrentLimitEnable(true))
                .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake));
        private static final TalonFXConfiguration STEER_CONFIGS = new TalonFXConfiguration()
                .withCurrentLimits(new CurrentLimitsConfigs().withStatorCurrentLimit(Amps.of(60)).withStatorCurrentLimitEnable(true))
                .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake));

        private static final Pigeon2Configuration PIGEON_CONFIGS = new Pigeon2Configuration()
                .withMountPose(new MountPoseConfigs().withMountPoseYaw(Degrees.of(-90)).withMountPosePitch(Degrees.of(180)));

        public static final SwerveDrivetrainConstants DRIVETRAIN_CONSTANTS = new SwerveDrivetrainConstants()
                .withCANBusName(CAN_FD_BUS.getName()).withPigeon2Id(1).withPigeon2Configs(PIGEON_CONFIGS);

        private static final SwerveModuleConstantsFactory<TalonFXConfiguration, TalonFXConfiguration, CANcoderConfiguration> CONSTANT_CREATOR = 
            new SwerveModuleConstantsFactory<TalonFXConfiguration, TalonFXConfiguration, CANcoderConfiguration>()
                .withDriveMotorGearRatio(6.746031746031747).withSteerMotorGearRatio(21.428571428571427)
                .withCouplingGearRatio(3.5714285714285716).withWheelRadius(Inches.of(1.873))
                .withSteerMotorGains(STEER_GAINS).withDriveMotorGains(DRIVE_GAINS)
                .withSteerMotorClosedLoopOutput(ClosedLoopOutputType.TorqueCurrentFOC)
                .withDriveMotorClosedLoopOutput(ClosedLoopOutputType.Voltage).withSlipCurrent(Amps.of(80.0))
                .withSpeedAt12Volts(MetersPerSecond.of(5)).withDriveMotorType(DriveMotorArrangement.TalonFX_Integrated)
                .withSteerMotorType(SteerMotorArrangement.TalonFX_Integrated).withFeedbackSource(SteerFeedbackType.FusedCANcoder)
                .withDriveMotorInitialConfigs(DRIVE_CONFIGS).withSteerMotorInitialConfigs(STEER_CONFIGS)
                .withEncoderInitialConfigs(new CANcoderConfiguration()).withSteerInertia(KilogramSquareMeters.of(0.01))
                .withDriveInertia(KilogramSquareMeters.of(0.01)).withSteerFrictionVoltage(Volts.of(0.2))
                .withDriveFrictionVoltage(Volts.of(0.2));

        public static class FrontLeft {
            public static final SwerveModuleConstants<TalonFXConfiguration, TalonFXConfiguration, CANcoderConfiguration> MODULE_CONSTANTS = 
                CONSTANT_CREATOR.createModuleConstants(7, 3, 3, Rotations.of(-0.25830078125), MODULE_DISTANCE.div(2), MODULE_DISTANCE.div(2), false, true, false);
        }
        public static class FrontRight {
            public static final SwerveModuleConstants<TalonFXConfiguration, TalonFXConfiguration, CANcoderConfiguration> MODULE_CONSTANTS = 
                CONSTANT_CREATOR.createModuleConstants(8, 4, 4, Rotations.of(0.361083984375), MODULE_DISTANCE.div(2), MODULE_DISTANCE.div(-2), true, true, false);
        }
        public static class BackLeft {
            public static final SwerveModuleConstants<TalonFXConfiguration, TalonFXConfiguration, CANcoderConfiguration> MODULE_CONSTANTS = 
                CONSTANT_CREATOR.createModuleConstants(6, 2, 2, Rotations.of(-0.0693359375), MODULE_DISTANCE.div(-2), MODULE_DISTANCE.div(2), false, true, false);
        }
        public static class BackRight {
            public static final SwerveModuleConstants<TalonFXConfiguration, TalonFXConfiguration, CANcoderConfiguration> MODULE_CONSTANTS = 
                CONSTANT_CREATOR.createModuleConstants(5, 1, 1, Rotations.of(0.268798828125), MODULE_DISTANCE.div(-2), MODULE_DISTANCE.div(-2), true, true, false);
        }

        public static final DriveRequestType DRIVE_REQUEST_TYPE = DriveRequestType.Velocity;
        public static final LinearVelocity LINEAR_VEL_DEADBAND = MetersPerSecond.of(0.02);
        public static final AngularVelocity ANGLULAR_VEL_DEADBAND = DegreesPerSecond.of(1);
        public static final PIDConstants PP_TRANSLATIONAL_PID = new PIDConstants(5, 0.75, 0.5);
        public static final PIDConstants PP_ROTATIONAL_PID = new PIDConstants(3, 0.1, 0.1);
    }
}
