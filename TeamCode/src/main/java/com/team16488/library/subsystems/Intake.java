package com.team16488.library.subsystems;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * This class controls the intake of the robot.
 * the intake is responsible for the acquiring of
 * the skyblocks
 */
public class Intake extends Subsystem {

    /**
     * Servo group of the intake
     */
    CRServo intake;
    /**
     * Sets the state of the intake. ON. OFF
     */
    private boolean isOn = false;
    /**
     * Sets if the intake is reverse
     */
    private boolean reverse = false;

    /**
     * This is the Class constructor
     * <p>
     * This constructor has all of the servos for
     * the arm head, the main purpose is so that the servo classes can actually work
     * </p>
     *
     * @param map This is the hardware map of the actual OpMode for the Intake
     */
    public Intake(HardwareMap map) {

        intake = map.crservo.get("Intake");
    }

    /**
     * This Method updates the servos based on the private variables
     *
     * @see Subsystem
     */
    @Override
    public void update() {
        if (isOn) {
            intake.setPower(0.5);
        } else if (reverse) {
            intake.setPower(-0.5);
        } else {
            intake.setPower(0.0);
        }


    }

    /**
     * Sets the intake position to reverse
     *
     * @param reverse boolean variable that sets weather or not the intakes are reverse
     */
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    /**
     * Sets the intake as on or off
     *
     * @param on boolean, sets if the intake is on
     */
    public void setOn(boolean on) {
        this.isOn = on;
    }


}
