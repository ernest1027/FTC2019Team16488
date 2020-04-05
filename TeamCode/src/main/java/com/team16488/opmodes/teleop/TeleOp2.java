package com.team16488.opmodes.teleop;



import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.team16488.control.Control;
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
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp2", group = "telop")
public class TeleOp2 extends OpMode {
    /**
     * Robot object
     *
     * <p>See: {@link com.team16488.skystone.Robot}</p>
     */
    public Servo alternateLeftIntakeRaise, alternateLeftIntakeClose, alternateRightIntakeRaise, alternateRightIntakeClose, releaseCap, leftPuller, rightPuller, claw;

    public CRServo leftraiseIntake, rightraiseIntake, leftChain, rightChain;

    private boolean clawOpen;

    private boolean LeftON, Leftdown, Leftlock, RightON, Rightdown, Rightlock, release, intakeOn, intakeReverse, raiseIntakeOn, raiseIntake, pullerDown;
    private boolean pressedRT, pressedRB, pressedB, pressedA, pressedY, pressedX;

    private double power;
    public DcMotor leftIntake, rightIntake, LiftLeft, LiftRight, FrontLeftMotor, FrontRightMotor, RearRightMotor, RearLeftMotor;

    /**
     * This is what will be run on initialization of the robot
     * <p>
     * The main function of the method is to construct and create our objects
     * </p>
     */



    public void init() {
        FrontLeftMotor = hardwareMap.get(DcMotor.class, "FL");
        FrontRightMotor = hardwareMap.get(DcMotor.class, "FR");
        RearRightMotor = hardwareMap.get(DcMotor.class, "BR");
        RearLeftMotor = hardwareMap.get(DcMotor.class, "BL");
        leftPuller = hardwareMap.servo.get("LP");
        rightPuller = hardwareMap.servo.get("RP");
        leftIntake = hardwareMap.get(DcMotor.class, "LeftIntake");
        rightIntake = hardwareMap.get(DcMotor.class, "RightIntake");
        alternateLeftIntakeRaise = hardwareMap.servo.get("left alternate intake raise");
        alternateLeftIntakeClose = hardwareMap.servo.get("left alternate intake close");
        alternateRightIntakeRaise = hardwareMap.servo.get("right alternate intake raise");
        alternateRightIntakeClose = hardwareMap.servo.get("right alternate intake close");
        releaseCap = hardwareMap.servo.get("CapRelease");
        claw = hardwareMap.servo.get("ch1");
        leftraiseIntake = hardwareMap.crservo.get("LeftYeet");
        rightraiseIntake = hardwareMap.crservo.get("RightYeet");
        LiftLeft = hardwareMap.dcMotor.get("LiftLeft");
        LiftRight = hardwareMap.dcMotor.get("LiftRight");
        LiftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LiftLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LiftRight.setDirection(DcMotorSimple.Direction.REVERSE);
        FrontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftChain = hardwareMap.crservo.get("Left chain");
        rightChain = hardwareMap.crservo.get("Right chain");



    }


    @Override
    public void start() {

    }


