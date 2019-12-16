package com.team16488.control.chassis;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.skystone.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This Class deals with the control of the alternate Intake
 *
 * @author Parham Baghbanbashi: parhambagh@gmail.com
 * @author Ernest Wong
 *
 * <p>github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi</p>
 */
public class AlternateIntakeControl {
    private Robot robot;

    private Gamepad chassisControl;

    private Telemetry telemetry;

    /**
     * The class constructor
     *
     * @param opMode  this is the OpMode that it is being used in
     * @param oprobot The Robot object of the OpMode
     */
    public AlternateIntakeControl(OpMode opMode, Robot oprobot) {
        robot = oprobot;
        chassisControl = opMode.gamepad1;
        telemetry = opMode.telemetry;
    }

    /**
     * This is the Method that deals with the control of the alternate intake
     */
    public void alternateIntakeControl() {
        if (chassisControl.dpad_up) {
            robot.alternateIntake.setPos(-0.5);
        }
        if (chassisControl.start) {
            robot.alternateIntake.ON = true;
        }
        if (chassisControl.back) {
            robot.alternateIntake.setPos(1.00);
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
