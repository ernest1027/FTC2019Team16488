package com.team16488.library.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawHeadMovement extends Subsystem {
    Servo LeftHead, RightHead;
    private boolean open = false;

    public ClawHeadMovement(HardwareMap map){
        LeftHead = map.servo.get("s1");
        RightHead = map.servo.get("s2");
    }

    @Override
    public void update() {
        if(open == false)
        {
            RightHead.setPosition(1.0);
            LeftHead.setPosition(-0.5);
        }

        /** set servos positon to 0.5 if button a is presed
         */
        if(open == true){
            RightHead.setPosition(-0.5);
            LeftHead.setPosition(1.0);
        }

    }

    public void setOpen(boolean open){
        this.open = open;

    }

}
