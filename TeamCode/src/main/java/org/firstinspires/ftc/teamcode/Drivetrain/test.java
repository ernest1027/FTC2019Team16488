package org.firstinspires.ftc.teamcode.Drivetrain;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import team16488.*;
@Disabled
public class test extends OpMode {
    DcMotor FrontLeftMotor;
    DcMotor FrontRightMotor;
    DcMotor RearRightMotor;
    DcMotor RearLeftMotor;
    Servo RightPullerServo;
    Servo LeftPullerServo;
    CRServo armJoint1;
    DriveTrain driveTrain = new DriveTrain();

    double y = -gamepad1.right_stick_y;
    double rotationValue = -gamepad1.left_stick_x;
    double x = -gamepad1.right_stick_x;

    public void mapHardware(){
        /**
         * define motors
         */
        FrontLeftMotor = hardwareMap.dcMotor.get("m4");
        FrontRightMotor = hardwareMap.dcMotor.get("m3");
        RearRightMotor = hardwareMap.dcMotor.get("m2");
        RearLeftMotor = hardwareMap.dcMotor.get("m1");

        /**
         * define servos
         */
        RightPullerServo = hardwareMap.servo.get("s1");
        LeftPullerServo = hardwareMap.servo.get("s2");
        armJoint1 = hardwareMap.crservo.get("arm1");

        /**
         * print out status
         */
        telemetry.addData("Status:", "Initialized");

        /**
         * Reverse Approprate Motors
         */
        FrontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        RearLeftMotor.setDirection(DcMotor.Direction.REVERSE);

    }

    public void printStatus(){
        telemetry.addData("Game pad 1 stick status:","-------------------------");
        telemetry.addData("Left stick x", gamepad1.left_stick_x);
        telemetry.addData("Right stick y", gamepad1.right_stick_y);
        telemetry.addData("Right stick x", gamepad1.right_stick_x);

    }

    public void init(){

        mapHardware();

        telemetry.addData("Robot Status", "Initalized");

    }


    public void loop() {
        telemetry.addData("Robot Status:","Running");
        printStatus();

        driveTrain.mecanumDrive(x,y,rotationValue,FrontLeftMotor,FrontRightMotor,RearRightMotor,RearLeftMotor);

    }


}
