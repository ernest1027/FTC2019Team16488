package com.team16488.opmodes.joysticks;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.team16488.skystone.Robot;

@Disabled
public class SubsystemControl extends OpMode {
    double vpower = 0.0;
    double hpower = 0.0;
    boolean clawOpen;
    private Robot robot;

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    public void subsetemControl() {

        if (gamepad2.left_bumper) {
            robot.lift.setGoingUp(true);
        }
        if (gamepad2.right_bumper) {
            robot.lift.setGoingUp(false);
        }


        if (gamepad2.dpad_up) {
            vpower += 0.1;
        }
        if (gamepad2.dpad_down) {
            vpower -= 0.1;
        }
        if (gamepad2.dpad_left) {
            hpower += 0.1;
        }

        if (gamepad2.dpad_right) {
            hpower -= 0.1;
        }

        robot.clawHead.setverticalRotation(vpower);
        robot.clawHead.sethorizontalRotationPosition(hpower);

        telemetry.addData("vpower", vpower);
        telemetry.addData("hpower", hpower);


        //swich gampad 1 to gamepad2
        if (gamepad2.a) {
            clawOpen = true;
        }
        if (gamepad2.b) {
            clawOpen = false;
        }
        if (clawOpen) {
            robot.clawHead.setOpen(true);
        }
        if (!clawOpen) {
            robot.clawHead.setOpen(false);
        }


        robot.arm.setPower(-gamepad2.right_stick_y);


    }

}
