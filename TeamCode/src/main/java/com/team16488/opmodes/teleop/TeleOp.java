package com.team16488.opmodes.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.team16488.control.ChassisControl;
import com.team16488.control.SubsystemControl;
import com.team16488.skystone.Robot;

/**
 * This is the Main Class that the robot looks for
 * this class calls all of the classes that are responsable for the chassis and subsytem control
 * this is the main script that the robot runs when you start the OpMode.
 * <p>
 * All Classes converge at this OpMode
 *
 * @author Parham Baghbanbashi: parhambagh@gmail.com
 * @author Ernest Wong
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Teleop main", group = "teleop")
public class TeleOp extends OpMode {
    /**
     * Robot object
     *
     * @see Robot
     */
    private Robot robot;
    /**
     * ChassisControl object
     *
     * @see ChassisControl
     */
    private ChassisControl chassisControl;
    /**
     * Subsystem control object
     *
     * @see SubsystemControl
     */
    private SubsystemControl subsystemControl;

    /**
     * This is what will be run on initialization of the robot
     * <p>
     * The main function of the method is to construct and create our objects
     * </p>
     */
    public void init() {
        robot = new Robot(this, telemetry);
        subsystemControl = new SubsystemControl(this, telemetry);
        chassisControl = new ChassisControl(this, telemetry);
    }

    /**
     * This method runs once the driver hits start
     * <p>
     * This also runs robot.start
     *
     * @see Robot
     */
    @Override
    public void start() {
        robot.start();
    }

    /**
     * This method runs repetedly after the driver hits start
     * <p>
     * This method runs the Chassis control class and subsystem control class
     *
     * @see ChassisControl
     * @see SubsystemControl
     */
    @Override
    public void loop() {
        chassisControl.driverPad(gamepad1, gamepad2, telemetry);
        subsystemControl.subsystemDriver(gamepad2, telemetry);
    }

    /**
     * This method Runs once the Driver hits stop
     *
     * Stops the robot
     * @see Robot
     */
    @Override
    public void stop() {
        robot.stop();
    }

}
