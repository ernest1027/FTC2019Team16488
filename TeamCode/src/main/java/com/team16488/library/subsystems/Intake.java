package com.team16488.library.subsystems;
/**
 * Deloped by Parham Baghbanbashi and Ernest Wong
 * parhambagh@gmail.com
 */
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake extends Subsystem {
    private boolean isOn;
    CRServo leftinake;
    CRServo  rightintake;

    public Intake(HardwareMap map){
        leftinake = map.crservo.get("li");
        rightintake = map.crservo.get("ri");
    }

    @Override
    public void update() {
        if(isOn){
            leftinake.setPower(1.00);
            rightintake.setPower(-1.00);
        }
        if(!isOn){
            leftinake.setPower(0);
            rightintake.setPower(0);
        }

    }

    public void setOn(boolean on){
        this.isOn = on;
    }
}
