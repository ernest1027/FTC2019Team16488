package api.team16488;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
public class MapMotors extends OpMode {

    public void mapHardware(DcMotor FrontLeftMotor, DcMotor FrontRightMotor, DcMotor RearRightMotor, DcMotor RearLeftMotor){
        FrontLeftMotor = hardwareMap.dcMotor.get("m4");
        FrontRightMotor = hardwareMap.dcMotor.get("m3");
        RearRightMotor = hardwareMap.dcMotor.get("m2");
        RearLeftMotor = hardwareMap.dcMotor.get("m1");
    }



    public void init(){}
    public void loop(){}
}
