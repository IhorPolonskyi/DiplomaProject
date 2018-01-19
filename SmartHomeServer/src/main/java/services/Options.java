package services;

import org.apache.commons.cli.Option;

/**
 * Created by igorp on 19/01/18.
 */
public class Options {

    public static org.apache.commons.cli.Options options(){

        //option parameters
        Option help = new Option("h", "help", false, "Help");

        Option wiFi = new Option("name", false, "Wi-Fi Name");
        Option password = new Option("passw", false, "Wi-Fi Password");

        org.apache.commons.cli.Options options = new org.apache.commons.cli.Options();
        options.addOption(help);
        options.addOption(wiFi);
        options.addOption(password);

        return options;
    }
}
