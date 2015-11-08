package com.tarasantoshchuk.twin.util;

import com.tarasantoshchuk.twin.input.Flag;
import com.tarasantoshchuk.twin.util.exception.InvalidArgumentsException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ErrorHelper {
    private static final String FILE_NOT_FOUND_FORMAT = "File %s not found";
    private static final String IO_EXCEPTION_FORMAT = "Input/Output exception reading file %s";
    private static final String WARNINGS_TITLE = "WARNINGS:";

    private static List<String> warnings = new ArrayList<>();

    public static void missingArguments() {
        System.out.println("Missing input arguments");
        handleInvalidArguments();
    }

    public static void invalidFlagArguments(Flag flag) {
        System.out.println("Invalid arguments for flag " + flag.getFlagString());
        handleInvalidArguments();
    }

    public static void missingFlagArguments(Flag flag) {
        System.out.println("Missing arguments for flag " + flag.getFlagString());
        handleInvalidArguments();
    }

    public static void missingTargetFolder() {
        System.out.println("You must provide path to folder as last argument");
        handleInvalidArguments();
    }

    public static void tooManyArguments() {
        System.out.println("You mustn't place any arguments after target folder arguments");
        handleInvalidArguments();
    }

    public static void pathIsNotDirectory() {
        System.out.println("You provided path that is not path to directory");
        handleInvalidArguments();
    }

    private static void handleInvalidArguments() {
        throw new InvalidArgumentsException();
    }

    public static void fileNotFound(File file) {
        warnings.add(String.format(FILE_NOT_FOUND_FORMAT, file.getAbsolutePath()));
    }

    public static void ioExceptionReadingFile(File file) {
        warnings.add(String.format(IO_EXCEPTION_FORMAT, file.getAbsolutePath()));
    }

    public static void printWarnings() {
        if(!warnings.isEmpty()) {
            System.out.println(WARNINGS_TITLE);
            for(String warning: warnings) {
                System.out.println(warning);
            }
        }
    }
}
