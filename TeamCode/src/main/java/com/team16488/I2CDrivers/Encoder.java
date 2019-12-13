package com.team16488.I2CDrivers;


import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchDevice;
import com.qualcomm.robotcore.hardware.configuration.annotations.DeviceProperties;
import com.qualcomm.robotcore.hardware.configuration.annotations.I2cDeviceType;

@I2cDeviceType
@DeviceProperties(name = "Leo And Simons Scetchy Encoders", description = "The Encoders for the DR4b", xmlTag = "LS16488")
public class Encoder extends I2cDeviceSynchDevice<I2cDeviceSynch> {
    public Encoder(I2cDeviceSynch deviceClient) {
        super(deviceClient, true);

        super.registerArmingStateCallback(false);
        this.deviceClient.engage();

    }

    @Override
    protected boolean doInitialize() {
        return true;
    }

    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.Unknown;
    }

    @Override
    public String getDeviceName() {
        return "Leo and Simons Encoders";
    }
}
