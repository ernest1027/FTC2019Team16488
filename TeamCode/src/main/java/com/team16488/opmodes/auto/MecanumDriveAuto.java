package com.team16488.opmodes.auto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDriveAuto {
    /**
     * The Front Left Motor
     */
    private DcMotor FrontLeftMotor;
    /**
     * The Front Right Motor
     */
    private DcMotor FrontRightMotor;
    /**
     * The Rear Right Motor
     */
    private DcMotor RearRightMotor;
    /**
     * The Rear Left Motor
     */
    private DcMotor RearLeftMotor;

    /**
     * Sets the power/speed of the Front Right Motor
     */
    private double FrontRightpower;
    /**
     * Sets the power/speed of the Front Left Motor
     */
    private double FrontLeftpower;
    /**
     * Sets the power/speed of the Rear Right Motor
     */
    private double RearRightpower;
    /** Sets the power/speed of the Rear Left Motor*/
    private double RearLeftpower;

    /**
     * This is the Constructor of the Mecanum Drive class
     * <p>
     *     This constructor allows the robot to find not only the class
     *     but also the motors and necessary hardware components
     * </p>
     *
     * @param map This is the hardware map of the actual OpMode for the Mecanum Drive
     */
    public MecanumDriveAuto(HardwareMap map) {
        FrontLeftMotor = map.dcMotor.get("FL");
        FrontRightMotor = map.dcMotor.get("FR");
        RearRightMotor = map.dcMotor.get("BR");
        RearLeftMotor = map.dcMotor.get("BL");
        FrontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }


    public void setVelocity(double leftstickx, double leftsticky, double rightstickx) {
        double r = Math.hypot(leftstickx, leftsticky);
        double robotAngle = Math.atan2(leftsticky, leftstickx) - Math.PI / 4;

        final double v1 = r * Math.cos(robotAngle) + rightstickx;
        final double v2 = r * Math.sin(robotAngle) - rightstickx;
        final double v3 = r * Math.sin(robotAngle) + rightstickx;
        final double v4 = r * Math.cos(robotAngle) - rightstickx;

        this.FrontLeftMotor.setPower(v1);
        this.FrontRightMotor.setPower(v2);
        this.RearLeftMotor.setPower(v3);
        this.RearRightMotor.setPower(v4);

    }

}
