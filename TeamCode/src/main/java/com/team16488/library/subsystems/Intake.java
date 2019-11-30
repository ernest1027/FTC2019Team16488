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
    private boolean reverse = false;

    CRServo  intake;

    public Intake(HardwareMap map){

        intake = map.crservo.get("i");
    }

    @Override
    public void update() {
        if(isOn){
            intake.setPower(1.00);
        }

        if (reverse) {
            intake.setPower(-1.0);
        }

        else {
            intake.setPower(0.5);
        }


    }

    /**
     * Sets the intake position to reverse
     * @param reverse
     */
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    /**
     * Sets the intake as on or off
     * @param on
     */
    public void setOn(boolean on){
        this.isOn = on;
    }



}
