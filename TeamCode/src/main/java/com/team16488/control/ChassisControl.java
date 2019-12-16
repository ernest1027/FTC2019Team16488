package com.team16488.control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.control.chassis.AlternateIntakeControl;
import com.team16488.control.chassis.DrivetrianControl;
import com.team16488.control.chassis.IntakeControl;
import com.team16488.control.chassis.PullerControl;
import com.team16488.skystone.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This is the Class that Controls the Chassis of the robot
 * this class takes the gamepad inputs and transforms them
 * into movement using the robot class which uses the
 * various subsystem classes.
 *
 *
 * @author Parham Baghbanbashi
 * <p>See: {@link com.team16488.skystone.Robot}</p>
 * <p>
 *    github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi
 * </p>
 *
 */
public class ChassisControl {
    /**
     * This is the robot object
     */
    private Robot robot;
    /** sets the intake on reverse mode*/
    private boolean reverse = false;
    /** this turns the intake on */
    private boolean On = false;

    private Gamepad chassisControl;

    private boolean crainmode;

    private AlternateIntakeControl alternateIntakeControl;

    private DrivetrianControl drivetrianControl;

    private IntakeControl intakeControl;

    private PullerControl pullerControl;

    private OpMode opMode;


    /**
     * This is the constructor for a chassis control this allows the opmode to run the code in the class
     * @param opMode The opmode that this class is being used in
     * @param oprobot   The robot object in the acctual OpMode that it
     *                  is being used ins.
     */
    public ChassisControl(OpMode opMode, Robot oprobot) {
        robot = oprobot;
        chassisControl = opMode.gamepad1;
        this.opMode = opMode;
    }

    /**
     * This is the method that handles the control of the chassis via driver inputs
     *
     * @param telemetry the telemetry for the class.
     */
    public void driverPad(Telemetry telemetry) {
        alternateIntakeControl = new AlternateIntakeControl(opMode, robot);
        drivetrianControl = new DrivetrianControl(opMode, robot);
        intakeControl = new IntakeControl(opMode, robot);
        pullerControl = new PullerControl(opMode, robot);

        alternateIntakeControl.alternateIntakeControl();
        drivetrianControl.driveControl();
        intakeControl.intakeCOntrol();
        pullerControl.pullerControl();


        telemetry.addData("Subsystem Status", "ON");
        telemetry.addData("----------------------------------------------", " ");
        telemetry.addData("Gamepad1 start", chassisControl.start);
        telemetry.addData("Gamepad1 right bumper", chassisControl.right_bumper);
        telemetry.addData("Gamepad1 left bumper", chassisControl.left_bumper);

 /*
        robot.drive.setVelocity(chassisControl.left_stick_x, -chassisControl.left_stick_y, chassisControl.right_stick_x);

        if (chassisControl.right_stick_x == 0 && chassisControl.left_stick_y == 0 && chassisControl.left_stick_x == 0) {
            double slowmode = 0.5;
            robot.drive.setVelocity(-subsystemChassisControl.left_stick_x * slowmode, -subsystemChassisControl.right_stick_y * slowmode, -subsystemChassisControl.right_stick_x * slowmode);
        }

        if (chassisControl.x) {
            On = false;
            reverse = false;

        }
        if (chassisControl.right_bumper) {
            On = true;

        }
        if (chassisControl.right_trigger != 0) {
            On = true;
            reverse = false;
        }
        if (chassisControl.right_bumper) {
            reverse = true;
        }

        if (On) {
            robot.intake.setOn(true);
            telemetry.addData("state", "Intake on");
        }
        if (!On) {
            robot.intake.setOn(false);
            telemetry.addData("state", "Intake off");
        }

        robot.intake.setReverse(reverse);

        if (chassisControl.left_trigger != 0) {
            robot.puller.setDown(true);
        }

        if (chassisControl.left_bumper) {
            robot.puller.setDown(false);
        }


        if (chassisControl.dpad_up) {
            robot.alternateIntake.setPos(-0.5);
        }
        if (chassisControl.start) {
            robot.alternateIntake.ON = true;
        }
        if (chassisControl.b) {
            robot.alternateIntake.setDown(true);
        }
        if (chassisControl.y) {
            robot.alternateIntake.setDown(false);
        }
        if (robot.alternateIntake.state) {
            telemetry.addData("Alternate intake state", "No Block");

        }
        if (!robot.alternateIntake.state) {
            telemetry.addData("Alternate intake state", "You Have Block");

        }
*/
    }
}
