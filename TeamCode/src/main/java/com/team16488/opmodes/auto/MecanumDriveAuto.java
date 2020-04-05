package com.team16488.opmodes.auto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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
     * Puller servos
     */
    public Servo Left, Right;
    public Servo alternateIntakeRaise, alternateIntakeClose;
    /**
     * Sets if the puller is down
     */
    private double pos;
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
        Left = map.servo.get("LP");
        Right = map.servo.get("RP");
        alternateIntakeRaise = map.servo.get("alternate intake raise");
        alternateIntakeClose = map.servo.get("alternate intake close");


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

    public void setPuller(boolean down)
    {
        if (!down) {
            Right.setPosition(0.85);
            Left.setPosition(0.5);

        }
        if (down) {
            Left.setPosition(1.0);
            Right.setPosition(0);

        }
    }
    public void setAlternateIntake(boolean ON, boolean lock, boolean down) {
        if (ON) {
            if (down) {
                pos = 1.0;
            }else {
                pos = 0;
            }
            if (lock) {

                alternateIntakeClose.setPosition(1.0);
            }
            if (lock) {

                alternateIntakeClose.setPosition(0);
            }
            alternateIntakeRaise.setPosition(pos);
        } else {
            down = true;

        }


    }


}
