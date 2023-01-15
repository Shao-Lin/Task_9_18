package Task;

import org.apache.commons.cli.*;
import util.ArrayUtils;

import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

public class Main {
    public static final String PROGRAM_NAME_IN_HELP = "program (-h | -w | -s -i <in-file> -i2 <in-file2> [-o <out-file>])";

    public static void winMain() throws Exception {
        Locale.setDefault(Locale.ROOT);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }


    public static void main(String[] args) throws Exception {
        Options cmdLineOptions = new Options();
        cmdLineOptions.addOption("s", "sort", false, "Sort");
        cmdLineOptions.addOption("h", "help", false, "Show help");
        cmdLineOptions.addOption("w", "window", false, "Use window user interface");
        cmdLineOptions.addOption("i", "input-file", true, "Input file");
        cmdLineOptions.addOption("i2", "input-file2", true, "Input 2 file");
        cmdLineOptions.addOption("o", "output-file", true, "Output file");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine = null;
        try {
            cmdLine = parser.parse(cmdLineOptions, args);
        } catch (Exception e) {
            new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
            System.exit(1);
        }

        if (cmdLine.hasOption("h")) {
            new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
            System.exit(1);
        }
        if (cmdLine.hasOption("w")) {
            winMain();
        } else {
            if (!cmdLine.hasOption("i") ||
                    !cmdLine.hasOption("s")) {
                new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
                System.exit(1);
            }
            String inputFilename = cmdLine.getOptionValue("i");
            int[] arrayList = ArrayUtils.readIntArrayFromFile(inputFilename);
            String inputFilename2 = cmdLine.getOptionValue("i2");
            int[] arrayList2 = ArrayUtils.readIntArrayFromFile(inputFilename2);
            int[] answer = new int[0];
            if (arrayList == null) {
                System.err.printf("Can't read array from \"%s\"%n", inputFilename);
                System.exit(2);
            }
            if (arrayList2 == null) {
                System.err.printf("Can't read array from \"%s\"%n", inputFilename2);
                System.exit(2);
            }
            if (cmdLine.hasOption("s")) {

                List<Integer> list = Logic.createNewList(ArrayUtils.toList(arrayList), ArrayUtils.toList(arrayList2));
                answer = list.stream().mapToInt(Integer::intValue).toArray();
            }

            PrintStream out = (cmdLine.hasOption("o")) ? new PrintStream(cmdLine.getOptionValue("o")) : System.out;
            out.println(ArrayUtils.toString(answer));
            out.close();
        }
    }
}