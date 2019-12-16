package com.team16488.automated_proccess;

import com.team16488.skystone.Robot;

public class StackBlocks {
    public Robot robot;

    public StackBlocks(Robot oprobot) {
        robot = oprobot;
    }

    public void stackOneBlockHigh(double curentTime, double hight) {
        if (curentTime < 1) {
            robot.lift.setPower(0.85);
        }
    }

}
