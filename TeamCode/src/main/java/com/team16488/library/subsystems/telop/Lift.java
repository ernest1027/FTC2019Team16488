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
public class Lift extends Subsystem {

    /**
     * Sets the speed of the Double Reverse 4 Bar
     */
    public double position = 0;

    /**
     * The servo groups that will be Controlled
     */
    public DcMotor LiftTop, LiftBottom;




    /**
     * This is the constructor for the subsystem
     *
     * @param map This is the hardware map of the actual OpMode for the Lift Class
     */
    public Lift(HardwareMap map) {
        LiftTop = map.dcMotor.get("LTOP");
        LiftBottom = map.dcMotor.get("LBOTTOM");
        LiftTop.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LiftBottom.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    /**
     * This function is what changes the powers of the crservos when the setpower function changes the
     * variable power.
     *
     * <p>See: {@link Subsystem}</p>
     */
    @Override
    public void update() {
        LiftTop.setTargetPosition((int) position);
        LiftBottom.setTargetPosition((int) position);

        LiftBottom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LiftTop.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    /**
     * Sets the speed of the lift
     *
     * @param position sets the position of the lift
     */
    public void setPosition(double position) {
        this.position = (int) position;
    }


}
