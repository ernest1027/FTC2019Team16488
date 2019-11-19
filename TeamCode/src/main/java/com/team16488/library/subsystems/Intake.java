package com.team16488.library.subsystems;
/**
 * Deloped by Parham Baghbanbashi and Ernest Wong
 * parhambagh@gmail.com
 */
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake extends Subsystem {
    private double speed = 1.00;
    private boolean isOn;
    private boolean fast = true;
    CRServo leftintake;
    CRServo  rightintake;

    public Intake(HardwareMap map){
        leftintake = map.crservo.get("li");
        rightintake = map.crservo.get("ri");
    }

    @Override
    public void update() {
        if(isOn){
            if(fast)
            {
                leftintake.setPower(1.0);
                rightintake.setPower(1.0);
            }
            else
            {
                leftintake.setPower(0.5);
                rightintake.setPower(0.5);
            }
            leftintake.setPower(speed);
            rightintake.setPower(speed);
        }
        if(!isOn){
            leftintake.setPower(0);
            rightintake.setPower(0);
        }

    }




    public void setOn(boolean on){
        this.isOn = on;
    }

    public void speedMode(boolean fast)
    {
        this.fast = fast;
    }

}
