package com.team16488.library.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Arrays;
import java.util.Collections;

public class MecanumDrive extends Subsystem {
    DcMotor FrontLeftMotor;
    DcMotor FrontRightMotor;
    DcMotor RearRightMotor;
    DcMotor RearLeftMotor;

    private double FrontRightpower;
    private double FrontLeftpower;
    private double RearRightpower;
    private double RearLeftpower;


    public  MecanumDrive(HardwareMap map){
        FrontLeftMotor = map.dcMotor.get("m4");
        FrontRightMotor = map.dcMotor.get("m3");
        RearRightMotor = map.dcMotor.get("m2");
        RearLeftMotor = map.dcMotor.get("m1");
    }

    @Override
    public void update() {
        FrontLeftMotor.setPower(FrontLeftpower);
        FrontRightMotor.setPower(FrontRightpower);
        RearLeftMotor.setPower(RearLeftpower);
        RearRightMotor.setPower(RearRightpower);
    }

    public void setVelocity(double leftstickx, double leftsticky, double rightstickx){
        double r = Math.hypot(leftstickx, leftsticky);
        double robotAngle = Math.atan2(leftsticky, leftstickx) - Math.PI / 4;
        double rightX = rightstickx;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        this.FrontLeftpower = v1;
        this.FrontRightpower = v2;
        this.RearLeftpower = v3;
        this.RearRightpower = v4;



    }


}
