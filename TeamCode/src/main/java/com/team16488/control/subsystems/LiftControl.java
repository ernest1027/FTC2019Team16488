package com.team16488.control.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.automated_proccess.StackBlocks;
import com.team16488.skystone.Robot;

/**
 * This class deals with the control of the LiftStageOne movement.
 * It als contains the Macros for the LiftStageOne hight
 * {@link StackBlocks}
 *
 * @author Parham Baghbanbashi: parhambagh@gmail.com
 * @author Ernest Wong
 *
 * <p>github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi</p>
 */
public class LiftControl {

    private Robot robot;

    private boolean shift;

    private Gamepad subsystemDriver;

    private StackBlocks stackBlocks;

    private double tickCount = 288;


    /**
     * This is the construtor for the class
     *
     * @param opMode      The OpMode that it is being used in
     * @param robot       The robot object
     */
    public LiftControl(OpMode opMode, Robot robot) {
        this.robot = robot;
        subsystemDriver = opMode.gamepad2;
        stackBlocks = new StackBlocks(robot, tickCount);
    }

    /**
     * The main method that deals with the control of the LiftStageOne
     */
    public void liftControl() {

        if (subsystemDriver.left_bumper) {
            tickCount += 1;
            robot.liftStageFourBar.setExtend(true);
        }

        if (subsystemDriver.left_trigger != 0) {
            tickCount -= 1;
            robot.liftStageFourBar.setExtend(false);
        }

        if (subsystemDriver.right_trigger != 0) {
            shift = true;
        }

        robot.LIftStageOne.setPosition(tickCount);

        if (subsystemDriver.dpad_down) {
            // here is the macro co5de 3
            stackBlocks.stackBlocks(3);
        }
        if (subsystemDriver.dpad_up) {
            //here is the macro code 1
            stackBlocks.stackBlocks(1);
        }
        if (subsystemDriver.dpad_right) {
            //here is the macro code 4
        }
        if (subsystemDriver.dpad_left) {
            //here is the macro code 2
            stackBlocks.stackBlocks(2);
        }

        if (shift) {
            if (subsystemDriver.dpad_down) {
                // here is the macro code 7
            }
            if (subsystemDriver.dpad_up) {
                //here is the macro code 5
            }
            if (subsystemDriver.dpad_right) {
                //here is the macro code8
            }
            if (subsystemDriver.dpad_left) {
                //here is the macro code 6
            }
        }


    }

}
