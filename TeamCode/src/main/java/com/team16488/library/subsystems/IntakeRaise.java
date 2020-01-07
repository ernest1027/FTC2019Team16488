package com.team16488.library.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team16488.library.subsystems.Subsystem;

public class IntakeRaise extends Subsystem {
    public boolean yeet;
    public CRServo leftyeet, rightyeet;
    double power = 0.85;
    public boolean On;


    public IntakeRaise(HardwareMap map) {
        leftyeet = map.crservo.get("LeftYeet");
        rightyeet = map.crservo.get("RightYeet");
    }



    @Override
    public void update() {
        if(On) {
            if (yeet) {
                leftyeet.setPower(power);
                rightyeet.setPower(power);
            }
            if (!yeet) {
                leftyeet.setPower(-power);
                rightyeet.setPower(-power);
            }
        }
        else{
            leftyeet.setPower(0);
            rightyeet.setPower(0);
        }
    }

    public void setYeet(boolean yeet) {
        this.yeet = yeet;
    }
}
