package com.team16488.control.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.skystone.Robot;

public class LiftControl {

    private Robot robot;

    private boolean liftOn = true;

    private boolean lock = true;

    private boolean shift;

    private Gamepad subsystemDriver;

    public LiftControl(OpMode opMode, Robot robot) {
        this.robot = robot;
        subsystemDriver = opMode.gamepad2;

    }

    public void liftControl() {

        robot.lift.setOn(liftOn);

        if (lock) {
            robot.lift.setPower(0.1);
        }

        if (subsystemDriver.left_bumper) {
            robot.lift.setPower(0.85);
        }

        if (subsystemDriver.left_trigger != 0) {
            robot.lift.setPower(-0.85);
        }

        if (subsystemDriver.right_trigger != 0) {
            shift = true;
        }

        if (subsystemDriver.dpad_down) {
            // here is the macro code
        }
        if (subsystemDriver.dpad_up) {
            //here is the macro code
        }
        if (subsystemDriver.dpad_right) {
            //here is the macro code
        }
        if (subsystemDriver.dpad_left) {
            //here is the macro code
        }

        if (shift) {
            if (subsystemDriver.dpad_down) {
                // here is the macro code
            }
            if (subsystemDriver.dpad_up) {
                //here is the macro code
            }
            if (subsystemDriver.dpad_right) {
                //here is the macro code
            }
            if (subsystemDriver.dpad_left) {
                //here is the macro code
            }
        }


    }

}
