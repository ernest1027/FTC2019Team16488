package com.team16488.control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.skystone.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ChassisControl {
    private Robot robot;
    private boolean reverse = false;
    private boolean On = false;

    public ChassisControl(OpMode opMode, Telemetry telemetry) {
        robot = new Robot(opMode, telemetry);
    }

    public void driverPad(Gamepad gamepad1, Gamepad gamepad2, Telemetry telemetry) {

        robot.drive.setVelocity(-gamepad1.left_stick_x, -gamepad1.left_stick_y, -gamepad1.right_stick_x);

        if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0 && gamepad1.right_stick_x == 0) {
            double slowmode = 0.2;
            robot.drive.setVelocity(-gamepad2.left_stick_x * slowmode, gamepad2.left_stick_y * slowmode, -gamepad2.right_stick_x * slowmode);
        }


        if (gamepad1.y) {
            On = true;
        }
        if (On) {
            robot.intake.setOn(true);
            telemetry.addData("state", "Intake on");
        }

        if (gamepad1.a) {
            On = false;
            reverse = false;
        }

        if (!On) {
            robot.intake.setOn(false);
            telemetry.addData("state", "Intake off");
        }

        if (gamepad1.x) {
            reverse = true;
        }

        if (reverse) {
            robot.intake.setReverse(true);
            telemetry.addData("state", "Intake reverse");
        }

        if (gamepad1.right_trigger != 0) {
            robot.puller.setDown(true);
        }

        if (gamepad1.right_bumper) {
            robot.puller.setDown(false);
        }


    }
}