    @Override
    public void loop() {
        setVelocity(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
        if(!pressedRT) {
            if (gamepad1.right_trigger > 0)
            {
                intakeOn = !intakeOn;
                pressedRT = true;
            }



        }
        if(gamepad1.right_trigger == 0)
        {
            pressedRT = false;
        }
        if(!pressedRB)
        {
            if(gamepad1.right_bumper == true)
            {
                intakeReverse = !intakeReverse;
                pressedRB = true;
            }
        }
        if(gamepad1.right_bumper==false)
        {
            pressedRB = false;
        }
        setIntake(intakeOn, intakeReverse);
        if(gamepad1.y == false)
        {
            pressedY = false;
        }
        if(!pressedY)
        {
            if(gamepad1.y== true)
            {
                Rightdown = !Rightdown;
                pressedY = true;
                RightON=true;
            }
        }
        setRightHook(RightON, Rightdown, Rightlock);
        if(!pressedX)
        {
            if(gamepad1.x== true)
            {
                Rightlock = !Rightlock;
                pressedX = true;
            }
        }
        if(gamepad1.x==false)
        {
            pressedX= false;
        }
        setRightHook(RightON, Rightdown, Rightlock);
        if(gamepad1.b == false)
        {
            pressedB = false;
        }
        if(!pressedB)
        {
            if(gamepad1.b== true)
            {
                Leftdown = !Leftdown;
                pressedB = true;
                LeftON=true;
            }
        }
        setLeftHook(LeftON, Leftdown, Leftlock);
        if(!pressedA)
        {
            if(gamepad1.a== true)
            {
                Leftlock = !Leftlock;
                pressedA = true;
            }
        }
        if(gamepad1.a==false)
        {
            pressedA = false;
        }
        setLeftHook(LeftON, Leftdown, Leftlock);
        if(gamepad1.left_trigger != 0){
            setIntakeRaise(true,true);
        }
        else if(gamepad1.left_bumper){
            setIntakeRaise(true,false);
        }
        else{
            setIntakeRaise(false,false);
        }

        setLift(gamepad2.left_stick_y, gamepad2.right_stick_y);
        if (gamepad2.right_bumper) {
            clawOpen = false;
        }
        if (gamepad2.right_trigger != 0) {
            clawOpen = true;
        }
        setClaw(clawOpen);

        if (gamepad2.left_bumper) {
            setChainBar(true,true);
        }
        else if (gamepad2.left_trigger != 0) {
            setChainBar(true,false);
        }
        else{
            setChainBar(false,true);
        }


        if(gamepad2.x)
        {
            setPuller(true);
        }
        if(gamepad2.y)
        {
            setPuller(false);
        }

        if(gamepad2.b){
            setReleaseCap(true);
        }
        if(gamepad2.a){
            setReleaseCap(false);
        }


    }


    @Override
    public void stop() {

    }

    public void setPuller(boolean pullerDown)
    {
        if (!pullerDown) {
            rightPuller.setPosition(0.85);
            leftPuller.setPosition(0.5);

        }
        if (pullerDown) {
            leftPuller.setPosition(1.0);
            rightPuller.setPosition(0);

        }
    }
    public void setLeftHook(boolean LeftON, boolean Leftdown, boolean Leftlock) {
        double pos;
        if (LeftON) {
            if (Leftdown) {
                pos = 1.0;
            } else {
                pos = 0.75;
            }
            if (Leftlock) {

                alternateLeftIntakeClose.setPosition(0.5);
            }
            if (!Leftlock) {

                alternateLeftIntakeClose.setPosition(0);
            }
            alternateLeftIntakeRaise.setPosition(pos);
        }
        else
        {
            pos = 0.5;
        }
        alternateLeftIntakeRaise.setPosition(pos);

    }
    public void setRightHook(boolean RightON, boolean Rightdown, boolean Rightlock) {
        double pos;
        if (RightON) {
            if (Rightdown) {
                pos = 0.5;
            } else {
                pos = 0.25;
            }
            if (Rightlock) {

                alternateRightIntakeClose.setPosition(0.5);
            }
            if (!Rightlock) {

                alternateRightIntakeClose.setPosition(1.0);
            }
            alternateRightIntakeRaise.setPosition(pos);
        }
        else
        {
            pos = 0;
        }
        alternateRightIntakeRaise.setPosition(pos);

    }
    public void setReleaseCap(boolean release) {
        if (release) {
            releaseCap.setPosition(0);
        }
        if (!release) {
            releaseCap.setPosition(1.0);
        }
    }

    public void setClaw(boolean clawOpen)
    {
        if (!clawOpen) {
            claw.setPosition(1.0);

        }


        if (clawOpen) {
            claw.setPosition(0.3);

        }
    }

    public void setIntake(boolean intakeReverse, boolean intakeOn)
    {
        if (intakeOn) {

            if (intakeReverse) {
                leftIntake.setPower(1);
                rightIntake.setPower(-1);
            } else {
                leftIntake.setPower(-1);
                rightIntake.setPower(1);
            }
        } else {
            leftIntake.setPower(0);
            rightIntake.setPower(0);
        }
    }

    public void setIntakeRaise(boolean raiseIntakeOn, boolean raiseIntake)
    {
        if(raiseIntakeOn) {
            if (raiseIntake) {
                leftraiseIntake.setPower(0.85);
                rightraiseIntake.setPower(0.85);
            }
            if (!raiseIntake) {
                leftraiseIntake.setPower(-0.85);
                rightraiseIntake.setPower(-0.85);
            }
        }
        else{
            leftraiseIntake.setPower(0);
            rightraiseIntake.setPower(0);
        }
    }

    public void setLift(double leftpower, double rightpower)
    {
        LiftLeft.setPower(leftpower);
        LiftRight.setPower(rightpower);
    }

    public void setVelocity(double leftstickx, double leftsticky, double rightstickx) {
        double r = Math.hypot(leftstickx, leftsticky);
        double robotAngle = Math.atan2(leftsticky, leftstickx) - Math.PI / 4;

        final double v1 = r * Math.cos(robotAngle) + rightstickx;
        final double v2 = r * Math.sin(robotAngle) - rightstickx;
        final double v3 = r * Math.sin(robotAngle) + rightstickx;
        final double v4 = r * Math.cos(robotAngle) - rightstickx;

        FrontLeftMotor.setPower(v1);
        FrontRightMotor.setPower(v2);
        RearLeftMotor.setPower(v3);
        RearRightMotor.setPower(v4);

    }

    public void setChainBar(boolean on, boolean extend)
    {
        if(on) {
            if (extend) {
                leftChain.setPower(0.85);
                rightChain.setPower(0.85);


            }
            if (!extend) {
                leftChain.setPower(-0.85);
                rightChain.setPower(-0.85);
            }
        }else{
            leftChain.setPower(0);
            rightChain.setPower(0);
        }
    }


}


