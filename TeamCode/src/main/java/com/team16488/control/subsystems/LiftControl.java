package com.team16488.control.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.automated_proccess.StackBlocks;
import com.team16488.skystone.Robot;


public class LiftControl {

    private Robot robot;

    private boolean liftOn = true;

    private boolean lock = true;

    private boolean shift;

    private Gamepad subsystemDriver;

    private StackBlocks stackBlocks;

    private double currentTime;

    public LiftControl(OpMode opMode, Robot robot, double currentTime) {
        this.robot = robot;
        subsystemDriver = opMode.gamepad2;
        this.currentTime = currentTime;
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
            // here is the macro code 3
        }
        if (subsystemDriver.dpad_up) {
            //here is the macro code 1
        }
        if (subsystemDriver.dpad_right) {
            //here is the macro code 4
        }
        if (subsystemDriver.dpad_left) {
            //here is the macro code 2
        }

        if (shift) {
            if (subsystemDriver.dpad_down) {
                // here is the macro code 7
            }
            if (subsystemDriver.dpad_up) {
                //here is the macro code 5
            }
            if (subsystemDriver.dpad_right) {
                //here is the macro code8
            }
            if (subsystemDriver.dpad_left) {
                //here is the macro code 6
            }
        }


    }

}
