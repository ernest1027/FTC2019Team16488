package com.team16488.control.chassis;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.skystone.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class AlternateIntakeControl {
    private Robot robot;

    private Gamepad chassisControl;

    private Telemetry telemetry;

    public AlternateIntakeControl(OpMode opMode, Robot oprobot) {
        robot = oprobot;
        chassisControl = opMode.gamepad1;
        telemetry = opMode.telemetry;
    }

    public void alternateIntakeControl() {
        if (chassisControl.dpad_up) {
            robot.alternateIntake.setPos(-0.5);
        }
        if (chassisControl.start) {
            robot.alternateIntake.ON = true;
        }
        if (chassisControl.b) {
            robot.alternateIntake.setDown(true);
        }
        if (chassisControl.y) {
            robot.alternateIntake.setDown(false);
        }
        if (robot.alternateIntake.state) {
            telemetry.addData("Alternate intake state", "No Block");

        }
        if (!robot.alternateIntake.state) {
            telemetry.addData("Alternate intake state", "You Have Block");

        }
    }
}
