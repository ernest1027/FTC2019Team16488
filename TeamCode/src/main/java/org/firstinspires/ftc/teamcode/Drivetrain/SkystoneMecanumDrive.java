package org.firstinspires.ftc.teamcode.Drivetrain;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import team16488.DriveTrain;

public class SkystoneMecanumDrive extends OpMode {

    DriveTrain driveTrain = new DriveTrain();
    public void initalize(DcMotor FrontLeftMotor, DcMotor FrontRightMotor, DcMotor RearRightMotor, DcMotor RearLeftMotor){
        FrontLeftMotor = hardwareMap.dcMotor.get("m4");
        FrontRightMotor = hardwareMap.dcMotor.get("m3");
        RearRightMotor = hardwareMap.dcMotor.get("m2");
        RearLeftMotor = hardwareMap.dcMotor.get("m1");
    }
    public void loopofprogram(double x, double y,double rotationvalue, DcMotor FrontLeftMotor, DcMotor FrontRightMotor, DcMotor RearRightMotor, DcMotor RearLeftMotor){
        driveTrain.mecanumDrive(x,y,rotationvalue,FrontLeftMotor,FrontRightMotor,RearRightMotor,RearLeftMotor);
    }
    public void init() {}
    public void loop() {
    }
}

