package com.caochaojie.cli;

import org.apache.commons.cli.*;
import org.junit.Test;

public class CliTest {
    private static Options options = new Options();

    public static void main(String[] args) {
        // for test
        String[] Args0 = {"-h"};
        String[] Args1 = {"-i", "192.168.1.1", "-p", "8443", "-t", "https"};

        Option help = new Option("h", "the command help");
        Option user = OptionBuilder.withArgName("type").hasArg().withDescription("target the search type").create("t");

        // 此处定义参数类似于 java 命令中的 -D<name>=<value>
        Option property = OptionBuilder.withArgName("property=value").hasArgs(2).withValueSeparator().withDescription
                ("search the objects which have the target property and value").create("D");
        Options opts = new Options();
        opts.addOption(help);
        opts.addOption(user);
        opts.addOption(property);

        simpleTest(Args1);

    }


    public static void simpleTest(String[] args) {
        Options opts = new Options();
        opts.addOption("h", false, "HELP_DESCRIPTION");
        opts.addOption("i", true, "HELP_IPADDRESS");
        opts.addOption("p", true, "HELP_PORT");
        opts.addOption("t", true, "HELP_PROTOCOL");
        CommandLineParser parser = new BasicParser();
//        CommandLineParser parser = new GnuParser ();
//        CommandLineParser parser = new PosixParser();
        CommandLine cl;
        try {
            cl = parser.parse(opts, args);
            if (cl.getOptions().length > 0) {
                if (cl.hasOption('h')) {
                    HelpFormatter hf = new HelpFormatter();
                    hf.printHelp("May Options", opts);
                } else {
                    String ip = cl.getOptionValue("i");
                    String port = cl.getOptionValue("p");
                    String protocol = cl.getOptionValue("t");
                    System.out.println(ip);
                    System.out.println(port);
                    System.out.println(protocol);
                }
            } else {
                System.err.println("ERROR_NOARGS");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
