package com.team16488.library.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift extends Subsystem {
    private boolean goingUp = false;
    private double power = 1.0;

    CRServo leftSideArm1, rightSideArm1, leftSideArm2, rightSideArm2;


    public Lift(HardwareMap map){
        leftSideArm1 = map.crservo.get("lsa1");
        rightSideArm1 = map.crservo.get("rsa1");
        leftSideArm2 = map.crservo.get("lsa2");
        rightSideArm2 = map.crservo.get("lsa2");
    }


    @Override
    public void update() {
        if(goingUp){
            leftSideArm1.setPower(power);
            rightSideArm1.setPower(power);
            leftSideArm2.setPower(power);
            rightSideArm2.setPower(power);
        }

        if(!goingUp){
            leftSideArm1.setPower(-power);
            rightSideArm1.setPower(-power);
            leftSideArm2.setPower(-power);
            rightSideArm2.setPower(-power);

        }
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }
}
