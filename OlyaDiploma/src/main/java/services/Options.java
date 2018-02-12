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

        Option port = new Option("port", false, "COM Port name");

        org.apache.commons.cli.Options options = new org.apache.commons.cli.Options();
        options.addOption(help);
        options.addOption(db);
        options.addOption(port);

        return options;
    }
}
