package com.team16488.library.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team16488.library.subsystems.Subsystem;

/**
 * This class controls the intake of the robot.
 * the intake is responsible for the acquiring of
 * the skyblocks.
 * <p>github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi</p>
 * @author Parham Baghbanbashi
 * @author Ernest Wong
 */
public class Intake extends Subsystem {

    /**
     * Servo group of the intake
     */
    private static final String[] MOTOR_NAMES = {"LeftIntake", "RightIntake"};
    private DcMotor[] motors;
    private double[] powers;
    /**
     * Sets the state of the intake. ON. OFF
     */
    public boolean isOn = false;
    /**
     * Sets if the intake is reverse
     */
    private boolean reverse = false;

    /**
     * This is the Class constructor
     * <p>
     * This constructor has all of the servos for
     * the arm head, the main purpose is so that the servo classes can actually work
     * </p>
     *
     * @param map This is the hardware map of the actual OpMode for the Intake
     */
    public Intake(HardwareMap map) {
        powers = new double[2];
        motors = new DcMotor[2];

        for (int i = 0; i < 2; i++) {
            DcMotor dcMotor = map.get(DcMotor.class, MOTOR_NAMES[i]);
            motors[i] = dcMotor;
            motors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        }
    }

    /**
     * This Method updates the servos based on the private variables
     *
     * <p>See: {@link Subsystem}</p>
     */
    @Override
    public void update() {
        if (isOn) {

            if (reverse) {
                motors[0].setPower(1.0);
                motors[1].setPower(-1.0);
            } else {
                motors[0].setPower(-1.0);
                motors[1].setPower(1.0);
            }
        } else {
            for (int i = 0; i < 2; i++) {
                motors[i].setPower(0);
            }
        }
    }

    /**
     * Sets the intake position to reverse
     *
     * @param reverse boolean variable that sets weather or not the intakes are reverse
     */
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    /**
     * Sets the intake as on or off
     *
     * @param on boolean, sets if the intake is on
     */
    public void setOn(boolean on) {
        this.isOn = on;
    }


}
