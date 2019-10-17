package team16488;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import team16488.MotorControl;

@Disabled
public class DriveTrain extends OpMode {
    MotorControl motorControl = new MotorControl();
    DriveModes driveModes = new DriveModes();


    public void arcadeDriveAndStrafe(double x, double y, double rotationValue, DcMotor FrontLeftMotor, DcMotor FrontRightMotor, DcMotor RearRightMotor, DcMotor RearLeftMotor){

        driveModes.arcadeDrive(x,rotationValue,FrontLeftMotor,FrontRightMotor,RearRightMotor,RearLeftMotor);
        driveModes.strafe(x,FrontLeftMotor,FrontRightMotor,RearRightMotor,RearLeftMotor);

    }
    public void mecanumDrive(double x, double y, double rotationValue, DcMotor FrontLeftMotor, DcMotor FrontRightMotor, DcMotor RearRightMotor, DcMotor RearLeftMotor){



        if(gamepad1.left_trigger>0)
        {
            driveModes.diagonalStrafe(x,y,rotationValue,FrontLeftMotor,FrontRightMotor,RearRightMotor,RearLeftMotor);

        }
        else
        {
            arcadeDriveAndStrafe(x,y,rotationValue,FrontLeftMotor,FrontRightMotor,RearRightMotor,RearLeftMotor);
        }

    }
    public void init(){}
    public void loop(){}
}
