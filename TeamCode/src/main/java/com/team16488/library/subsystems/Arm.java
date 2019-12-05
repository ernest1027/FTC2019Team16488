
package com.team16488.library.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * This is the subsystem that controls the arm of the robot
 * the arm is used to move the claw to its intend location
 * it runs off two motors that control the linear motion
 *
 * @author Parham Baghbanbashi. parhambagh@gmail.com
 * @author Ernest Wong
 * <p>
 *     github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi
 * </p>
 */
public class Arm extends Subsystem {
    /**
     * Defines the Arm Motors
     */
    private DcMotor armJoint1, armJoint2;

    /**
     * Is the power variable used to set the motors speed
     */
    private double power;

    /**
     * This is the hardware map for the two motors on the arm.
     * This is need to allow the usage of the motors.
     *
     * @param map This is what maps the arm motors for the actual OpMode
     *            this is what the robot looks for when looking at what is
     *            connected
     */
    public Arm(HardwareMap map) {

        armJoint1 = map.dcMotor.get("AJ1");
        armJoint2 = map.dcMotor.get("AJ2");

    }


    /**
     * This is the update Method this method handles the movement of the motors based on the value
     * of the power variable
     * @see Subsystem
     */
    @Override
    public void update() {

        armJoint2.setPower(power);
        armJoint1.setPower(power);

    }

    /**
     * This Method changes the power variable which in essence sets the speed of the arm
     *
     * @param power This is the double that will be used outside of the class to change the
     *              private power variable
     */
    public void setPower(double power) {
        this.power = power;
    }
}
