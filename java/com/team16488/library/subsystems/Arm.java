package com.team16488.library.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm extends Subsystem {
    private double power;

    DcMotor armJoint1, armJoint2;

    public Arm(HardwareMap map){
        armJoint1 = map.dcMotor.get("AJ1");
        armJoint2 = map.dcMotor.get("AJ2");

    }
    @Override
    public void update() {
        armJoint2.setPower(power);
        armJoint1.setPower(power);

    }

    public void setPower(double power){
        this.power = power;
    }
}
