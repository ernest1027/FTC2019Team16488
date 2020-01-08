package com.team16488.library.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.team16488.library.subsystems.Subsystem;

/**
 * This class is what lowers the puller for the Build platform
 * its main use is to move/pull the bilud platform by using the
 * puller servos to attach the platform to the main Chassis
 *
 * @author Parham Baghbanbashi: parhambagh@gmail.com
 * @author Ernest Wong
 * <p>github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi</p>
 */
public class Puller extends Subsystem {
    /**
     * Puller servos
     */
    public Servo Left, Right;
    /**
     * Sets if the puller is down
     */
    private boolean down;

    /**
     * This is the Constructor for the Puller Class
     *
     * @param map This is the hardware map of the actual OpMode for the LiftStageOne Class
     */
    public Puller(HardwareMap map) {
        Left = map.servo.get("LP");
        Right = map.servo.get("RP");
    }

    /**
     * This method is what updates the position of the servos
     * based on the down variable
     *
     * <p>See: {@link Subsystem}</p>
     */
    @Override
    public void update() {
        if (!down) {
            Right.setPosition(0.85);
            Left.setPosition(-0.85);

        }
        if (down) {
            Left.setPosition(1.0);
            Right.setPosition(0);

        }

    }

    /**
     * Sets the portion of the stage pullers
     *
     * @param dowen The state of the servos (down, UP)
     *             this is what allows the update to
     *             change the servo position
     */
    public void setDown(boolean dowen) {
        this.down = dowen;

    }
}
