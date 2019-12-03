package com.team16488.library.subsystems;
/*
 * Deloped by Parham Baghbanbashi and Ernest Wong
 * parhambagh@gmail.com
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm extends Subsystem {
    DcMotor armJoint1, armJoint2;
    private double power;

    public Arm(HardwareMap map) {
        armJoint1 = map.dcMotor.get("AJ1");
        armJoint2 = map.dcMotor.get("AJ2");

    }

    /**
     * this function updates the power of the arm motors based on the "setPower" method
     */
    @Override
    public void update() {
        armJoint2.setPower(power);
        armJoint1.setPower(power);

    }

    /**
     * CHANGES THE POWER VARIABLE FOR THE ARM MOTORS
     *
     * @param power
     */
    public void setPower(double power) {
        this.power = power;
    }
}
