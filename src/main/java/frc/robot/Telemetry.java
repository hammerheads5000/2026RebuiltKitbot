// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.swerve.SwerveDrivetrain.SwerveDriveState;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.networktables.DoubleArrayPublisher;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Telemetry {
    private final NetworkTableInstance inst = NetworkTableInstance.getDefault();
    private final NetworkTable table = inst.getTable("SwerveTelemetry");
    
    private final DoubleArrayPublisher robotPosePub = table.getDoubleArrayTopic("RobotPose").publish();
    private final DoubleArrayPublisher moduleStatesPub = table.getDoubleArrayTopic("ModuleStates").publish();
    private final DoubleArrayPublisher moduleTargetsPub = table.getDoubleArrayTopic("ModuleTargets").publish();
    private final DoublePublisher odometryPeriodPub = table.getDoubleTopic("OdometryPeriod").publish();

    public Telemetry(double maxSpeedMetersPerSecond) {
    }

    public void telemeterize(SwerveDriveState state) {
        Pose2d pose = state.Pose;
        robotPosePub.set(new double[] {pose.getX(), pose.getY(), pose.getRotation().getDegrees()});

        moduleStatesPub.set(new double[] {
            state.ModuleStates[0].angle.getDegrees(), state.ModuleStates[0].speedMetersPerSecond,
            state.ModuleStates[1].angle.getDegrees(), state.ModuleStates[1].speedMetersPerSecond,
            state.ModuleStates[2].angle.getDegrees(), state.ModuleStates[2].speedMetersPerSecond,
            state.ModuleStates[3].angle.getDegrees(), state.ModuleStates[3].speedMetersPerSecond
        });

        moduleTargetsPub.set(new double[] {
            state.ModuleTargets[0].angle.getDegrees(), state.ModuleTargets[0].speedMetersPerSecond,
            state.ModuleTargets[1].angle.getDegrees(), state.ModuleTargets[1].speedMetersPerSecond,
            state.ModuleTargets[2].angle.getDegrees(), state.ModuleTargets[2].speedMetersPerSecond,
            state.ModuleTargets[3].angle.getDegrees(), state.ModuleTargets[3].speedMetersPerSecond
        });

        odometryPeriodPub.set(state.OdometryPeriod);
        SmartDashboard.putNumber("Robot X", pose.getX());
        SmartDashboard.putNumber("Robot Y", pose.getY());
        SmartDashboard.putNumber("Robot Heading", pose.getRotation().getDegrees());
    }
}
