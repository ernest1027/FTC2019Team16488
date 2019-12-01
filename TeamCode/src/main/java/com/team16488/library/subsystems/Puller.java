package com.team16488.library.subsystems;
/**
 * Deloped by Parham Baghbanbashi and Ernest Wong
 * parhambagh@gmail.com
 */
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Puller extends Subsystem {
    Servo Left, Right;
    private boolean down = false;

    public Puller(HardwareMap map){
        Left = map.servo.get("LP");
        Right = map.servo.get("RP");
    }
    /**
     * this function is what moves the servos and updates the positions of said servos
     */
    @Override
    public void update() {
        if(down == false)
        {
            Right.setPosition(1.0);
            Left.setPosition(1.0);

        }


        if(down == true){
            Left.setPosition(0);
            Right.setPosition(0);

        }

    }

    /**
     * Sets the portion of the stage pullers
     * @param down
     */
    public void setDown(boolean down){
        this.down = down;

    }
}
