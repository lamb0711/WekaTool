package edu.handong.csee.isel.weka;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class WekaTool {
	String resultDirectory = null;
	boolean verbose;
	boolean help;
	
	public static void main(String[]args) {
		WekaTool my = new WekaTool();
		my.run(args);
	}
	
	private void run(String[] args) {
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
			//

			if(verbose) {
				System.out.println("Your program is terminated. (This message is shown because you turned on -v option!");
			}
		}
	}
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);

//			gitRepositoryPath = cmd.getOptionValue("i");
			resultDirectory = cmd.getOptionValue("o");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("result").desc("directory will have result file").hasArg()
				.argName("directory").build());
		
		options.addOption(Option.builder("h").longOpt("help").desc("Help").build());

		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Collecting weka result program";
		String footer = "\nPlease report issues at https://github.com/lamb0711/WekaTool/issues";
		formatter.printHelp("WekaTool", header, options, footer, true);
	}

}
