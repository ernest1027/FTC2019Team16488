/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.team16488.opmodes.auto;


import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team16488.skystone.Robot;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.Locale;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

/**
 * This file illustrates the concept of driving a path based on encoder counts.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code REQUIRES that you DO have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByTime;
 *
 *  This code ALSO requires that the drive Motors have been configured such that a positive
 *  power command moves them forwards, and causes the encoders to count UP.
 *
 *   The desired path in this example is:
 *   - Drive forward for 48 inches
 *   - Spin right for 12 Inches
 *   - Drive Backwards for 24 inches
 *   - Stop and close the claw.
 *
 *  The code is written using a method called: encoderDrive(speed, leftInches, rightInches, timeoutS)
 *  that performs the actual movement.
 *  This methods assumes that each movement is relative to the last stopping place.
 *  There are other ways to perform encoder based moves, but this method is probably the simplest.
 *  This code uses the RUN_TO_POSITION mode to enable the Motor controllers to generate the run profile
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="redfoundation", group="Pushbot")

public class redfoundation extends LinearOpMode {

    /* Declare OpMode members. */
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = false;
    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY =
            "AQLgl7n/////AAABme+dNMPhrUUJjKAoNuY8bohUPjuCocER5Fpn94nlG5wvrLJZsJabuSihGcb5US+gHaLRCt20n4q2opXCriEaa+vi2pb3kIMMLuFioUVynCEJrTa9Y/9wPELJUwvpTfq55v6pSWfU/LIFnkTVIqm5OuG6X/KDeA3nTg6ykBYErTSd1zOYUabMdTR+DBKBevHF9NsmHo3/Le3XgCfopFYw049yYAVmRYy+dx84wlLhgF1JBNtDqx4rjQgICRzKQmKuh4EBe39ygQDnFd85uxD6Lbo6VZ3IuQeIrb0nu9eaD4H8oE+jRIvho8d3WJWR8smec0ddud1UFTRdXt69njtluVDe9zSU5vMGOnDn/cw8lQAb";
    // Since ImageTarget trackables use mm to specifiy their dimensions, we must use mm for all the physical dimension.
    // We will define some constants and conversions here
    private static final float mmPerInch = 25.4f;
    private static final float mmTargetHeight = (6) * mmPerInch;          // the height of the center of the target image above the floor
    // Constant for Stone Target
    private static final float stoneZ = 2.00f * mmPerInch;
    // Constants for the center support targets
    private static final float bridgeZ = 6.42f * mmPerInch;
    private static final float bridgeY = 23 * mmPerInch;
    private static final float bridgeX = 5.18f * mmPerInch;
    private static final float bridgeRotY = 59;                                 // Units are degrees
    private static final float bridgeRotZ = 180;
    // Constants for perimeter targets
    private static final float halfField = 72 * mmPerInch;
    private static final float quadField = 36 * mmPerInch;
    // IMPORTANT:  For Phone Camera, set 1) the camera source and 2) the orientation, based on how your phone is mounted:
    // 1) Camera Source.  Valid choices are:  BACK (behind screen) or FRONT (selfie side)
    // 2) Phone Orientation. Choices are: PHONE_IS_PORTRAIT = true (portrait) or PHONE_IS_PORTRAIT = false (landscape)
    //
    // NOTE: If you are running on a CONTROL H
    WebcamName webcamName = null;
    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia = null;
    private boolean targetVisible = false;
    private float phoneXRotate = 0;
    private float phoneYRotate = 0;
    private float phoneZRotate = 0;
    private int asd = 0;
    private double angleFromPhone;

    private ElapsedTime runtime = new ElapsedTime();
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;
    private DcMotor FrontLeftMotor;
    private DcMotor FrontRightMotor;
    private DcMotor RearLeftMotor;
    private DcMotor RearRightMotor;
    private Servo Left,Right;
    public double pos;
    public Servo alternateIntakeRaise, alternateIntakeClose;


    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 0.3 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.1 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.3;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {

        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         * We can pass Vuforia the handle to a camera preview resource (on the RC phone);
         * If no camera monitor is desired, use the parameter-less constructor instead (commented out below).
         */

        webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // TAKE THIS OUT IF NO SCREEN WANTED VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CAMERA_CHOICE;
        // do not use extended tracking; too messy
        parameters.useExtendedTracking = false;
        parameters.cameraName = webcamName;
        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Load the data sets for the trackable objects. These particular data
        // sets are stored in the 'assets' part of our application.
        VuforiaTrackables targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");
        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");
//        VuforiaTrackable blueRearBridge = targetsSkyStone.get(1);
//        blueRearBridge.setName("Blue Rear Bridge");
//        VuforiaTrackable redRearBridge = targetsSkyStone.get(2);
//        redRearBridge.setName("Red Rear Bridge");
//        VuforiaTrackable redFrontBridge = targetsSkyStone.get(3);
//        redFrontBridge.setName("Red Front Bridge");
//        VuforiaTrackable blueFrontBridge = targetsSkyStone.get(4);
//        blueFrontBridge.setName("Blue Front Bridge");
//        VuforiaTrackable red1 = targetsSkyStone.get(5);
//        red1.setName("Red Perimeter 1");
//        VuforiaTrackable red2 = targetsSkyStone.get(6);
//        red2.setName("Red Perimeter 2");
//        VuforiaTrackable front1 = targetsSkyStone.get(7);
//        front1.setName("Front Perimeter 1");
//        VuforiaTrackable front2 = targetsSkyStone.get(8);
//        front2.setName("Front Perimeter 2");
//        VuforiaTrackable blue1 = targetsSkyStone.get(9);
//        blue1.setName("Blue Perimeter 1");
//        VuforiaTrackable blue2 = targetsSkyStone.get(10);
//        blue2.setName("Blue Perimeter 2");
//        VuforiaTrackable rear1 = targetsSkyStone.get(11);
//        rear1.setName("Rear Perimeter 1");
//        VuforiaTrackable rear2 = targetsSkyStone.get(12);
//        rear2.setName("Rear Perimeter 2");

        // For convenience, gather together all the trackable objects in one easily-iterable collection */
//        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
//        allTrackables.addAll(targetsSkyStone);

        /**
         * In order for localization to work, we need to tell the system where each target is on the field, and
         * where the phone resides on the .  These specifications are in the form of <em>transformation matrices.</em>
         * Transformation matrices are a central, important concept in the math here involved in localization.
         * See <a href="https://en.wikipedia.org/wiki/Transformation_matrix">Transformation Matrix</a>
         * for detailed information. Commonly, you'll encounter transformation matrices as instances
         * of the {@link OpenGLMatrix} class.
         *
         * If you are standing in the Red Alliance Station looking towards the center of the field,
         *     - The X axis runs from your left to the right. (positive from the center to the right)
         *     - The Y axis runs from the Red Alliance Station towards the other side of the field
         *       where the Blue Alliance Station is. (Positive is from the center, towards the BlueAlliance station)
         *     - The Z axis runs from the floor, upwards towards the ceiling.  (Positive is above the floor)
         *
         * Before being transformed, each target image is conceptually located at the origin of the field's
         *  coordinate system (the center of the field), facing up.
         */

        // Set the position of the Stone Target.  Since it's not fixed in position, assume it's at the field origin.
        // Rotated it to to face forward, and raised it to sit on the ground correctly.
        // This can be used for generic target-centric approach algorithms
        stoneTarget.setLocation(OpenGLMatrix
                .translation(0, 0, stoneZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));

//        //Set the position of the bridge support targets with relation to origin (center of field)
//        blueFrontBridge.setLocation(OpenGLMatrix
//                .translation(-bridgeX, bridgeY, bridgeZ)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, bridgeRotY, bridgeRotZ)));
//
//        blueRearBridge.setLocation(OpenGLMatrix
//                .translation(-bridgeX, bridgeY, bridgeZ)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, -bridgeRotY, bridgeRotZ)));
//
//        redFrontBridge.setLocation(OpenGLMatrix
//                .translation(-bridgeX, -bridgeY, bridgeZ)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, -bridgeRotY, 0)));
//
//        redRearBridge.setLocation(OpenGLMatrix
//                .translation(bridgeX, -bridgeY, bridgeZ)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, bridgeRotY, 0)));
//
//        //Set the position of the perimeter targets with relation to origin (center of field)
//        red1.setLocation(OpenGLMatrix
//                .translation(quadField, -halfField, mmTargetHeight)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 180)));
//
//        red2.setLocation(OpenGLMatrix
//                .translation(-quadField, -halfField, mmTargetHeight)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 180)));
//
//        front1.setLocation(OpenGLMatrix
//                .translation(-halfField, -quadField, mmTargetHeight)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , 90)));
//
//        front2.setLocation(OpenGLMatrix
//                .translation(-halfField, quadField, mmTargetHeight)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 90)));
//
//        blue1.setLocation(OpenGLMatrix
//                .translation(-quadField, halfField, mmTargetHeight)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0)));
//
//        blue2.setLocation(OpenGLMatrix
//                .translation(quadField, halfField, mmTargetHeight)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0)));
//
//        rear1.setLocation(OpenGLMatrix
//                .translation(halfField, quadField, mmTargetHeight)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , -90)));
//
//        rear2.setLocation(OpenGLMatrix
//                .translation(halfField, -quadField, mmTargetHeight)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));

        //
        // Create a transformation matrix describing where the phone is on the robot.
        //
        // NOTE !!!!  It's very important that you turn OFF your phone's Auto-Screen-Rotation option.
        // Lock it into Portrait for these numbers to work.
        //
        // Info:  The coordinate frame for the robot looks the same as the field.
        // The robot's "forward" direction is facing out along X axis, with the LEFT side facing out along the Y axis.
        // Z is UP on the robot.  This equates to a bearing angle of Zero degrees.
        //
        // The phone starts out lying flat, with the screen facing Up and with the physical top of the phone
        // pointing to the LEFT side of the Robot.
        // The two examples below assume that the camera is facing forward out the front of the robot.

        // We need to rotate the camera around it's long axis to bring the correct camera forward.
        if (CAMERA_CHOICE == BACK) {
            phoneYRotate = -90;
        } else {
            phoneYRotate = 90;
        }

        // Rotate the phone vertical about the X axis if it's in portrait mode
        if (PHONE_IS_PORTRAIT) {
            phoneXRotate = 90;
        }

        // Next, translate the camera lens to where it is on the robot.
        // In this example, it is centered (left to right), but forward of the middle of the robot, and above ground level.
//        final float CAMERA_FORWARD_DISPLACEMENT  = 4.0f * mmPerInch;   // eg: Camera is 4 Inches in front of robot center
//        final float CAMERA_VERTICAL_DISPLACEMENT = 8.0f * mmPerInch;   // eg: Camera is 8 Inches above ground
        final float CAMERA_FORWARD_DISPLACEMENT = 0;   // eg: Camera is 4 Inches in front of robot center
        final float CAMERA_VERTICAL_DISPLACEMENT = 0;
        final float CAMERA_LEFT_DISPLACEMENT = 0;     // eg: Camera is ON the robot's center line

        OpenGLMatrix robotFromCamera = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));
        ((VuforiaTrackableDefaultListener) stoneTarget.getListener()).setPhoneInformation(robotFromCamera, parameters.cameraDirection);

        sensorColor = hardwareMap.get(ColorSensor.class, "colour");

        // get a reference to the distance sensor that shares the same name.
        sensorDistance = hardwareMap.get(DistanceSensor.class, "colour");

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float[] hsvValues = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float[] values = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        FrontLeftMotor = hardwareMap.get(DcMotor.class, "FL");
        FrontRightMotor = hardwareMap.get(DcMotor.class, "FR");
        RearRightMotor = hardwareMap.get(DcMotor.class, "BR");
        RearLeftMotor = hardwareMap.get(DcMotor.class, "BL");
        Left = hardwareMap.servo.get("LP");
        Right = hardwareMap.servo.get("RP");

        alternateIntakeRaise = hardwareMap.servo.get("alternate intake raise");
        alternateIntakeClose = hardwareMap.servo.get("alternate intake close");
        FrontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();


        // Send telemetry message to indicate successful Encoder reset


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        runtime.reset();
        targetsSkyStone.activate();
        FrontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FrontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                FrontLeftMotor.getCurrentPosition(),
                FrontRightMotor.getCurrentPosition());
        telemetry.update();
        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
   /*  encoderDrive(DRIVE_SPEED,  48,  48, 5.0);  // S1: Forward 47 Inches with 5 Sec timeout
        sleep(500);
        encoderMecanumDrive(TURN_SPEED,   true, 60, 4.0);  // S2: Turn Right 12 Inches with 4 Sec timeout
        sleep(1000);
        encoderDrive(DRIVE_SPEED, -24, -24, 4.0);  // S3: Reverse 24 Inches with 4 Sec timeout
        sleep(500);
        encoderDrive(DRIVE_SPEED, -24, 24, 4.0);  // S3: Reverse 24 Inches with 4 Sec timeout
        sleep(500);
        encoderDrive(DRIVE_SPEED, 24, -24, 4.0);  // S3: Reverse 24 Inches with 4 Sec timeout

        setPuller(true);
        sleep(1000);     // pause for servos to move
        setPuller(false);
        sleep(1000);
        setPuller(true);
        sleep(1000);
        */
        /*setIntake(true, true, true);
        telemetry.addData("gay", pos);
        telemetry.update();
        sleep(1000);
        setIntake(true, true, false);
        telemetry.addData("gay", pos);
        telemetry.update();
        sleep(1000);
        setIntake(true, false, false);
        telemetry.addData("gay", pos);
        telemetry.update();
        sleep(1000);
        setIntake(false, true, true);
        telemetry.addData(   "gay", pos);
        telemetry.update();
        sleep(1000);
        setPos(1);
        telemetry.addData("gay", pos);
        telemetry.update();
        sleep(1000);
        setPos(0.75);
        telemetry.addData("gay", pos);
        telemetry.update();
        sleep(1000);
        setPos(0.5);
        telemetry.addData("gay", pos);
        telemetry.update();
        sleep(1000);
        setPos(0.25);
        telemetry.addData("gay", pos);
        telemetry.update();
        sleep(1000);
        setPos(0);
        telemetry.addData("gay", pos);
        telemetry.update();
        sleep(1000);
        setPos(-0.25);
        telemetry.addData("gay", pos);
        telemetry.update();
        sleep(1000);
        setPos(-0.5);
        telemetry.addData("gay", pos);
        telemetry.update();
        sleep(1000);
        setPos(-0.75);
        telemetry.addData("gay", pos);
        telemetry.update();
        sleep(1000);
        setPos(-1);
        telemetry.addData("gay", pos);
        telemetry.update();
        sleep(1000);


*/
        /*
       while(true)
       {
           Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                   (int) (sensorColor.green() * SCALE_FACTOR),
                   (int) (sensorColor.blue() * SCALE_FACTOR),
                   hsvValues);

           // send the info back to driver station using telemetry function.
           telemetry.addData("Distance (cm)",
                   String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));
           telemetry.addData("Alpha", sensorColor.alpha());
           telemetry.addData("Red  ", sensorColor.red());
           telemetry.addData("Green", sensorColor.green());
           telemetry.addData("Blue ", sensorColor.blue());
           telemetry.addData("Hue", hsvValues[0]);
           FrontLeftMotor.setPower(-0.2);
           FrontRightMotor.setPower(0.2);
           RearLeftMotor.setPower(0.2);
           RearRightMotor.setPower(-0.2);
           if(sensorDistance.getDistance(DistanceUnit.CM)<8.5)
           {
               break;
           }
       }
       */
        encoderMecanumDrive(0.4, true,40,10);
        encoderDrive(0.4, 40,40, 10);
        setPuller(true);
        sleep(1000);
        encoderDrive(0.4, -40,-40, 10);
        setPuller(false);
        encoderMecanumDrive(0.4, false,40,10);

        /*
        setPos(0.85);
        encoderMecanumDrive(0.3, false, 37, 5);

        stack(50);

        encoderMecanumDrive(0.3, false, 10.5, 5);
        while(true)
        {

            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                    (int) (sensorColor.green() * SCALE_FACTOR),
                    (int) (sensorColor.blue() * SCALE_FACTOR),
                    hsvValues);

            // send the info back to driver station using telemetry function.
            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));
            telemetry.addData("Alpha", sensorColor.alpha());
            telemetry.addData("Red  ", sensorColor.red());
            telemetry.addData("Green", sensorColor.green());
            telemetry.addData("Blue ", sensorColor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            FrontLeftMotor.setPower(0.2);
            FrontRightMotor.setPower(0.2);
            RearLeftMotor.setPower(0.2);
            RearRightMotor.setPower(0.2);
            if(sensorDistance.getDistance(DistanceUnit.CM)<20 && hsvValues[0]>140)
            {
                break;
            }


        }
        FrontLeftMotor.setPower(0);
        FrontRightMotor.setPower(0);
        RearLeftMotor.setPower(0);
        RearRightMotor.setPower(0);
        stack(60);

        encoderMecanumDrive(0.3, false, 10.5, 5);
        while(true)
        {

            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                    (int) (sensorColor.green() * SCALE_FACTOR),
                    (int) (sensorColor.blue() * SCALE_FACTOR),
                    hsvValues);

            // send the info back to driver station using telemetry function.
            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));
            telemetry.addData("Alpha", sensorColor.alpha());
            telemetry.addData("Red  ", sensorColor.red());
            telemetry.addData("Green", sensorColor.green());
            telemetry.addData("Blue ", sensorColor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            FrontLeftMotor.setPower(0.2);
            FrontRightMotor.setPower(0.2);
            RearLeftMotor.setPower(0.2);
            RearRightMotor.setPower(0.2);
            if(sensorDistance.getDistance(DistanceUnit.CM)<20 && hsvValues[0]>140)
            {
                break;
            }


        }
        FrontLeftMotor.setPower(0);
        FrontRightMotor.setPower(0);
        RearLeftMotor.setPower(0);
        RearRightMotor.setPower(0);
        stack(65);

        encoderMecanumDrive(0.3, false, 10.5, 5);
        while(true)
        {

            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                    (int) (sensorColor.green() * SCALE_FACTOR),
                    (int) (sensorColor.blue() * SCALE_FACTOR),
                    hsvValues);

            // send the info back to driver station using telemetry function.
            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));
            telemetry.addData("Alpha", sensorColor.alpha());
            telemetry.addData("Red  ", sensorColor.red());
            telemetry.addData("Green", sensorColor.green());
            telemetry.addData("Blue ", sensorColor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            FrontLeftMotor.setPower(0.2);
            FrontRightMotor.setPower(0.2);
            RearLeftMotor.setPower(0.2);
            RearRightMotor.setPower(0.2);
            if(sensorDistance.getDistance(DistanceUnit.CM)<20 && hsvValues[0]>140)
            {
                break;
            }


        }
        FrontLeftMotor.setPower(0);
        FrontRightMotor.setPower(0);
        RearLeftMotor.setPower(0);
        RearRightMotor.setPower(0);
        stack(70);

        encoderMecanumDrive(0.3, false, 10.5, 5);
        while(true)
        {

            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                    (int) (sensorColor.green() * SCALE_FACTOR),
                    (int) (sensorColor.blue() * SCALE_FACTOR),
                    hsvValues);

            // send the info back to driver station using telemetry function.
            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));
            telemetry.addData("Alpha", sensorColor.alpha());
            telemetry.addData("Red  ", sensorColor.red());
            telemetry.addData("Green", sensorColor.green());
            telemetry.addData("Blue ", sensorColor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            FrontLeftMotor.setPower(0.2);
            FrontRightMotor.setPower(0.2);
            RearLeftMotor.setPower(0.2);
            RearRightMotor.setPower(0.2);
            if(sensorDistance.getDistance(DistanceUnit.CM)<20 && hsvValues[0]>140)
            {
                break;
            }


        }
        FrontLeftMotor.setPower(0);
        FrontRightMotor.setPower(0);
        RearLeftMotor.setPower(0);
        RearRightMotor.setPower(0);
        stack(80);

        encoderMecanumDrive(0.3, false, 10.5, 5);
        while(true)
        {

            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                    (int) (sensorColor.green() * SCALE_FACTOR),
                    (int) (sensorColor.blue() * SCALE_FACTOR),
                    hsvValues);

            // send the info back to driver station using telemetry function.
            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));
            telemetry.addData("Alpha", sensorColor.alpha());
            telemetry.addData("Red  ", sensorColor.red());
            telemetry.addData("Green", sensorColor.green());
            telemetry.addData("Blue ", sensorColor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            FrontLeftMotor.setPower(0.2);
            FrontRightMotor.setPower(0.2);
            RearLeftMotor.setPower(0.2);
            RearRightMotor.setPower(0.2);
            if(sensorDistance.getDistance(DistanceUnit.CM)<20 && hsvValues[0]>140)
            {
                break;
            }


        }
        FrontLeftMotor.setPower(0);
        FrontRightMotor.setPower(0);
        RearLeftMotor.setPower(0);
        RearRightMotor.setPower(0);
        stack(85);





        telemetry.addData("Path", "Complete");
        telemetry.update();
        */

    }


    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        FrontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        FrontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        RearLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        RearRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        FrontLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        FrontRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        RearLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        RearRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller

            newLeftTarget = FrontLeftMotor.getCurrentPosition() + (int) (Math.abs(leftInches) * COUNTS_PER_INCH);
            newRightTarget = FrontRightMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            if(leftInches<0) {
                newLeftTarget = -1*newLeftTarget;
            }
            FrontLeftMotor.setTargetPosition(newLeftTarget);
            FrontRightMotor.setTargetPosition(newRightTarget);
            RearLeftMotor.setTargetPosition(newLeftTarget);
            RearRightMotor.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            FrontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FrontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RearLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RearRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();
            FrontLeftMotor.setPower(Math.abs(speed));
            FrontRightMotor.setPower(Math.abs(speed));
            RearLeftMotor.setPower(Math.abs(speed));
            RearRightMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (FrontLeftMotor.isBusy() && FrontRightMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        FrontLeftMotor.getCurrentPosition(),
                        FrontRightMotor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            FrontLeftMotor.setPower(0);
            FrontRightMotor.setPower(0);
            RearLeftMotor.setPower(0);
            RearRightMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            FrontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FrontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RearLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RearRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }

    }
    public void encoderMecanumDrive(double speed,
                                    boolean direction, double Inches,
                                    double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        FrontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        FrontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        RearLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        RearRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        FrontLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        FrontRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        RearLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        RearRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = FrontLeftMotor.getCurrentPosition() + (int)(Inches * COUNTS_PER_INCH);
            newRightTarget = FrontRightMotor.getCurrentPosition() + (int)(Inches * COUNTS_PER_INCH);

            if(direction)
            {
                newRightTarget = newRightTarget*(-1);
            }
            else
            {
                newLeftTarget = newLeftTarget*(-1);
            }
            FrontLeftMotor.setTargetPosition(newLeftTarget);
            FrontRightMotor.setTargetPosition(newRightTarget);
            RearLeftMotor.setTargetPosition(newRightTarget);
            RearRightMotor.setTargetPosition(newLeftTarget);

            // Turn On RUN_TO_POSITION
            FrontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FrontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RearLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RearRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();
            FrontLeftMotor.setPower(Math.abs(speed));
            FrontRightMotor.setPower(Math.abs(speed));
            RearLeftMotor.setPower(Math.abs(speed));
            RearRightMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (FrontLeftMotor.isBusy() && FrontRightMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        FrontLeftMotor.getCurrentPosition(),
                        FrontRightMotor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            FrontLeftMotor.setPower(0);
            FrontRightMotor.setPower(0);
            RearLeftMotor.setPower(0);
            RearRightMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            FrontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FrontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RearLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RearRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
    public void setPuller(boolean down)
    {
        if (!down) {
            Right.setPosition(0.85);
            Left.setPosition(0.5);

        }
        if (down) {
            Left.setPosition(1.0);
            Right.setPosition(0);

        }
    }
    public void setIntake(boolean ON, boolean down, boolean lock) {
        if (ON) {
            if (down) {
                pos = 1.0;
            } else {
                pos = 0.75;
            }
            if (lock) {

                alternateIntakeClose.setPosition(1.0);
            }
            if (!lock) {

                alternateIntakeClose.setPosition(0);
            }
            alternateIntakeRaise.setPosition(pos);
        } else {
            down = true;

        }

    }
    public void setPos(double pos)
    {
        this.pos=pos;
        alternateIntakeRaise.setPosition(pos);
    }

    public void stack(double dist){
        setPos(0.85);
        encoderMecanumDrive(0.1, false, 4, 5);
        setIntake(true, true, false);
        sleep(1000);
        setIntake(true,true,true);
        sleep(1000);
        setIntake(true,false,true);
        encoderMecanumDrive(0.3, true, 10, 5);
        encoderDrive(0.3, -dist, -dist, 10);
        //encoderMecanumDrive(0.3, false, 10, 5);
        setPos(0.5);
        setIntake(true, false, false);
        sleep(200);
        //encoderMecanumDrive(0.3, true, 12, 5);
        setIntake(true, false, false);
        encoderDrive(0.3  , dist,dist,10);


    }

    public void stack2(double dist)
    {
        setPos(0.85);
        encoderMecanumDrive(0.1, false, 4, 5);
        setIntake(true, true, false);
        sleep(750);
        setIntake(true,true,true);
        sleep(750);
        setIntake(true,false,true);
        encoderMecanumDrive(0.4, true, 16, 5);
        encoderDrive(0.4, -dist, -dist, 10);
        encoderMecanumDrive(0.4, false, 16, 5);
        setPos(0.8);
        setIntake(true, false, false);
        sleep(200);
        encoderMecanumDrive(0.4, true, 12, 5);
        encoderDrive(0.5, -25, 25, 5);
        //encoderDrive(0.5, -40, -40, 5);
        encoderDrive(0.3, 10, 10, 5);
        setPuller(true);
        sleep(2000);
        encoderDrive(0.5, -40, -40, 5);
        setPuller(false);
        sleep(500);
        encoderMecanumDrive(0.4, true, 70, 5);
    }
}
