package com.team16488.control.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.library.subsystems.telop.ArmHead;
import com.team16488.skystone.Robot;

/**
 * This class deals with the control of the Arm/Claw part of the
 * Robot
 *
 * @author Parham Baghbanbashi: parhambagh@gmail.com
 * @author Ernest Wong
 *
 * <p>github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi</p>
 */
public class  ArmControl {
    /**
     * Robot Class Object
     */
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

    /**
     * This is the constructor for the class
     *
     * @param opMode The opMode  that it is being used in
     * @param robot  The robot object for the OpMode
     */
    public ArmControl(OpMode opMode, Robot robot) {
        this.robot = robot;
        subsystemDriver = opMode.gamepad2;

    }

    /**
     * The method that deals with the control
     * of the arm/claw
     */
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
            robot.liftStageFourBar.setExtend(true);
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
}
