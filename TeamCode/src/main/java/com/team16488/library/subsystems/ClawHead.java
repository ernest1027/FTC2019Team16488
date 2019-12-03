package com.team16488.library.subsystems;
/*
 * Deloped by Parham Baghbanbashi and Ernest Wong
 * parhambagh@gmail.com
 */

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawHead extends Subsystem {
    Servo claw, verticalRotation, horazontalRotation;
    private boolean open = false;
    private boolean sideOpen = false;
    private double verticalRotationPosition, horizontalRotationPosition;

    public ClawHead(HardwareMap map) {
        claw = map.servo.get("ch1");
        verticalRotation = map.servo.get("VR");
        horazontalRotation = map.servo.get("HR");
    }

    /**
     * this updates the postions of the servos based on the "setServo" methods
     */
    @Override
    public void update() {
        if (open == false) {
            claw.setPosition(1.0);

        }


        if (open == true) {
            claw.setPosition(-1.0);

        }

        verticalRotation.setPosition(verticalRotationPosition);
        horazontalRotation.setPosition(horizontalRotationPosition);


    }

    public void setOpen(boolean open) {
        this.open = open;

    }

    /**
     * Changes the postion of the vertical servo on the head apparatus
     *
     * @param pos
     */
    public void setverticalRotation(double pos) {
        this.verticalRotationPosition = pos;
    }

    /**
     * Changes the position of the horizontal servo on the head apparatus
     *
     * @param horizontalRotationPosition
     */
    public void sethorizontalRotationPosition(double horizontalRotationPosition) {
        this.horizontalRotationPosition = horizontalRotationPosition;
    }
}
