package com.team16488.control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.control.subsystems.ArmControl;
import com.team16488.control.subsystems.LiftControl;
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


    private Gamepad subsystemDriver;

    private OpMode opMode;

    private LiftControl liftControl;
    private ArmControl armControl;
    private double currentTime;

    /**
     * Constructs the subsystemControl class in the main OpMode
     *
     * @param opMode    The OpMode the class is being used in
     * @param oprobot   The robot object in the acctual OpMode that it
     *                  is being used in.
     */
    public SubsystemControl(OpMode opMode, Robot oprobot, double currentTime) {
        robot = oprobot;
        subsystemDriver = opMode.gamepad2;
        this.opMode = opMode;
        this.currentTime = currentTime;

    }


    /**
     * This is the method that updates the Subsystems based on driver input
     *
     * @param telemetry OpMode telemetry
     */
    public void subsystemDriverPad(Telemetry telemetry) {
        liftControl = new LiftControl(this.opMode, robot, currentTime);
        armControl = new ArmControl(this.opMode, robot);

        liftControl.liftControl();
        armControl.armControl();



/*
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
            // here is the macro code
        }
        if (subsystemDriver.dpad_up) {
            //here is the macro code
        }
        if (subsystemDriver.dpad_right) {
            //here is the macro code
        }
        if (subsystemDriver.dpad_left) {
            //here is the macro code
        }

        if (shift) {
            if (subsystemDriver.dpad_down) {
                // here is the macro code
            }
            if (subsystemDriver.dpad_up) {
                //here is the macro code
            }
            if (subsystemDriver.dpad_right) {
                //here is the macro code
            }
            if (subsystemDriver.dpad_left) {
                //here is the macro code
            }
        }

        if (subsystemDriver.right_bumper) {
            clawOpen = true;
        }
        if (clawOpen) {
            robot.armHead.setOpen(true);
        }
        if (!clawOpen) {
            robot.armHead.setOpen(false);
        }

        if (subsystemDriver.x) {
            robot.arm.setPower(1.0);
        }
        if (subsystemDriver.y) {
            robot.arm.setPower(-1.0);
        }
        if (subsystemDriver.a) {
            // reset pos using encoders
        }


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


        if (subsystemDriver.x) {
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
