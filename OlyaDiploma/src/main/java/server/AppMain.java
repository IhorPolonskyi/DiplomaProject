package server;

import services.*;
import org.apache.commons.cli.*;
import org.apache.commons.cli.Options;

import static services.Help.printHelpOptions;

/**
 * Created by igorp on 19/01/18.
 */
public class AppMain {
    ServerObject serverObject = new ServerObject("diploma","test","COM1");

    public void main(String[] args) {

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

            if (commandLine.hasOption("port"))
                serverObject.setComPort(commandLine.getOptionValue("port"));

            //show help
            else {
                printHelpOptions(options, 200, "Options", "-- HELP --", 3, 5, true, System.out);
            }
        } catch (ParseException e) {
            //catch exception, show help
            System.out.println("Something went wrong, see help to use program right");
            printHelpOptions(options, 200, "Options", "-- HELP --", 3, 5, true, System.out);
        }


    }
}


