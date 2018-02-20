package server;

import lombok.extern.java.Log;
import services.*;
import org.apache.commons.cli.*;
import org.apache.commons.cli.Options;

import java.sql.SQLException;

import static services.Help.printHelpOptions;

/**
 * Created by igorp on 19/01/18.
 */
@Log
public class AppMain {
    ServerObject serverObject = new ServerObject("smarthome","localhost","olha", "123", "/dev/ttyUSB0", "indicators");

    public void main(String[] args) throws SQLException, InterruptedException{

        Options options = services.Options.options();

        try {
            //parse command line parameters
            CommandLineParser cmdLinePosixParser = new PosixParser();
            CommandLine commandLine = cmdLinePosixParser.parse(options, args);
            //help
            if (commandLine.hasOption("h"))
                printHelpOptions(options, 200, "Options", "-- HELP --", 3, 5, true, System.out);
            if (commandLine.hasOption("db"))
                serverObject.setDbName(commandLine.getOptionValue("db"));
            if (commandLine.hasOption("host"))
                serverObject.setDbName(commandLine.getOptionValue("host"));
            if (commandLine.hasOption("comport"))
                serverObject.setComPort(commandLine.getOptionValue("comport"));
            Server.scanPort(serverObject);
        } catch (ParseException e) {
            //catch exception, show help
            log.info("Something went wrong, see help to use program right");
            printHelpOptions(options, 200, "Options", "-- HELP --", 3, 5, true, System.out);
        }

    }
}


