package com.team16488.library.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * This Class is what controls the Drive Train
 * in this case the drive train is a mecanum drive.
 *
 * @author Parham Baghbanbashi: parhambagh@gmail.com
 * @author Ernest Wong
 */
public class MecanumDrive extends Subsystem {
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
    public MecanumDrive(HardwareMap map) {
        FrontLeftMotor = map.dcMotor.get("FL");
        FrontRightMotor = map.dcMotor.get("FR");
        RearRightMotor = map.dcMotor.get("BR");
        RearLeftMotor = map.dcMotor.get("BL");
        FrontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    /**
     * This function sets the vairous speeds according to the
     * power variables
     *
     * @see Subsystem
     */
    @Override
    public void update() {
        FrontLeftMotor.setPower(FrontLeftpower);
        FrontRightMotor.setPower(FrontRightpower);
        RearLeftMotor.setPower(RearLeftpower);
        RearRightMotor.setPower(RearRightpower);
    }

    /**
     * Sets the velocity of the Chassis
     * <p>
     *     This function does the necessary calculations for the speed and direction
     *     of the chassis.
     * </p>
     *
     * @param leftstickx x axis of the Left Stick of the Joystick
     * @param leftsticky y axis of the Left Stick of the Joystick
     * @param rightstickx x axis of the Right Stick of the Joystick
     */
    public void setVelocity(double leftstickx, double leftsticky, double rightstickx) {
        double r = Math.hypot(leftstickx, leftsticky);
        double robotAngle = Math.atan2(leftsticky, leftstickx) - Math.PI / 4;

        final double v1 = r * Math.cos(robotAngle) + rightstickx;
        final double v2 = r * Math.sin(robotAngle) - rightstickx;
        final double v3 = r * Math.sin(robotAngle) + rightstickx;
        final double v4 = r * Math.cos(robotAngle) - rightstickx;

        this.FrontLeftpower = v1;
        this.FrontRightpower = v2;
        this.RearLeftpower = v3;
        this.RearRightpower = v4;

    }


}
