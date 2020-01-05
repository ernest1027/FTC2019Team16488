package com.team16488.control.general;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.automated_proccess.StackBlocks;
import com.team16488.library.subsystems.ArmHead;
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
    private Robot robot;
    /**
     * Sets the Virtical rotation power
     *
     * @see ArmHead
     */
    private double vPower;
    /**
     * Sets the Horizontal rotation power
     *
     * @see ArmHead
     */
    private double hPower;
    /**
     * Sets the position of the claw
     *
     * @see ArmHead
     */
    private boolean clawOpen = true;

    private Gamepad subsystemDriver;

    private StackBlocks stackBlocks;

    private boolean shift;

    private double tickCount = 0;

    private static double count = 1440;

    private OpMode opMode;


    /**
     * Constructs the subsystemControl class in the main OpMode
     *
     * @param opMode    The OpMode the class is being used in
     * @param oprobot   The robot object in the acctual OpMode that it
     *                  is being used in.
     */
    public SubsystemControl(OpMode opMode, Robot oprobot) {
        robot = oprobot;
        subsystemDriver = opMode.gamepad2;
        this.opMode = opMode;


    }

    public void armControl() {

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
            robot.liftStageFourBar.setExtend(false);
        }
        if (subsystemDriver.y) {
            stackBlocks.stackBlocks(6);
            if (robot.lIftStageOne.position > 6000){
                robot.liftStageFourBar.setExtend(true);
            }
        }
        if (subsystemDriver.a) {
            robot.liftStageFourBar.setExtend(false);
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

    }

    public void liftControl() {

        if (subsystemDriver.left_bumper) {
            tickCount += 1440;
            robot.liftStageFourBar.setExtend(true);
        }

        if (subsystemDriver.left_trigger != 0) {
            tickCount -= 1440;
            robot.liftStageFourBar.setExtend(false);
        }

        if (subsystemDriver.right_trigger != 0) {
            shift = true;
        }


        if (subsystemDriver.dpad_down) {
            // here is the macro co5de 3
            this.tickCount = count*3;
        }
        if (subsystemDriver.dpad_up) {
            //here is the macro code 1
            this.tickCount = count*1;
        }
        if (subsystemDriver.dpad_right) {
            this.tickCount = count*4;
        }
        if (subsystemDriver.dpad_left) {
            //here is the macro code 2
            this.tickCount = count*2;
        }

        if (shift) {
            if (subsystemDriver.dpad_down) {
                // here is the macro code 7
                this.tickCount = count*7;
            }
            if (subsystemDriver.dpad_up) {
                //here is the macro code 5
                this.tickCount = count*5;
            }
            if (subsystemDriver.dpad_right) {
                //here is the macro code8
                this.tickCount = count*8;
            }
            if (subsystemDriver.dpad_left) {
                //here is the macro code 6
                this.tickCount = count*6;
            }
        }
        robot.lIftStageOne.setPosition(tickCount);

    }

    public void printState(Telemetry telemetry) {
        telemetry.addData("Subsystem Status", "ON");
        telemetry.addData("------------------------------", "----------------");
        telemetry.addData("Gamepad2 start", subsystemDriver.start);
        telemetry.addData("Gamepad2 right bumper", subsystemDriver.right_bumper);
        telemetry.addData("Gamepad 2 left bumper", subsystemDriver.left_bumper);
        telemetry.addData("Ticks", robot.lIftStageOne.LiftLeft.getCurrentPosition());


    }


}
