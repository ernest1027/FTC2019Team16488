package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Drivetrain.TeleopMecanumDrive;
import org.firstinspires.ftc.teamcode.subsytems.PullerServos;
import org.firstinspires.ftc.teamcode.subsytems.TestPullerServos;
// author Parham baghbanbashi email: parhambagh@gmail.com

@TeleOp
public class TestServos extends OpMode {

    Servo RightPullerServo;
    Servo LeftPullerServo;

    TestPullerServos testServos = new TestPullerServos();
    public void init() {
        RightPullerServo = hardwareMap.servo.get("s1");
        LeftPullerServo = hardwareMap.servo.get("s2");
    }
    public void loop(){
        testServos.loopofprogram(LeftPullerServo,RightPullerServo,gamepad1.right_bumper,gamepad1.right_trigger);

        telemetry.addData("trigger", gamepad1.right_trigger);
        telemetry.addData("bumper", gamepad1.right_bumper);

    }
}
