package com.team16488.automated_proccess;

import com.team16488.skystone.Robot;

public class ArmRealese {
    // every second is no more then  10 irterations
    public Robot robot;

    public ArmRealese(Robot oprobot) {
        robot = oprobot;
    }

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
