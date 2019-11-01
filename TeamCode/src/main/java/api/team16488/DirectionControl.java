package api.team16488;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
public class DirectionControl extends OpMode {

    MotorControl motorControl = new MotorControl();
    public void forward(double power, DcMotor FrontLeftMotor, DcMotor FrontRightMotor, DcMotor RearRightMotor, DcMotor RearLeftMotor){
        motorControl.setLeftSidePower(power, FrontLeftMotor, RearLeftMotor);
        motorControl.setRightSidePower(power,FrontRightMotor,FrontLeftMotor);

    }
    public void backward(double power, DcMotor FrontLeftMotor, DcMotor FrontRightMotor, DcMotor RearRightMotor, DcMotor RearLeftMotor){
        double speed = -power;
        motorControl.setLeftSidePower(speed, FrontLeftMotor, RearLeftMotor);
        motorControl.setRightSidePower(speed, FrontRightMotor, FrontLeftMotor);

    }
    public void strafeRight(double power, DcMotor FrontLeftMotor, DcMotor FrontRightMotor, DcMotor RearRightMotor, DcMotor RearLeftMotor){
        motorControl.setFrontLeftToBottomRightDiagonalPower(power,FrontLeftMotor,RearRightMotor);
        motorControl.setFrontRightToBottomLeftDiagonalPower(-power,FrontRightMotor,RearLeftMotor);
    }
    public void strafeLeft(double power, DcMotor FrontLeftMotor, DcMotor FrontRightMotor, DcMotor RearRightMotor, DcMotor RearLeftMotor){
        double speed = -power;
        motorControl.setFrontLeftToBottomRightDiagonalPower(power,FrontLeftMotor,RearRightMotor);
        motorControl.setFrontRightToBottomLeftDiagonalPower(-power,FrontRightMotor,RearLeftMotor);
    }





    public void init() {}
    public void loop(){}
}
