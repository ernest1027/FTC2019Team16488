package com.team16488.control.chassis;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.skystone.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeControl {
    /**
     * This is the robot object
     */
    private Robot robot;
    /**
     * sets the intake on reverse mode
     */
    private boolean reverse = false;
    /**
     * this turns the intake on
     */
    private boolean On = false;

    private Telemetry telemetry;

    private Gamepad chassisControl;

    public IntakeControl(OpMode opMode, Robot oprobot) {
        robot = oprobot;
        chassisControl = opMode.gamepad1;
        telemetry = opMode.telemetry;

    }

    public void intakeCOntrol() {
        if (chassisControl.x) {
            On = false;
            reverse = false;

        }
        if (chassisControl.right_bumper) {
            On = true;

        }
        if (chassisControl.right_trigger != 0) {
            On = true;
            reverse = false;
        }
        if (chassisControl.right_bumper) {
            reverse = true;
        }

        if (On) {
            robot.intake.setOn(true);
            telemetry.addData("state", "Intake on");
        }
        if (!On) {
            robot.intake.setOn(false);
            telemetry.addData("state", "Intake off");
        }

        robot.intake.setReverse(reverse);
    }

}
