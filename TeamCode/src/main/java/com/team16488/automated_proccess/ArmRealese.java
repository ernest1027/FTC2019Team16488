package com.team16488.automated_proccess;

import com.team16488.skystone.Robot;

public class ArmRealese {
    // every second is no more then  10 irterations
    public Robot robot;
    public boolean doprocces = true;
    public boolean moveState;
    public boolean LiftState;
    public boolean RealseState;

    public ArmRealese(Robot oprobot) {
        robot = oprobot;
    }

    public void procces() {
        if (moveState) {
            robot.drive.setVelocity(0.0, 1.0, 0.0);
        }
        if (LiftState) {
            robot.lift.setPower(0.85);
        }
        if (RealseState) {
            robot.alternateIntake.ON = true;
        }
    }
}
