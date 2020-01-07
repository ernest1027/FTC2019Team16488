package com.team16488.library.subsystems;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class PlatformHook extends Subsystem {

    Servo PullerServoRight;
    Servo PullerServoLeft;
    private boolean open = false;


    public PlatformHook(HardwareMap map) {

        PullerServoRight = map.servo.get("s1");

        PullerServoLeft = map.servo.get("s2");

    }

    @Override
    public void update() {
        if (open == true) {
            PullerServoRight.setPosition(1);
            PullerServoLeft.setPosition(0);
        }
        if (open == false) {
            PullerServoRight.setPosition(0.5);
            PullerServoLeft.setPosition(0.5);


        }
    }
    public void setOpen(boolean open){
        this.open = open;
    }
}

