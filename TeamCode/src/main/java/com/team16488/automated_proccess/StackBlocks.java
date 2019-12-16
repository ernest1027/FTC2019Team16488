package com.team16488.automated_proccess;

import com.team16488.skystone.Robot;

/**
 * This is the Class that deals with stacking the blocks
 * for the macros on the subsystem driver controler
 *
 * @author Parham Baghbanbashi: parhambagh@gmail.com
 * @author Ernest Wong
 *
 * <p>github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi</p>
 */
public class StackBlocks {
    public Robot robot;

    /**
     * This is the constructor for the class
     *
     * @param oprobot The robot Object for the OpMode
     */
    public StackBlocks(Robot oprobot) {
        robot = oprobot;
    }

    /**
     * This is the Method that will determine the motor movement for the
     * hight specified
     * @param curentTime The current Time
     * @param hight How meany blocks high that the driver wants to stack
     */
    public void stackOneBlockHigh(double curentTime, double hight) {
        if (curentTime < 1) {
            robot.lift.setPower(0.85);
        }
    }

}
