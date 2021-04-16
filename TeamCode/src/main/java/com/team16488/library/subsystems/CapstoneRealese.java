package com.team16488.library.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class CapstoneRelease extends Subsystem{
    public Servo releaseCap;

    boolean release;

    public CapstoneRelease(HardwareMap map){
        releaseCap = map.servo.get("CapRelease");
    }

    @Override
    public void update() {
        if(release){
            releaseCap.setPosition(0);
        }
        if(!release){
            releaseCap.setPosition(1.0);
        }
    }
    public void setRelease(boolean release){
        this.release = release;
    }
}
