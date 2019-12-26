package com.team16488.opmodes.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team16488.automated_proccess.ArmRealese;
import com.team16488.control.Control;
import com.team16488.control.general.ChassisControl;
import com.team16488.control.general.SubsystemControl;
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
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp Main", group = "telop")
public class TeleOp extends OpMode {
    /**
     * Robot object
     *
     * <p>See: {@link com.team16488.skystone.Robot}</p>
     */
    private Robot robot;

    /**
     * This is what will be run on initialization of the robot
     * <p>
     * The main function of the method is to construct and create our objects
     * </p>
     */
    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime gameTime = new ElapsedTime();

    private ArmRealese armRealese;

    private Control control;

    public void init() {
        robot = new Robot(this, telemetry);
        control = new Control(this, robot);
        // armRealese = new ArmRealese(robot);


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
        runtime.reset();
        gameTime.reset();
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
        /*
        while (currentTime < 10) {
            armRealese.procces(currentTime);
        }
        */

        //armRealese.procces(currentTime);
        //subsystemControl.subsystemDriverPad(telemetry);
        //telemetry.addData("tick count for the LiftStageOne", robot.LiftStageOne.LiftBottom.getCurrentPosition());
        control.control();
    }

    /**
     * This method Runs once the Driver hits stop
     *
     * Stops the robot
     * <p>See: {@link Robot}</p>
     */
    @Override
    public void stop() {
        robot.stop();
    }


}
