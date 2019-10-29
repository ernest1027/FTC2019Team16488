package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.teamcode.Drivetrain.SkystoneMecanumDrive;
import org.firstinspires.ftc.teamcode.Drivetrain.TeleopMecanumDrive;
import org.firstinspires.ftc.teamcode.subsytems.PullerServos;

@TeleOp(name="Skystone 2019 Teleop", group="Iterative Opmode")
public class SkystoneTeleop extends OpMode {
    DcMotor FrontLeftMotor;
    DcMotor FrontRightMotor;
    DcMotor RearRightMotor;
    DcMotor RearLeftMotor;
    double y = -gamepad1.right_stick_y;
    //this is what your looking for
    double rotationValue = gamepad1.right_stick_x;
    double x = gamepad1.left_stick_x;
    SkystoneMecanumDrive mecanumDrive = new SkystoneMecanumDrive();
    PullerServos pullerServos = new PullerServos();

    public void init() {
        mecanumDrive.initalize(FrontLeftMotor,FrontRightMotor,RearRightMotor,RearLeftMotor);
    }
    public void loop(){
        mecanumDrive.loopofprogram(x,y,rotationValue,FrontLeftMotor,FrontRightMotor,RearRightMotor,RearLeftMotor);

    }
}
