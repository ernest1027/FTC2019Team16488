package com.team16488.library.subsystems;
/**
 * Deloped by Parham Baghbanbashi and Ernest Wong
 * parhambagh@gmail.com
 */

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift extends Subsystem {
    private boolean goingUp = false;
    private double power = 1.0;

    CRServo leftSideArmTop, rightSideArmTop, leftSideArmBot, rightSideArmBot;


    public Lift(HardwareMap map){
        leftSideArmTop = map.crservo.get("lst");
        rightSideArmTop = map.crservo.get("rst");
        leftSideArmBot = map.crservo.get("lsb");
        rightSideArmBot = map.crservo.get("rsb");
    }


    @Override
    public void update() {
        if(goingUp){
            leftSideArmTop.setPower(power);
            rightSideArmTop.setPower(-power);
            leftSideArmBot.setPower(-power);
            rightSideArmBot.setPower(power);
        }

        if(!goingUp){
            leftSideArmTop.setPower(power);
            rightSideArmTop.setPower(-power);
            leftSideArmBot.setPower(-power);
            rightSideArmBot.setPower(power);

        }
    }

    /**
     * Sets the speed of the lift
     * @param power
     */
    public void setPower(double power)
    {
        this.power = power;
    }

    /**
     * Sets the direction of the lift(UP or DOWN)
     * @param goingUp
     */
    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }
}
