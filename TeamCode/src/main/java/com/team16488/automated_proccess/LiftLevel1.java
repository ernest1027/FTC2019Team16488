package com.team16488.automated_proccess;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.team16488.skystone.Robot;

public class LiftLevel1 {
    // every second is no more then  10 irterations
    public Robot robot;
    public boolean doprocces = true;

    public LiftLevel1(OpMode opMode, Robot oprobot) {
        robot = oprobot;
    }

    public void procces() {
        for (int i = 0; i > 20; i++) {
            robot.lift.setPower(0.85);
        }
    }
}
