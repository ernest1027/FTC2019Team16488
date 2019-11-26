package com.team16488.library.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Puller extends Subsystem {
    Servo Left, Right;
    private boolean down = false;

    public Puller(HardwareMap map){
        Left = map.servo.get("LP");
        Right = map.servo.get("RP");
    }

    @Override
    public void update() {
        if(down == false)
        {
            Right.setPosition(1.0);
            Left.setPosition(1.0);

        }


        if(down == true){
            Left.setPosition(-1.0);
            Right.setPosition(-1.0);

        }

    }

    public void setDown(boolean down){
        this.down = down;

    }
}
