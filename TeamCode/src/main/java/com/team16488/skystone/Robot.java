package com.team16488.skystone;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.team16488.library.subsystems.ClawHeadMovement;
import com.team16488.library.subsystems.MecanumDrive;
import com.team16488.library.subsystems.PullerServos;
import com.team16488.library.subsystems.Subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import com.qualcomm.robotcore.util.ThreadPool;

public class Robot {
    private ExecutorService subsystemUpdateExecutor;
    private boolean started;

    public MecanumDrive drive;

    public PullerServos pullerServos;

    public ClawHeadMovement clawHeadMovement;

    private List<Subsystem> subsystems;

    private Telemetry telemetry;

    private Runnable subsystemUpdateRunnable = () -> {

        while (!Thread.currentThread().isInterrupted()) {

            try {

                for (Subsystem subsystem : subsystems) {

                    if (subsystem == null) continue;


                    try {

                        subsystem.update();

                    } catch (Throwable t) {

                        this.telemetry.addData("Exception running thread 1", "");
                        this.telemetry.update();

                    }

                }

            } catch (Throwable t) {

                this.telemetry.addData("Exception running thread 2", "");
                this.telemetry.update();
            }

        }

    };

    public Robot(OpMode opMode, Telemetry telemetry){
        this.telemetry = telemetry;

        subsystems = new ArrayList<>();
        /*
        try{
            drive = new MecanumDrive(opMode.hardwareMap);
            subsystems.add(drive);
        } catch (IllegalArgumentException e){

        }

        try{
            pullerServos = new PullerServos(opMode.hardwareMap);
            subsystems.add(pullerServos);
        }catch (IllegalArgumentException e){

        }

         */

        try{
            clawHeadMovement = new ClawHeadMovement(opMode.hardwareMap);
            subsystems.add(clawHeadMovement);
        }catch(IllegalArgumentException e){

        }


        subsystemUpdateExecutor = ThreadPool.newSingleThreadExecutor("subsystem update");

    }
    public void start(){
        if(!started){
            subsystemUpdateExecutor.submit(subsystemUpdateRunnable);
            started = true;
        }
    }
    private void stop(){
        if(subsystemUpdateExecutor != null){
            subsystemUpdateExecutor.shutdownNow();
            subsystemUpdateExecutor = null;
        }
    }









}
