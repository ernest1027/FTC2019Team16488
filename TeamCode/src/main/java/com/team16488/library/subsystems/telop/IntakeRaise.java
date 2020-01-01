package com.team16488.library.subsystems.telop;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team16488.library.subsystems.Subsystem;

public class IntakeRaise extends Subsystem {
    public boolean yeet;
    public CRServo leftyeet, rightyeet;
    double power = 0.85;


    public IntakeRaise(HardwareMap map) {
        leftyeet = map.crservo.get("LeftYeet");
        rightyeet = map.crservo.get("RightYeet");
    }



    @Override
    public void update() {

        if (yeet) {
            leftyeet.setPower(power);
            rightyeet.setPower(power);
        }
        if (!yeet) {
            leftyeet.setPower(-power);
            rightyeet.setPower(-power);
        }

    }

    public void setYeet(boolean yeet) {
        this.yeet = yeet;
    }
}
