package com.team16488.library.subsystems;
/**
 * Deloped by Parham Baghbanbashi and Ernest Wong
 * parhambagh@gmail.com
 */

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawHead extends Subsystem {
    Servo claw, verticalRotation, horazontalRotation;
    private boolean open = false;
    private boolean sideOpen = false;
    private double verticalRotationPosition, horizontalRotationPosition;

    public ClawHead(HardwareMap map){
        claw = map.servo.get("ch1");
        verticalRotation = map.servo.get("VR");
        horazontalRotation = map.servo.get("HR");
    }

    @Override
    public void update() {
        if(open == false)
        {
            claw.setPosition(1.0);

        }


        if(open == true){
            claw.setPosition(-1.0);

        }

        verticalRotation.setPosition(verticalRotationPosition);
        horazontalRotation.setPosition(horizontalRotationPosition);



    }

    public void setOpen(boolean open){
        this.open = open;

    }

    public void setverticalRotation(double pos){
        this.verticalRotationPosition = pos;
    }

    public void sethorizontalRotationPosition(double horizontalRotationPosition) {
        this.horizontalRotationPosition = horizontalRotationPosition;
    }
}
