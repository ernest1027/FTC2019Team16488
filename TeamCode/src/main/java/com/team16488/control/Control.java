package com.team16488.control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.team16488.control.general.ChassisControl;
import com.team16488.control.general.SubsystemControl;
import com.team16488.skystone.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Control {
    public Telemetry telemetry;
    private Robot robot;
    private ChassisControl chassisControl;
    private SubsystemControl subsystemControl;

    public Control(OpMode opMode, Robot oprobot) {
        robot = oprobot;
        this.telemetry = opMode.telemetry;
        chassisControl = new ChassisControl(opMode, robot);
        subsystemControl = new SubsystemControl(opMode, robot);
    }

    public void control() {
        chassisControl.joysticks();
        chassisControl.buttons();
        chassisControl.leftTriggers();
        chassisControl.rightTrigger();
        chassisControl.printState();

        subsystemControl.dpad();
        subsystemControl.buttons();
        subsystemControl.joySticks();
        subsystemControl.leftTriggers();
        subsystemControl.rightTriggers();
        subsystemControl.printState(telemetry);
    }
}
