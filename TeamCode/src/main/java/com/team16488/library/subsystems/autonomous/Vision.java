package com.team16488.library.subsystems.autonomous;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team16488.library.subsystems.Subsystem;

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

public class Vision extends Subsystem {

    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = false;

    private static final String VUFORIA_KEY =
            "AQLgl7n/////AAABme+dNMPhrUUJjKAoNuY8bohUPjuCocER5Fpn94nlG5wvrLJZsJabuSihGcb5US+gHaLRCt20n4q2opXCriEaa+vi2pb3kIMMLuFioUVynCEJrTa9Y/9wPELJUwvpTfq55v6pSWfU/LIFnkTVIqm5OuG6X/KDeA3nTg6ykBYErTSd1zOYUabMdTR+DBKBevHF9NsmHo3/Le3XgCfopFYw049yYAVmRYy+dx84wlLhgF1JBNtDqx4rjQgICRzKQmKuh4EBe39ygQDnFd85uxD6Lbo6VZ3IuQeIrb0nu9eaD4H8oE+jRIvho8d3WJWR8smec0ddud1UFTRdXt69njtluVDe9zSU5vMGOnDn/cw8lQAb";
    private static final float mmPerInch = 25.4f;
    private static final float mmTargetHeight = (6) * mmPerInch;          // the height of the center of the target image above the floor

    private static final float stoneZ = 2.00f * mmPerInch;

    private static final float bridgeZ = 6.42f * mmPerInch;
    private static final float bridgeY = 23 * mmPerInch;
    private static final float bridgeX = 5.18f * mmPerInch;
    private static final float bridgeRotY = 59;                                 // Units are degrees
    private static final float bridgeRotZ = 180;

    private static final float halfField = 72 * mmPerInch;
    private static final float quadField = 36 * mmPerInch;
    // IMPORTANT:  For Phone Camera, set 1) the camera source and 2) the orientation, based on how your phone is mounted:
    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia = null;
    private boolean targetVisible = false;
    private float phoneXRotate = 0;
    private float phoneYRotate = 0;
    private float phoneZRotate = 0;
    private VuforiaTrackable stoneTarget;
    public VuforiaTrackables targetsSkyStone;
    WebcamName webcamName = null;
    private double delta_x;
    private double delta_y;
    public double angleFromPhone;
    private double relativeAngle;
    private double targetRange;
    public Vision(HardwareMap hardwareMap) {
        webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CAMERA_CHOICE;
        parameters.useExtendedTracking = false;
        parameters.cameraName = webcamName;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
        targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");
        stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");
        stoneTarget.setLocation(OpenGLMatrix
                .translation(0, 0, stoneZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));
        if (CAMERA_CHOICE == BACK) {
            phoneYRotate = -90;
        } else {
            phoneYRotate = 90;
        }
        if (PHONE_IS_PORTRAIT) {
            phoneXRotate = 90;
        }
        final float CAMERA_FORWARD_DISPLACEMENT = 0;   // eg: Camera is 4 Inches in front of robot center
        final float CAMERA_VERTICAL_DISPLACEMENT = 0;
        final float CAMERA_LEFT_DISPLACEMENT = 0;     // eg: Camera is ON the robot's center line
        OpenGLMatrix robotFromCamera = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));
        ((VuforiaTrackableDefaultListener) stoneTarget.getListener()).setPhoneInformation(robotFromCamera, parameters.cameraDirection);
    }

    public void update() {
        if (((VuforiaTrackableDefaultListener) stoneTarget.getListener()).isVisible()) {

            targetVisible = true;


            OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) stoneTarget.getListener()).getUpdatedRobotLocation();
            if (robotLocationTransform != null) {
                lastLocation = robotLocationTransform;
            }
        }


        if (targetVisible) {
            VectorF translation = lastLocation.getTranslation();
           delta_x = translation.get(0);
           delta_y = translation.get(1);
           double delta_z = translation.get(2);

            Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
            angleFromPhone = rotation.thirdAngle;

          targetRange = (Math.hypot(delta_x, delta_y)) / mmPerInch;

            double xToBlock1 = Math.toDegrees(Math.atan(delta_y / delta_x));

           relativeAngle = xToBlock1 - angleFromPhone;
//

        }


    }

    public String getName()
    {
        return stoneTarget.getName();
    }

    public double getPosX()
    {
        return  delta_x/ mmPerInch;
    }
    public double getPosY()
    {
        return  delta_y/ mmPerInch;
    }
    public double getRot() {

        return angleFromPhone;
    }
    public double getRelativeAngle()
    {
        return relativeAngle;
    }
    public double getTotalDistance()
    {
        return targetRange;
    }
    public boolean isTargetVisible()
    {
        return targetVisible;
    }



}