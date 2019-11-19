package com.team16488.library.subsystems;
/**
 * Deloped by Parham Baghbanbashi and Ernest Wong
 * parhambagh@gmail.com
 */

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawHead extends Subsystem {
    Servo claw, virticalRotation, horazontalRotation;
    private boolean open = false;
    private boolean sideOpen = false;
    private double virticalRotationDegres, horazontalRoationDegrees;

    public ClawHead(HardwareMap map){
        claw = map.servo.get("ch1");
        virticalRotation = map.servo.get("VR");
        horazontalRotation = map.servo.get("HR");
    }

    @Override
    public void update() {
        if(open == false)
        {
            claw.setPosition(1.0);

        }

        /** set servos positon to 0.5 if button a is presed
         */
        if(open == true){
            claw.setPosition(-1.0);

        }

        virticalRotation.setPosition(virticalRotationDegres);
        horazontalRotation.setPosition(horazontalRoationDegrees);



    }

    public void setOpen(boolean open){
        this.open = open;

    }

    public void setVirticalRotation(double degrees){
        this.virticalRotationDegres = degrees;
    }

    public void setHorazontalRoationDegrees(double horazontalRoationDegrees) {
        this.horazontalRoationDegrees = horazontalRoationDegrees;
    }
}
