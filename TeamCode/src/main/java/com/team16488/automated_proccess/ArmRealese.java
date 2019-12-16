package com.team16488.automated_proccess;

import com.team16488.skystone.Robot;

/**
 * At the start of the match we must expand all the components of the robot
 * this class is what will accomplish that task
 *
 * @author Parham Baghbanbashi: parhambagh@gmail.com
 * @author Ernest Wong
 *
 * <p>github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi</p>
 */
public class ArmRealese {
    // every second is no more then  10 irterations
    public Robot robot;

    /**
     * This is the main constructor for this class
     *
     * @param oprobot The robot object in the opMode
     */
    public ArmRealese(Robot oprobot) {
        robot = oprobot;
    }

    /**
     * This is the function that accomplishes the main
     * purpose of this class
     * @param currentTime The current time of the OpMode
     */
    public void procces(double currentTime) {
        if (currentTime < 3) {
            robot.drive.setVelocity(0.0, 1.0, 0.0);
        }
        if (currentTime > 3 && currentTime < 8) {
            robot.lift.setPower(0.85);
        }
        if (currentTime > 6) {
            robot.alternateIntake.ON = true;
        }
    }
}
