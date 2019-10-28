package org.firstinspires.ftc.teamcode.subsytems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
public class TestPullerServos extends OpMode {




    public void initalize(Servo RightPullerServo,Servo LeftPullerServo) {
        RightPullerServo = hardwareMap.servo.get("s1");
        LeftPullerServo = hardwareMap.servo.get("s2");

    }
    public void loopofprogram(Servo LeftPullerServo,Servo RightPullerServo,boolean gampad1bump,double gamepad1trg){
        if(gampad1bump == true)
        {
            RightPullerServo.setPosition(1);
            LeftPullerServo.setPosition(0);
        }

        /** set servos positon to 0.5 if button a is presed
         */
        if(gamepad1trg > 0){
            RightPullerServo.setPosition(0);
            LeftPullerServo.setPosition(1.0);
        }

    }


    public void loop(){}
    public void init(){}
}
