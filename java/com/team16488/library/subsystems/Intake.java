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
    private boolean in = true;
    CRServo intake;

    public Intake(HardwareMap map){

        intake = map.crservo.get("i");
    }

    @Override
    public void update() {
        if (isOn) {
            if (in) {
                intake.setPower(0.45);
            } else {
                intake.setPower(-0.55);
            }

        }


        if (!isOn) {
            intake.setPower(0);
        }

    }




    public void setOn(boolean on){
        this.isOn = on;
    }
        public void setIn(boolean in)
        {
            this.in = in;
        }


}

