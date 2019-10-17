package org.firstinspires.ftc.teamcode.subsytems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
public class PullerServos extends OpMode {

    Servo RightPullerServo;
    Servo LeftPullerServo;




    public void init() {
        RightPullerServo = hardwareMap.servo.get("s1");
        LeftPullerServo = hardwareMap.servo.get("s2");

    }
    public void loop(){
        if(gamepad1.right_bumper == true)
        {
            RightPullerServo.setPosition(1.0);
            LeftPullerServo.setPosition(-0.5);
        }

        /** set servos positon to 0.5 if button a is presed
         */
        if(gamepad1.right_trigger > 0){
            RightPullerServo.setPosition(-0.5);
            LeftPullerServo.setPosition(1.0);
        }

    }
}
