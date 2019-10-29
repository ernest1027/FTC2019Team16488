package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsytems.ClawHeadMovement;
// author Parham baghbanbashi email: parhambagh@gmail.com

@TeleOp
public class TestServos extends OpMode {

    Servo RightHeadServo;
    Servo LeftHeadServo;

    ClawHeadMovement clawHeadMovement = new ClawHeadMovement();
    public void init() {
        clawHeadMovement.initalize(RightHeadServo,LeftHeadServo);
    }
    public void loop(){
        clawHeadMovement.loopofprogram(RightHeadServo,LeftHeadServo,gamepad1.right_bumper,gamepad1.right_trigger);

    }
}
