package services;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by igorp on 19/01/18.
 */
public class Help {

    public static void printHelpOptions(
            final Options options,
            final int printedRowWidth,
            final String header,
            final String footer,
            final int spacesBeforeOption,
            final int spacesBeforeOptionDescription,
            final boolean displayUsage,
            final OutputStream out)
    {
        final String commandLineSyntax = "To start app in console insert: java -jar SmartHomeServer.jar";
        final PrintWriter writer = new PrintWriter(out);
        final HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(
                writer,
                printedRowWidth,
                commandLineSyntax,
                header,
                options,
                spacesBeforeOption,
                spacesBeforeOptionDescription,
                footer,
                displayUsage);
        writer.flush();
    }


}
