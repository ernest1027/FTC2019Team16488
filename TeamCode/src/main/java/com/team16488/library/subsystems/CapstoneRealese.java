package com.team16488.library.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class CapstoneRealese extends Subsystem{
    public Servo realseCap;

    boolean realse;

    public CapstoneRealese(HardwareMap map){
        realseCap = map.servo.get("CapRelease");
    }

    @Override
    public void update() {
        if(realse){
            realseCap.setPosition(0);
        }
        if(!realse){
            realseCap.setPosition(1.0);
        }
    }
    public void setRealse(boolean realse){
        this.realse = realse;
    }
}
