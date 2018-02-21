package project;

import arduino.Arduino;
import lombok.extern.java.Log;
import services.InfoObject;
import services.ServerObject;

import java.sql.SQLException;

import static project.DataBase.writeData;


@Log
public class Server {

    public static void scanPort(ServerObject serverObject) throws InterruptedException, SQLException {

        InfoObject infoObject = new InfoObject();
        Arduino arduino = new Arduino(serverObject.getComPort(), 9600);
        boolean connected = arduino.openConnection();
        log.info("Connected: " + connected);
        Thread.sleep(2000);

        for(;;){

            String portRead = arduino.serialRead();

            if(portRead.contains("co2"))
                infoObject.setCo2(portRead.replace("co2:", ""));
            else if(portRead.contains("temp"))
                infoObject.setTemp(portRead.replace("temp:", ""));
            else if(portRead.contains("temp"))
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
}
