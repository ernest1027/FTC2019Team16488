package com.team16488.library.subsystems;

/**
 * This is the class that the robot looks for when it is running
 * this class alowws the substems to be run from the robot class a lot
 * easier, This class is what every substem extends and is what is ran
 * in the Robot class to make the Robot work
 *
 * @see com.team16488.skystone.Robot
 * github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi
 */
public abstract class Subsystem {
    /**
     * This is the method that updates the various servos and motors on the robot
     * <p>
     * This method is what gives the robot the movement values
     * If the you want to move the motor you would add that movement to the update method
     * that would then be translated in the robot class and that movement would
     * be in its own thread.
     * </p>
     *
     * @see com.team16488.skystone.Robot
     */
    public abstract void update();
}

