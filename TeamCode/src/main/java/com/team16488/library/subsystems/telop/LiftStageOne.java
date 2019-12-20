package com.team16488.library.subsystems.telop;


import com.qualcomm.robotcore.hardware.DcMotor;
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
    public double position = 0;

    /**
     * The servo groups that will be Controlled
     */
    public DcMotor LiftLeft, LiftRight;


    /**
     * This is the constructor for the subsystem
     *
     * @param map This is the hardware map of the actual OpMode for the LiftStageOne Class
     */
    public LiftStageOne(HardwareMap map) {
        LiftLeft = map.dcMotor.get("LiftLeft");
        LiftRight = map.dcMotor.get("LiftRight");
        LiftLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LiftRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    /**
     * This function is what changes the powers of the crservos when the setpower function changes the
     * variable power.
     *
     * <p>See: {@link Subsystem}</p>
     */
    @Override
    public void update() {
        LiftLeft.setTargetPosition((int) position);
        LiftRight.setTargetPosition((int) position);

        LiftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LiftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    /**
     * Sets the speed of the LiftStageOne
     *
     * @param position sets the position of the LiftStageOne
     */
    public void setPosition(double position) {
        this.position = (int) position;
    }


}
