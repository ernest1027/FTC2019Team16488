package org.firstinspires.ftc.teamcode.subsytems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
public class ClawHeadMovement extends OpMode {




    public void initalize(Servo RightHeadServo, Servo LeftHeadServo) {
        RightHeadServo = hardwareMap.servo.get("s1");
        LeftHeadServo = hardwareMap.servo.get("s2");

    }
    public void loopofprogram(Servo LeftHeadServo,Servo RightHeadServo,boolean gampad1bump,double gamepad1trg){
        if(gampad1bump == true)
        {
            RightHeadServo.setPosition(1);
            LeftHeadServo.setPosition(0);
        }

        /** set servos positon to 0.5 if button a is presed
         */
        if(gamepad1trg > 0){
            RightHeadServo.setPosition(0);
            LeftHeadServo.setPosition(1.0);
        }

    }


    public void loop(){}
    public void init(){}
}
