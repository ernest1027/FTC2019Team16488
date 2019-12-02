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

    CRServo LiftTop, LiftBottom;


    public Lift(HardwareMap map){
        LiftTop = map.crservo.get("LTOP");
        LiftBottom = map.crservo.get("LBOTTOM");

    }

    /**
     * This function is what changes the powers of the crservos when the setpower function changes the
     * variable power.
     */
    @Override
    public void update() {
        if(goingUp){
            LiftTop.setPower(power);
            LiftBottom.setPower(-power);

        }

        if(!goingUp){
            LiftTop.setPower(-power);
            LiftBottom.setPower(power);

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
