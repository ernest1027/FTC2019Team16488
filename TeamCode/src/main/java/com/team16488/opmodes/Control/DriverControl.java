package com.team16488.opmodes.Control;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.team16488.skystone.Robot;

@Disabled
public class DriverControl extends OpMode {
    double slowmode = 0.8;
    boolean isOn = false;
    boolean reverse = false;
    private Robot robot;

    @Override
    public void init() {
    }

    @Override
    public void loop() {
    }

    public void driverPad() {


        robot.drive.setVelocity(-gamepad1.left_stick_x, -gamepad1.left_stick_y, -gamepad1.right_stick_x);

        if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0 && gamepad1.right_stick_x == 0) {
            robot.drive.setVelocity(-gamepad2.left_stick_x * slowmode, gamepad2.left_stick_y * slowmode, -gamepad2.right_stick_x * slowmode);
        }


        if (gamepad1.y) {
            reverse = false;
            isOn = true;
        }
        if (isOn) {
            robot.intake.setOn(true);
        }

        if (gamepad1.a) {
            isOn = false;
        }

        if (!isOn) {
            robot.intake.setOn(false);
        }

        if (gamepad1.x) {
            reverse = true;
        }

        if (reverse) {
            robot.intake.setReverse(true);
        }

        if (gamepad1.right_trigger != 0) {
            robot.puller.setDown(true);
        }

        if (gamepad1.right_bumper) {
            robot.puller.setDown(false);
        }

    }
}
