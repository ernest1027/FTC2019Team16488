package com.team16488.control.chassis;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.skystone.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This is the class that controls the movement of the intake.
 *
 * @author Parham Baghbanbashi: parhambagh@gmail.com
 * @author Ernest Wong
 *
 * <p>github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi</p>
 */
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

    private boolean yeet = false;

    private Telemetry telemetry;

    private Gamepad chassisControl;

    private Gamepad yeetControl;

    /**
     * This is the class constructor
     *
     * @param opMode  The OpMode that it is being used in
     * @param oprobot The robot object in the OpMode
     */
    public IntakeControl(OpMode opMode, Robot oprobot) {
        robot = oprobot;
        chassisControl = opMode.gamepad1;
        telemetry = opMode.telemetry;
        yeetControl = opMode.gamepad1;

    }

    /**
     * This Method is wahat deals with the control of the intake
     */
    public void intakeControl() {
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

        if (yeetControl.dpad_up) {
            yeet = true;
        }
        if (yeetControl.dpad_down) {
            yeet = false;
        }
        robot.intakeRaise.setYeet(yeet);


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
