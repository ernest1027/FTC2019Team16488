package com.team16488.library.subsystems;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * This class controls the Double Reverse 4 bar
 *
 * @author Parham Baghbanbashi
 * @author Eernest Wong
 * <p>github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi</p>
 */
public class Lift extends Subsystem {
    /**
     * Sets the direction of the double reverse 4 bar
     */
    public boolean goingUp;
    /**
     * Sets the speed of the Double Reverse 4 Bar
     */
    public double power = 0.5;

    /**
     * The servo groups that will be Controlled
     */
    public CRServo LiftTop, LiftBottom;

    public boolean On = false;


    /**
     * This is the constructor for the subsystem
     *
     * @param map This is the hardware map of the actual OpMode for the Lift Class
     */
    public Lift(HardwareMap map) {
        LiftTop = map.crservo.get("LTOP");
        LiftBottom = map.crservo.get("LBOTTOM");

    }

    /**
     * This function is what changes the powers of the crservos when the setpower function changes the
     * variable power.
     *
     * <p>See: {@link Subsystem}</p>
     */
    @Override
    public void update() {
        if (On) {
            if (goingUp) {
                LiftTop.setPower(power);
                LiftBottom.setPower(-power);

            }
            if (!goingUp) {
                LiftTop.setPower(-power);
                LiftBottom.setPower(power);

            }
        }
        if (!On) {
            LiftTop.setPower(0);
            LiftBottom.setPower(0);
        }
    }

    /**
     * Sets the speed of the lift
     *
     * @param power sets the speed of the servos
     */
    public void setPower(double power) {
        this.power = power;
    }

    /**
     * Sets the direction of the lift(UP or DOWN)
     *
     * @param goingUp sets the direction of the Lift
     */
    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    /**
     * This turns the lif on
     *
     * @param on state ON or OFF
     */
    public void setOn(boolean on) {
        this.On = on;
    }

}
