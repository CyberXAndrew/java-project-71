package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
//    private final Integer errorCase = 100;
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", paramLabel = "format", description = "output format [default: stylish]")
    private String format;
////    @Option(names = {"-h", "--help"}, versionHelp = true, description = "Show this help message and exit.")
////    boolean versionInfoRequested;
////    @Option(names = {"-v", "--version"}, usageHelp = true, description = "Print version information and exit.")
////    private boolean helpRequested = false;
    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(filepath1, filepath2)); //format параметр
        return 0;
//        try {
//            System.out.println(Differ.generate(filepath1, filepath2, format));
//            return 0;
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//            return errorCase;
//        }
    }
//
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
