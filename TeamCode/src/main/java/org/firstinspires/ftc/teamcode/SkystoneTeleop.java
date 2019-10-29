// author Parham baghbanbashi email: parhambagh@gmail.com
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.teamcode.Drivetrain.SkystoneMecanumDrive;
import org.firstinspires.ftc.teamcode.Drivetrain.TeleopMecanumDrive;
import org.firstinspires.ftc.teamcode.subsytems.ClawHeadMovement;
import org.firstinspires.ftc.teamcode.subsytems.PullerServos;

@TeleOp(name="Skystone 2019 Teleop", group="Iterative Opmode")
public class SkystoneTeleop extends OpMode {
    DcMotor FrontLeftMotor;
    DcMotor FrontRightMotor;
    DcMotor RearRightMotor;
    DcMotor RearLeftMotor;
    Servo RightHeadServo;
    Servo LeftHeadServo;

    SkystoneMecanumDrive mecanumDrive = new SkystoneMecanumDrive();
    ClawHeadMovement clawHeadMovement = new ClawHeadMovement();

    double y = -gamepad1.right_stick_y;
    double rotationValue = gamepad1.right_stick_x;
    double x = gamepad1.left_stick_x;


    public void init() {
        mecanumDrive.initalize(FrontLeftMotor,FrontRightMotor,RearRightMotor,RearLeftMotor);
        clawHeadMovement.initalize(RightHeadServo,LeftHeadServo);
    }
    public void loop(){
        mecanumDrive.loopofprogram(x,y,rotationValue,FrontLeftMotor,FrontRightMotor,RearRightMotor,RearLeftMotor);
        clawHeadMovement.loopofprogram(RightHeadServo,LeftHeadServo,gamepad1.right_bumper,gamepad1.right_trigger);

    }
}
