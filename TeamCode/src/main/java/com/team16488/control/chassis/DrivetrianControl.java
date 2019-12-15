package com.team16488.control.chassis;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.skystone.Robot;

public class DrivetrianControl {
    private Robot robot;

    private Gamepad chassisControl, subsystemChassisControl;

    private boolean crainmode;

    public DrivetrianControl(OpMode opMode, Robot oprobot) {
        robot = oprobot;
        chassisControl = opMode.gamepad1;
        subsystemChassisControl = opMode.gamepad2;
    }

    public void driveControl() {
        robot.drive.setVelocity(chassisControl.left_stick_x, -chassisControl.left_stick_y, chassisControl.right_stick_x);

        if (chassisControl.right_stick_x == 0 && chassisControl.left_stick_y == 0 && chassisControl.left_stick_x == 0) {
            double slowmode = 0.5;
            robot.drive.setVelocity(-subsystemChassisControl.left_stick_x * slowmode, -subsystemChassisControl.right_stick_y * slowmode, -subsystemChassisControl.right_stick_x * slowmode);
        }
    }
}
