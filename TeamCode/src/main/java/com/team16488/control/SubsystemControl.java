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
 * <p>See: {@link com.team16488.skystone.Robot}</p>
 * <p>
 *     github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi
 * </p>
 *
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

    private boolean liftOn = false;

    private Gamepad subsystemDriver;

    /**
     * Constructs the subsystemControl class in the main OpMode
     *
     * @param opMode    The OpMode the class is being used in
     * @param telemetry The telemetry of the OpMode the class is being used
     */
    public SubsystemControl(OpMode opMode, Telemetry telemetry) {
        robot = new Robot(opMode, telemetry);
        subsystemDriver = opMode.gamepad2;
    }

    /**
     * This is the method that updates the Subsystems based on driver input
     *
     * @param telemetry OpMode telemetry
     */
    public void subsystemDriver(Telemetry telemetry) {

        if (subsystemDriver.y) {
            this.liftOn = true;
        }
        if (subsystemDriver.a) {
            this.liftOn = false;
        }
        robot.lift.setOn(liftOn);
        if (liftOn) {
            if (subsystemDriver.left_bumper) {
                up = true;
                robot.lift.setPower(0.5);
            }
            if (subsystemDriver.right_bumper) {
                up = false;
                robot.lift.setPower(-0.5);

            }
        }

        robot.lift.setGoingUp(up);
/*

        if (subsystemDriver.dpad_up) {
            vPower += 0.1;
        }
        if (subsystemDriver.dpad_down) {
            vPower -= 0.1;
        }
        if (subsystemDriver.dpad_left) {
            hPower += 0.1;
        }

        if (subsystemDriver.dpad_right) {
            hPower -= 0.1;
        }

        robot.armHead.setverticalRotation(vPower);
        robot.armHead.sethorizontalRotationPosition(hPower);


        telemetry.addData("vpower", vPower);
        telemetry.addData("hpower", hPower);


        //swich gampad 1 to gamepad2
        if (subsystemDriver.a) {
            clawOpen = true;
        }
        if (subsystemDriver.b) {
            clawOpen = false;
        }
        if (clawOpen) {
            robot.armHead.setOpen(true);
        }
        if (!clawOpen) {
            robot.armHead.setOpen(false);
        }


        robot.arm.setPower(-subsystemDriver.right_stick_y);
*/
        telemetry.addData("Subsystem Status", "ON");
        telemetry.addData("------------------------------", "----------------");
        telemetry.addData("Gamepad2 start", subsystemDriver.start);
        telemetry.addData("Gamepad2 right bumper", subsystemDriver.right_bumper);
        telemetry.addData("Gamepad 2 left bumper", subsystemDriver.left_bumper);
        telemetry.addData("Lift State", robot.lift.On);
        telemetry.addData("Lift Direction", robot.lift.goingUp);
        telemetry.addData("Lift power acctual", robot.lift.LiftTop.getPower());
        telemetry.addData("Lift power set", robot.lift.power);
    }


}
