package project;

import com.fazecast.jSerialComm.SerialPort;
import lombok.extern.java.Log;
import services.InfoObject;
import services.ServerObject;

import java.sql.SQLException;
import java.util.Scanner;

import static project.DataBase.writeData;


@Log
public class Server {

    public static void scanPort(ServerObject serverObject) throws InterruptedException, SQLException {

        InfoObject infoObject = new InfoObject();
        startPortReading(serverObject.getComPort(), 115200);
        boolean connected = openConnection();
        log.info("Connected: " + connected);
        Thread.sleep(2000);

        for(;;){

            String portRead = serialRead();

            if(portRead.contains("co2"))
                infoObject.setCo2(portRead.replace("co2:", ""));
            else if(portRead.contains("temp"))
                infoObject.setTemp(portRead.replace("temp:", ""));
            else if(portRead.contains("hum"))
                infoObject.setHum(portRead.replace("hum:", ""));

            if(!infoObject.getCo2().isEmpty()&&!infoObject.getData().isEmpty()&&!infoObject.getHum().isEmpty()){
                writeData(serverObject, infoObject);
                infoObject.setTemp(null);
                infoObject.setCo2(null);
                infoObject.setHum(null);
                log.info("Data was written to db");
            }
        }
    }

    private static SerialPort comPort;

    private static void startPortReading(String portDescriptionNew, int baud_rateNew){
        comPort = SerialPort.getCommPort(portDescriptionNew);
        comPort.setBaudRate(baud_rateNew);

    }

    private static void startPortReading(String portDescriptionNew){
        comPort = SerialPort.getCommPort(portDescriptionNew);
    }

    private static boolean openConnection(){
        if(comPort.openPort()){
            try {Thread.sleep(100);} catch(Exception e){}
            return true;
        }
        else {
            log.info("Error Connecting, try another port");
            return false;
        }
    }

    private static String serialRead(){
        //will be an infinite loop if incoming data is not bound
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        String out="";
        Scanner in = new Scanner(comPort.getInputStream());
        try
        {
            while(in.hasNext())
                out += (in.next()+"\n");
            in.close();
        } catch (Exception e) { e.printStackTrace(); }
        return out;
    }



}
