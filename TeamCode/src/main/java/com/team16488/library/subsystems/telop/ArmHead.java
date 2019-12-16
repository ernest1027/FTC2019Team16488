package com.team16488.library.subsystems.telop;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.team16488.library.subsystems.Subsystem;


/**
 * This class controls the movement of the claw that
 * grips the skystone
 *
 * <p>
 * This class has 3 components
 * - The Claw
 * - The Vertical movement
 * - The Rotational movement
 * </p>
 *
 * @author Parham Baghbanbashi: parhambagh@gmail.com
 * @author Ernest Wong
 *
 * <p>github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi</p>
 */
public class ArmHead extends Subsystem {
    /**
     * This is the servo that controls the position of the Claw
     */
    private Servo claw;
    /**
     * This it the servo that controls the vertical movement of the Arm Head
     */
    private Servo verticalRotation;
    /**
     * This is the servo that controls the horizontal rotation of the Arm Head
     */
    private Servo horizontalRotation;
    /**
     * Sets the position of the claw(Open or Closed
     */
    private boolean open = false;
    /** Position of the Vertical rotation servo and horizontal rotation servo*/
    private double verticalRotationPosition, horizontalRotationPosition;

    /**
     * This is the constructor for the class
     * <p>
     * This constructor has all of the servos for
     * the arm head, the main purpose is so that the servo classes can actually work
     * </p>
     *
     * @param map This is the hardware map of the actual OpMode for the Arm Head
     */
    public ArmHead(HardwareMap map) {
        claw = map.servo.get("ch1");
        verticalRotation = map.servo.get("VR");
        horizontalRotation = map.servo.get("HR");
    }

    /**
     * This Method updates the Arm Head Servos based on the private variable
     *
     * <p>See: {@link Subsystem}</p>
     */
    @Override
    public void update() {

        if (!open) {
            claw.setPosition(1.0);

        }


        if (open) {
            claw.setPosition(-1.0);

        }

        verticalRotation.setPosition(verticalRotationPosition);
        horizontalRotation.setPosition(horizontalRotationPosition);


    }

    /**
     * This method Updates the claws position: Open or Closed
     *
     * @param open Sets the claw position to open(if true)
     */
    public void setOpen(boolean open) {
        this.open = open;

    }

    /**
     * Changes the postion of the vertical servo on the head apparatus
     *
     * @param pos The desired position of the servo
     */
    public void setverticalRotation(double pos) {
        this.verticalRotationPosition = pos;
    }

    /**
     * Changes the position of the horizontal servo on the head apparatus
     *
     * @param horizontalRotationPosition the desired position of the horizontal servo
     */
    public void sethorizontalRotationPosition(double horizontalRotationPosition) {
        this.horizontalRotationPosition = horizontalRotationPosition;
    }
}
