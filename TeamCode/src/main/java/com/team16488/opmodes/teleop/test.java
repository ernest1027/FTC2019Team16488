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
 * <p>github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi</p>
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class test extends OpMode {
    /**
     * Robot object
     *
     * <p>See: {@link com.team16488.skystone.Robot}</p>
     */
    private Robot robot;
    /**
     * ChassisControl object
     *
     * <p>See: {@link ChassisControl}</p>
     */

    /**
     * Subsystem control object
     *
     * <p>See: {@link SubsystemControl}</p>
     */


    /**
     * This is what will be run on initialization of the robot
     * <p>
     * The main function of the method is to construct and create our objects
     * </p>
     */
    public void init() {
        robot = new Robot(this, telemetry);

    }


    /**
     * This method runs once the driver hits start
     * <p>
     * This also runs robot.start
     *
     * <p>See: {@link Robot}</p>
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
     * <p>See: {@link ChassisControl}</p>
     * <p>See: {@link SubsystemControl}</p>
     */
    @Override
    public void loop() {
        robot.alternateIntake.ON = true;
        if (gamepad1.a) {
            robot.alternateIntake.setPos(0);


        }
        if (gamepad1.b) {
            robot.alternateIntake.setPos(0.4);

        }
        if (gamepad1.x) {
            robot.alternateIntake.setPos(0.5);
        }
        if (gamepad1.y) {
            robot.alternateIntake.setPos(1.0);
        }
        if (gamepad1.left_bumper) {
            robot.alternateIntake.setPos(-0.4);
        }
        if (gamepad1.right_bumper) {
            robot.alternateIntake.setPos((-0.5));
        }
        if (gamepad1.start) {
            robot.alternateIntake.setPos(-1.0);
        }

        telemetry.addData("pos", robot.alternateIntake.getPos());
        telemetry.update();

    }


    /**
     * This method Runs once the Driver hits stop
     * <p>
     * Stops the robot
     * <p>See: {@link Robot}</p>
     */
    @Override
    public void stop() {
        robot.stop();
    }


}
