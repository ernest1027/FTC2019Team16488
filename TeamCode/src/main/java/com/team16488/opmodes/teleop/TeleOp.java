/* Developed by Parham Baghbanbashi
   parhambagh@gmail.com
 */

package com.team16488.opmodes.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.team16488.control.DriverControl;
import com.team16488.control.SubsystemControl;
import com.team16488.skystone.Robot;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Teleop main", group = "teleop")
public class TeleOp extends OpMode {
    public Robot robot;
    public DriverControl driverControl;
    public SubsystemControl subsystemControl;
    double slowmode = 0.8;
    double vpower = 0.0;
    double hpower = 0.0;
    boolean isOn = false;
    boolean reverse = false;
    boolean up = false;


    boolean clawOpen;


    public void init() {
        robot = new Robot(this, telemetry);
        subsystemControl = new SubsystemControl(this, telemetry);
        driverControl = new DriverControl(this, telemetry);
        robot.start();


    }

    @Override
    public void start() {
    }


    @Override
    public void loop() {
        driverControl.driverPad(gamepad1, gamepad2, telemetry);
        subsystemControl.subsystemDriver(gamepad2, telemetry);
    }

    @Override
    public void stop() {
        robot.stop();
    }

}
