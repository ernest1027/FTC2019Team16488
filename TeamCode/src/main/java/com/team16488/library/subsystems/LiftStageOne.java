package com.team16488.library.subsystems;


import android.service.autofill.DateValueSanitizer;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team16488.library.subsystems.Subsystem;

/**
 * This class controls the Double Reverse 4 bar
 *
 * @author Parham Baghbanbashi
 * @author Eernest Wong
 * <p>github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi</p>
 */
public class LiftStageOne extends Subsystem {

    /**
     * Sets the speed of the Double Reverse 4 Bar
     */
    public double position;

    /**
     * The servo groups that will be Controlled
     */
    public DcMotor LiftLeft, LiftRight;

    public double power;

    /**
     * This is the constructor for the subsystem
     *
     * @param map This is the hardware map of the actual OpMode for the LiftStageOne Class
     */
    public LiftStageOne(HardwareMap map) {
        LiftLeft = map.dcMotor.get("LiftLeft");
        LiftRight = map.dcMotor.get("LiftRight");

        LiftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LiftLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LiftRight.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    /**
     * This function is what changes the powers of the crservos when the setpower function changes the
     * variable power.
     *
     * <p>See: {@link Subsystem}</p>
     */
    @Override
    public void update() {
        LiftRight.setPower(position);
        LiftLeft.setPower(position);
    }

    /**
     * Sets the speed of the LiftStageOne
     *
     * @param power sets the power of the LiftStageOne
     */
    public void setPower(double power) {
        this.position = power;
    }



}
