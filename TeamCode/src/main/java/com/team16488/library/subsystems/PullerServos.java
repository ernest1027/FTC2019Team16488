package com.team16488.library.subsystems;

/**
 * Deloped by Parham Baghbanbashi and Ernest Wong
 * parhambagh@gmail.com
 */

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PullerServos extends Subsystem{
    Servo RightPullerServo;
    Servo LeftPullerServo;

    private boolean open = false;
    public PullerServos(HardwareMap map){
        RightPullerServo = map.servo.get("s1");
        LeftPullerServo = map.servo.get("s2");
    }

    @Override
    public void update() {
        if(open == true)
        {
            RightPullerServo.setPosition(1.0);
            LeftPullerServo.setPosition(-0.5);
        }

        /** set servos positon to 0.5 if button a is presed
         */
        if(open == false){
            RightPullerServo.setPosition(-0.5);
            LeftPullerServo.setPosition(1.0);
        }

    }

    public void setOpen(boolean position){
        this.open = position;
    }


}
