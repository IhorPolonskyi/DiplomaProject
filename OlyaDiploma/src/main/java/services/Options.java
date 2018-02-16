package services;

import org.apache.commons.cli.Option;

/**
 * Created by igorp on 19/01/18.
 */
public class Options {

    public static org.apache.commons.cli.Options options(){

        //option parameters
        Option help = new Option("h", "help", false, "Help");
        Option db = new Option("db", false, "Server name");
        Option host = new Option("host", false, "DB host name");
        Option comport = new Option("comport", false, "COM Port name");

        org.apache.commons.cli.Options options = new org.apache.commons.cli.Options();
        options.addOption(help);
        options.addOption(db);
        options.addOption(host);
        options.addOption(comport);

        return options;
    }
}
