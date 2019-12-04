/* Developed by Parham Baghbanbashi
   parhambagh@gmail.com
 */
package com.team16488.control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.skystone.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SubsystemControl {
    private Robot robot;
    private double vPower;
    private double hPower;
    private boolean up = false;
    private boolean clawOpen = true;

    public SubsystemControl(OpMode opMode, Telemetry telemetry) {
        robot = new Robot(opMode, telemetry);
    }

    public void subsystemDriver(Gamepad gamepad2, Telemetry telemetry) {

        if (gamepad2.left_bumper) {
            up = true;
        }
        if (gamepad2.right_bumper) {
            up = false;
        }
        robot.lift.setGoingUp(up);

/*
        if (gamepad2.dpad_up) {
            vPower += 0.1;
        }
        if (gamepad2.dpad_down) {
            vPower -= 0.1;
        }
        if (gamepad2.dpad_left) {
            hPower += 0.1;
        }

        if (gamepad2.dpad_right) {
            hPower -= 0.1;
        }

        robot.armHead.setverticalRotation(vPower);
        robot.armHead.sethorizontalRotationPosition(hPower);

        // robot.arm.setPower(-gamepad2.right_stick_y);

        telemetry.addData("vpower", vPower);
        telemetry.addData("hpower", hPower);


        //swich gampad 1 to gamepad2
        if (gamepad2.a) {
            clawOpen = true;
        }
        if (gamepad2.b) {
            clawOpen = false;
        }
        if (clawOpen) {
            robot.armHead.setOpen(true);
        }
        if (!clawOpen) {
            robot.armHead.setOpen(false);
        }


        robot.arm.setPower(-gamepad2.right_stick_y);
*/
    }


}
