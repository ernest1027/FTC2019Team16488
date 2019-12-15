package com.team16488.control.chassis;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.skystone.Robot;

public class PullerControl {
    private Robot robot;


    private Gamepad chassisControl;

    public PullerControl(OpMode opMode, Robot oprobot) {
        robot = oprobot;
        chassisControl = opMode.gamepad1;
    }

    public void pullerControl() {

        if (chassisControl.left_trigger != 0) {
            robot.puller.setDown(true);
        }

        if (chassisControl.left_bumper) {
            robot.puller.setDown(false);
        }

    }

}
