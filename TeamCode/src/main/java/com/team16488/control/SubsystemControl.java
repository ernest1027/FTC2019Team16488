package com.team16488.control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.skystone.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This is the class that controls the vairous subsystems on the robot
 * Its job is to map each subsystem to gamepad2
 *
 * @author Parham Baghbanbashi
 */
public class SubsystemControl {
    /**
     * Robot Class Object
     */
    private Robot robot;
    /**
     * Sets the Virtical rotation power
     *
     * @see com.team16488.library.subsystems.ArmHead
     */
    private double vPower;
    /**
     * Sets the Horizontal rotation power
     *
     * @see com.team16488.library.subsystems.ArmHead
     */
    private double hPower;
    /**
     * Sets the direction of the lift
     *
     * @see com.team16488.library.subsystems.Lift
     */
    private boolean up = false;
    /**
     * Sets the position of the claw
     *
     * @see com.team16488.library.subsystems.ArmHead
     */
    private boolean clawOpen = true;

    /**
     * Constructs the subsystemControl class in the main OpMode
     *
     * @param opMode    The OpMode the class is being used in
     * @param telemetry The telemetry of the OpMode the class is being used in
     */
    public SubsystemControl(OpMode opMode, Telemetry telemetry) {
        robot = new Robot(opMode, telemetry);
    }

    /**
     * This is the method that updates the Subsystems based on driver input
     *
     * @param gamepad2 Subsystem Driver Controller
     * @param telemetry OpMode telemetry
     */
    public void subsystemDriver(Gamepad gamepad2, Telemetry telemetry) {

        if (gamepad2.left_bumper) {
            up = true;
        }
        if (gamepad2.right_bumper) {
            up = false;
        }
        robot.lift.setGoingUp(up);


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

    }


}
